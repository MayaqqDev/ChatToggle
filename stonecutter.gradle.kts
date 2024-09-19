plugins {
    id("dev.kikugie.stonecutter")
    id("dev.architectury.loom") version "1.7-SNAPSHOT" apply false
}
stonecutter active "1.18.2-forge" /* [SC] DO NOT EDIT */

stonecutter registerChiseled tasks.register("chiseledBuildAndCollect", stonecutter.chiseled) {
    group = "project"
    ofTask("buildAndCollect")
}


stonecutter.configureEach {
    val platform = project.property("loom.platform")

    fun String.propDefined() = project.findProperty(this)?.toString()?.isNotBlank() ?: false
    consts(listOf(
        "fabric" to (platform == "fabric"),
        "forge" to (platform == "forge"),
        "neoforge" to (platform == "neoforge"),
        "forge-like" to (platform == "forge" || platform == "neoforge"),
    ))
}
