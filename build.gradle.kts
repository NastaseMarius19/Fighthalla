import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
    id ("info.solidsoft.pitest") version "1.7.0"// Adaugă plugin-ul PIT
}

group = "me.smooth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin test library
    testImplementation(kotlin("test"))

    // JUnit Jupiter API and Engine for JUnit 5
    testImplementation("junit:junit:4.13.2")
    // Mockito for Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
}

tasks.test {
    // Use JUnit Platform for testing
    useJUnit()
}

tasks.withType<KotlinCompile> {
    // Set JVM target to Java 11
    kotlinOptions.jvmTarget = "11"
}

application {
    // Define the entry point for the application
    mainClass.set("MainKt")
}

pitest {
    targetClasses.set(listOf("abstract.*", "controller.*")) // Pentru clasele din pachetele abstract și controller
    targetTests.set(listOf("*")) // Presupunând că testele tale sunt în directorul src/main/kotlin/test
    threads.set(4)
    outputFormats.set(listOf("HTML", "XML"))
    timestampedReports.set(false)
    mutationThreshold.set(16)
    coverageThreshold.set(24)
    testPlugin.set("junit4") // Folosind JUnit 4 pentru testele tale
}