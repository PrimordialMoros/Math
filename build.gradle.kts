plugins {
    `java-library`
    signing
    `maven-publish`
    alias(libs.plugins.checker)
}

allprojects {
    group = "me.moros"
    version = "2.0.0"

    apply(plugin = "java-library")
    apply(plugin = "org.checkerframework")

    repositories {
        mavenCentral()
    }

    configure<JavaPluginExtension> {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }

    tasks {
        withType<JavaCompile> {
            options.compilerArgs.addAll(listOf("-Xlint:unchecked", "-Xlint:deprecation"))
            options.encoding = "UTF-8"
        }
        withType<AbstractArchiveTask> {
            isPreserveFileTimestamps = false
            isReproducibleFileOrder = true
        }
        named<Copy>("processResources") {
            from("$rootDir/LICENSE") {
                rename { "${rootProject.name.uppercase()}_${it}" }
            }
        }
    }
}
subprojects {
    apply(plugin = "signing")
    apply(plugin = "maven-publish")

    java {
        if (!isSnapshot()) {
            withJavadocJar()
        }
        withSourcesJar()
    }

    tasks {
        withType<Sign>().configureEach {
            onlyIf { !isSnapshot() }
        }
    }

    publishing {
        publications.create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set(project.name)
                description.set("Immutable vector and math library for minecraft.")
                url.set("https://github.com/PrimordialMoros/Math")
                licenses {
                    license {
                        name.set("The GNU General Public License, Version 3.0")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("moros")
                        name.set("Moros")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/PrimordialMoros/Math.git")
                    developerConnection.set("scm:git:ssh://git@github.com/PrimordialMoros/Math.git")
                    url.set("https://github.com/PrimordialMoros/Math")
                }
                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/PrimordialMoros/Math/issues")
                }
            }
        }
        if (project.hasProperty("ossrhUsername") && project.hasProperty("ossrhPassword")) {
            val user = project.property("ossrhUsername") as String?
            val pass = project.property("ossrhPassword") as String?
            val snapshotUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            val releaseUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            repositories {
                maven {
                    credentials { username = user; password = pass }
                    url = if (isSnapshot()) snapshotUrl else releaseUrl
                }
            }
        }
    }

    signing {
        sign(publishing.publications["maven"])
    }
}

fun isSnapshot() = project.version.toString().endsWith("-SNAPSHOT")
