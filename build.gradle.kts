import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar

plugins {
    `java-library`
    signing
    alias(libs.plugins.maven.publish)
}

allprojects {
    group = "me.moros"
    version = "4.0.0"

    apply(plugin = "java-library")

    repositories {
        mavenCentral()
    }

    configure<JavaPluginExtension> {
        toolchain.languageVersion = JavaLanguageVersion.of(21)
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
        jar {
            val licenseName = "LICENSE_${rootProject.name.uppercase()}"
            from("$rootDir/LICENSE") {
                into("META-INF")
                rename { licenseName }
            }
        }
    }
}
subprojects {
    apply(plugin = "signing")
    apply(plugin = "com.vanniktech.maven.publish")

    mavenPublishing {
        pom {
            name = project.name
            description = "TImmutable vector and math library for minecraft."
            url = "https://github.com/PrimordialMoros/math"
            inceptionYear = "2020"
            licenses {
                license {
                    name = "The GNU General Public License, Version 3.0"
                    url = "https://www.gnu.org/licenses/gpl-3.0.txt"
                    distribution = "repo"
                }
            }
            developers {
                developer {
                    id = "moros"
                    name = "Moros"
                    url = "https://github.com/PrimordialMoros"
                }
            }
            scm {
                connection = "scm:git:https://github.com/PrimordialMoros/math.git"
                developerConnection = "scm:git:ssh://git@github.com/PrimordialMoros/math.git"
                url = "https://github.com/PrimordialMoros/math"
            }
            issueManagement {
                system = "Github"
                url = "https://github.com/PrimordialMoros/math/issues"
            }
        }
        configure(JavaLibrary(JavadocJar.Javadoc(), true))
        publishToMavenCentral()
        signAllPublications()
    }
}
