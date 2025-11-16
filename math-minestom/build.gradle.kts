dependencies {
    api(projects.mathCore)
    compileOnly(libs.minestom.api)
}

configure<JavaPluginExtension> {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}
