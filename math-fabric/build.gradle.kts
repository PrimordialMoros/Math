plugins {
    alias(libs.plugins.fabric.loom)
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    api(project(":math-core"))
    minecraft(libs.fabric.minecraft)
    mappings(loom.officialMojangMappings())
    modCompileOnly(libs.fabric.api)
    modCompileOnly(libs.fabric.loader)
}
