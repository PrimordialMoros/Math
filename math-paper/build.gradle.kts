repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(projects.mathCore)
    compileOnly(libs.paper.api)
}
