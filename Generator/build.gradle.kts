plugins {
    id("kotlin")
}

sourceSets.main {
    java.srcDirs("src")
}

tasks.jar {
    archiveBaseName.set("boilerplate-generator")
    from(configurations.runtimeClasspath.get().filter { !it.path.endsWith(".pom") }.map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "ApplicationKt"
    }
}
