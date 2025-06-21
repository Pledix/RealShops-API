group = "dev.pledix.realshops"
version = "1.4.2"

val serverVersion = "1.20.4-R0.1-SNAPSHOT"

plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev")
    id("com.github.johnrengelman.shadow") // Shadow Plugin
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://repo.velocitypowered.com/snapshots/")
        name = "velocity"
    }
    maven {
        url = uri("https://repo.codemc.org/repository/maven-public/")
        name = "codemc"
    }
    maven {
        url = uri("https://maven.enginehub.org/repo/")
        name = "enginehub"
    }
}

dependencies {
    val versionText = serverVersion.split("-")[0]
    if (versionText == "1.20.6") {
        compileOnly("dev.folia:folia-api:$serverVersion")
    }
    paperweight.paperDevBundle(serverVersion)
}

configurations.mojangMappedServer {
    val versionText = serverVersion.split("-")[0]
    if (versionText == "1.20.6") {
        exclude("io.papermc.paper", "paper-api")
    }
}

tasks.jar {
    archiveBaseName.set("RealShops-API")
    archiveVersion.set("")
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = "dev.pledix.realshops"
            artifactId = "api"
            version = "1.4.2"

            pom {
                name.set("RealShops API")
                description.set("API module for RealShops plugin")
                url.set("https://github.com/Pledix/RealShops-API")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("Pledix")
                        name.set("Enes")
                        email.set("eneskilinc_@hotmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/Pledix/RealShops-API.git")
                    developerConnection.set("scm:git:ssh://git@github.com/Pledix/RealShops-API.git")
                    url.set("https://github.com/Pledix/RealShops-API")
                }
            }
        }
    }

    repositories {
        maven {
            name = "central"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = "token"  // Sabit olarak "token" yazılır
                password = findProperty("ossrhApiToken") as String
            }
        }
    }
}