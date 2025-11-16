repositories {
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    api(projects.mathCore)
    compileOnly(libs.sponge.api)
}
