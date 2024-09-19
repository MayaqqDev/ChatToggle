plugins {
    id("dev.architectury.loom") version "1.7-SNAPSHOT" apply true
    id("maven-publish")
}

class ModData {
    val id = property("mod.id").toString()
    val name = property("mod.name").toString()
    val version = property("mod.version").toString()
    val group = property("mod.group").toString()
    val loader = property("loom.platform").toString()
}

class ModDeps {
    operator fun get(name: String) = property("deps.$name").toString()
}

val mod = ModData()
val deps = ModDeps()
val mcVersion = stonecutter.current.version

val isFabric = mod.loader == "fabric"
val isForge = mod.loader == "forge"
val isNeoforge = mod.loader == "neoforge"
val isForgeLike = isForge || isNeoforge

version = "${mod.version}+$mcVersion"

base { archivesName.set(mod.id) }

loom {
    runConfigs.all {
        ideConfigGenerated(false)
        vmArgs("-Dmixin.debug.export=true")
        runDir = "../../run"
    }
}


repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases/")
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

dependencies {

    var module = "fabric"

    mappings(loom.layered {
        // Fabric breaks with parchment for some reason and I CBA to fix it
        // so you're getting raw mojmap unless you want to change it
        // parchment("org.parchmentmc.data:parchment-${deps["parchment"]}@zip")
        officialMojangMappings()
    })
    if (isFabric) {
        modImplementation("net.fabricmc:fabric-loader:${deps["fabric_loader"]}")
        fun fapi(vararg modules: String) {
            modules.forEach { fabricApi.module(it, deps["fabric_api"]) }
        }
        modImplementation("net.fabricmc.fabric-api:fabric-api:${deps["fabric_api"]}")
    }
    if (isNeoforge) {
        "neoForge"("net.neoforged:neoforge:${deps["neoforge"]}")
        module = "neoforge"
    }
    if (isForge) {
        "forge"("net.minecraftforge:forge:${deps["forge"]}")
        module = "forge-latest"
    }
    minecraft("com.mojang:minecraft:${deps["minecraft"]}")

    modRuntimeOnly("me.djtheredstoner:DevAuth-${module}:${deps["devauth"]}")
}

java {
    withSourcesJar()
    val java = if (stonecutter.eval(mcVersion, ">=1.20.6")) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
    targetCompatibility = java
    sourceCompatibility = java
}

tasks.processResources {
    val props = buildMap {
        put("id", mod.id)
        put("name", mod.name)
        put("version", mod.version)
        put("minecraft", deps["minecraft"])
        if (isForgeLike) {
            put("forgeConstraint", deps["forge_constraint"])
        }
        if (isFabric) {
            put("fabricLoader", deps["fabric_loader"])
        }
    }

    props.forEach(inputs::property)

    if (isFabric) {
        filesMatching("fabric.mod.json") { expand(props) }
        exclude(listOf("META-INF/mods.toml", "META-INF/neoforge.mods.toml"))
    }

    if (isForgeLike) {
        filesMatching(listOf("META-INF/mods.toml", "META-INF/neoforge.mods.toml")) { expand(props) }
        exclude("fabric.mod.json")
    }

}

tasks.register<Copy>("buildAndCollect") {
    group = "build"
    from(tasks.remapJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}"))
    rename { "${mod.id}-${mod.version}+${mod.loader}+${mcVersion}.jar" }
    dependsOn("build")
}

if (stonecutter.current.isActive) {
    rootProject.tasks.register("buildActive") {
        group = "project"
        dependsOn(tasks.named("build"))
    }
}

loom.runConfigs.all {
    ideConfigGenerated(true)
    runDir = "../../run"
}

if (stonecutter.current.isActive) {
    rootProject.tasks.register("runActiveClient") {
        group = "project"
        dependsOn(tasks.named("runClient"))
    }
}
