repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(project(":math-core"))
    compileOnly(libs.paper.api)
}
