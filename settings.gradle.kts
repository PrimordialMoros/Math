enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
    }
}

rootProject.name = "math"

include("math-core")
include("math-paper")
include("math-sponge")
include("math-fabric")
include("math-minestom")
