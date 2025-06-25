import paper.libs.org.apache.commons.codec.digest.DigestUtils

group = "dev.pledix.realshops"
version = "1.0.0"

val serverVersion = "1.20.4-R0.1-SNAPSHOT"

plugins {
    `java-library`
    `maven-publish`
    signing
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
    compileOnly("commons-codec:commons-codec:1.15")
}

configurations.mojangMappedServer {
    val versionText = serverVersion.split("-")[0]
    if (versionText == "1.20.6") {
        exclude("io.papermc.paper", "paper-api")
    }
}

/*tasks.jar {
    archiveBaseName.set("realshops-api")
    archiveVersion.set("")
}*/

java {
    withSourcesJar()
    withJavadocJar()
}

val javadocJar = tasks.named<Jar>("javadocJar")
val sourcesJar = tasks.named<Jar>("sourcesJar")

val copyPomToLibs by tasks.registering {
    dependsOn("publishToMavenLocal")
    doLast {
        val m2Repo = File(System.getProperty("user.home"))
            .resolve(".m2/repository/dev/pledix/realshops/realshops-api/$version/")

        val libsDir = layout.buildDirectory.dir("libs").get().asFile

        listOf(
            "realshops-api-$version.pom",
            "realshops-api-$version.pom.asc"
        ).forEach { filename ->
            val sourceFile = m2Repo.resolve(filename)
            if (sourceFile.exists()) {
                sourceFile.copyTo(libsDir.resolve(filename), overwrite = true)
            } else {
                logger.warn("Dosya bulunamadı: $sourceFile")
            }
        }
    }
}

val generateChecksums by tasks.registering {
    dependsOn(copyPomToLibs)
    doLast {
        val libsDir = layout.buildDirectory.dir("libs").get().asFile
        libsDir.listFiles { file -> file.extension in listOf("jar", "pom") }?.forEach { file ->
            File("${file.absolutePath}.md5").writeText(DigestUtils.md5Hex(file.readBytes()))
            File("${file.absolutePath}.sha1").writeText(DigestUtils.sha1Hex(file.readBytes()))
        }
    }
}

// Kopyalama ve ZIP oluşturma task'ı
val preparePackage by tasks.registering {
    dependsOn(generateChecksums)
    doLast {
        val libsDir = layout.buildDirectory.dir("libs").get().asFile

        // Hedef klasör: build/package/dev/pledix/realshops/realshops-api/1.4.2
        val versionDir = File(buildDir, "package/dev/pledix/realshops/realshops-api/$version")
        if (versionDir.exists()) versionDir.deleteRecursively()
        versionDir.mkdirs()

        // libs içindeki tüm dosyaları hedef klasöre kopyala
        libsDir.listFiles()?.forEach { file ->
            file.copyTo(versionDir.resolve(file.name), overwrite = true)
        }

        val packageDir = File(buildDir, "package")
        val zipFile = File(packageDir, "realshops-api-$version.zip")
        if (zipFile.exists()) zipFile.delete()

        // ZIP'leme: package içindeki 'dev' klasörünü ziple (yani zipin kökü 'dev' olacak)
        ant.withGroovyBuilder {
            "zip"(
                "destfile" to zipFile.absolutePath,
                "basedir" to packageDir.absolutePath,
                "includes" to "dev/**"
            )
        }

        println("ZIP dosyası oluşturuldu: ${zipFile.absolutePath}")
    }
}

tasks.named("publishToMavenLocal") {
    finalizedBy(copyPomToLibs)
    finalizedBy(generateChecksums)
}

tasks.named("build") {
    dependsOn("publishToMavenLocal")
    dependsOn(generateChecksums)
    dependsOn(preparePackage)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "realshops-api"

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
                        name.set("Pledix")
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
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}