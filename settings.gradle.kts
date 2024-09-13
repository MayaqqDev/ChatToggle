pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.parchmentmc.org")
        maven("https://maven.architectury.dev")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.minecraftforge.net/")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.4.4" apply true
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    shared {
        fun mc(version: String, loaders: Iterable<String>, name: String = version) {
            for (loader in loaders) {
                vers("$name-$loader", version)
            }
        }
        mc("1.18.2", listOf("fabric", "forge"))
        mc("1.19.2", listOf("fabric", "forge"))
        mc("1.20.1", listOf("fabric", "forge"))
        mc("1.21", listOf("fabric", "neoforge"))
    }
    create(rootProject)
}

rootProject.name = "Template"