repositories {
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    api(project(":math-core"))
    compileOnly(libs.sponge.api)
}
