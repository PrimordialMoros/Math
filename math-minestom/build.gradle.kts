repositories {
    maven("https://jitpack.io/")
}

dependencies {
    api(project(":math-core"))
    compileOnly(libs.minestom.api)
}
