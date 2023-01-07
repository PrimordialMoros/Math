pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
    }
}

rootProject.name = "math"
include("math-core")
include("math-bukkit")
include("math-sponge")
include("math-fabric")
include("math-minestom")
