plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(gradleTestKit())
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.2")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

tasks.withType<Test> {
    useJUnitPlatform()
    failFast = true
    jvmArgs("--enable-preview", "-XX:+ShowCodeDetailsInExceptionMessages")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}