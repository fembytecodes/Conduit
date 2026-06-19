plugins {
    `java-library`
    `maven-publish`
}

group = providers.gradleProperty("group").orElse("dev.fembyte").get()
version = providers.gradleProperty("version").orElse("0.1.0-SNAPSHOT").get()

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "conduit"
            pom {
                name.set("Conduit")
                description.set("A lightweight, annotation-driven event bus for Java.")
                url.set("https://github.com/fembytecodes/Conduit")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("fembyte")
                        name.set("fembyte")
                        url.set("https://github.com/fembytecodes")
                    }
                }
                scm {
                    url.set("https://github.com/fembytecodes/Conduit")
                    connection.set("scm:git:https://github.com/fembytecodes/Conduit.git")
                    developerConnection.set("scm:git:ssh://git@github.com:fembytecodes/Conduit.git")
                }
            }
        }
    }
    repositories {
        maven {
            name = "fembyteReleases"
            url = uri("https://maven.fembyte.dev/releases")
            credentials {
                username = providers.gradleProperty("fembyteMavenUsername").orNull
                password = providers.gradleProperty("fembyteMavenPassword").orNull
            }
        }
    }
}
