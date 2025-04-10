//group = rootProject.group
//version = rootProject.version

group = "dev.pledix.realshops"
version = "1.3"

val serverVersion: String by rootProject.extra

plugins {
    `java-library`
    `maven-publish`
    id("io.papermc.paperweight.userdev") version "1.7.7"
    id("com.github.johnrengelman.shadow") version "8.1.1" // Shadow Plugin
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
    archiveBaseName.set(rootProject.name + "API")
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
            version = "1.3"
        }
    }

    repositories {
        maven {
            name = "CentralPortal"
            url = uri("https://central.sonatype.com/api/v1/publish/")

            credentials {
                username = project.findProperty("cppUsername") as String?
                password = project.findProperty("cppPassword") as String?
            }
        }
    }
}