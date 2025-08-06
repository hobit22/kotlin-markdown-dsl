plugins {
    kotlin("jvm") version "2.1.20"
    signing
    id("com.vanniktech.maven.publish") version "0.34.0"
}


group = "io.github.hobit22"
version = "1.0.3"

repositories {
    mavenCentral()

    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}


mavenPublishing {
    coordinates(
        groupId = "io.github.hobit22",
        artifactId = "kotlin-markdown-dsl",
        version = "1.0.3")

    pom {
        name.set("kotlin-markdown-dsl")
        description.set("A Kotlin-based DSL library to generate Markdown documents programmatically. Supports structured document creation, multiple character encodings, and automation-friendly use cases.")
        inceptionYear.set("2025")
        url.set("https://github.com/hobit22/kotlin-markdown-dsl")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("Rojojun")
                name.set("Hojun Na")
                url.set("https://github.com/Rojojun")
            }
            developer {
                id.set("hobit22")
                name.set("Hobin Kim")
                url.set("https://github.com/hobit22")
            }
        }
        scm {
            url.set("https://github.com/hobit22/kotlin-markdown-dsl")
            connection.set("scm:git:git://github.com/hobit22/kotlin-markdown-dsl.git")
            developerConnection.set("scm:git:ssh:// github.com/hobit22/kotlin-markdown-dsl.git")
        }
    }


    publishToMavenCentral()

    signAllPublications() // GPG/PGP 서명

}

signing {
    useInMemoryPgpKeys(
        findProperty("signingKeyId") as String?,
        findProperty("signingKey") as String?,
        findProperty("signingPassword") as String?
    )
}