import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

public class Lesson1Task4IntegrationTest {

    @Test
    @DisplayName("Should pass if junit dependencies ware added and test task is configured")
    void test1() {
        //given kts script with configured java plugin
        var kts = """
                    import org.gradle.api.tasks.testing.logging.TestExceptionFormat
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                    
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        sourceCompatibility = JavaVersion.VERSION_11
                        targetCompatibility = JavaVersion.VERSION_11
                    }
                    
                    repositories {
                        maven("https://dl.google.com/android/maven2/")
                        mavenCentral()
                    }
                    
                    dependencies {
                        implementation("com.google.mlkit:face-detection:16.0.1")
                        implementation("com.google.guava:guava:29.0-jre")
                        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
                        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.2")
                    }
                    
                    tasks.withType<Test> {
                        useJUnitPlatform()
                        testLogging {
                            exceptionFormat = TestExceptionFormat.FULL
                            events = setOf(FAILED, SKIPPED)
                        }
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should pass if junit dependencies ware added and test task is configured even if java plugin is not configured")
    void test2() {
        //given kts script with configured java plugin
        var kts = """
                    import org.gradle.api.tasks.testing.logging.TestExceptionFormat
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                    
                    plugins {
                        java
                    }
                    
                    repositories {
                        maven("https://dl.google.com/android/maven2/")
                        mavenCentral()
                    }
                    
                    dependencies {
                        implementation("com.google.mlkit:face-detection:16.0.1")
                        implementation("com.google.guava:guava:29.0-jre")
                        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
                        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.2")
                    }
                    
                    tasks.withType<Test> {
                        useJUnitPlatform()
                        testLogging {
                            exceptionFormat = TestExceptionFormat.FULL
                            events = setOf(FAILED, SKIPPED)
                        }
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should pass if junit dependencies ware added and test task is configured even if java plugin is not configured and face-detection dependence was not added")
    void test3() {
        //given kts script with configured java plugin
        var kts = """
                    import org.gradle.api.tasks.testing.logging.TestExceptionFormat
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                    
                    plugins {
                        java
                    }
                    
                    repositories {
                        mavenCentral()
                    }
                    
                    dependencies {
                        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
                        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.2")
                    }
                    
                    tasks.withType<Test> {
                        useJUnitPlatform()
                        testLogging {
                            exceptionFormat = TestExceptionFormat.FULL
                            events = setOf(FAILED, SKIPPED)
                        }
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if junit-jupiter-api dependence was not add")
    void test4() {
        //given kts script with configured java plugin
        var kts = """
                    import org.gradle.api.tasks.testing.logging.TestExceptionFormat
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                    
                    plugins {
                        java
                    }
                    
                    repositories {
                        mavenCentral()
                    }
                    
                    dependencies {
                        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.2")
                    }
                    
                    tasks.withType<Test> {
                        useJUnitPlatform()
                        testLogging {
                            exceptionFormat = TestExceptionFormat.FULL
                            events = setOf(FAILED, SKIPPED)
                        }
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if junit-jupiter-engine dependence was not add")
    void test5() {
        //given kts script with configured java plugin
        var kts = """
                    import org.gradle.api.tasks.testing.logging.TestExceptionFormat
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                    
                    plugins {
                        java
                    }
                    
                    repositories {
                        mavenCentral()
                    }
                    
                    dependencies {
                        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
                    }
                    
                    tasks.withType<Test> {
                        useJUnitPlatform()
                        testLogging {
                            exceptionFormat = TestExceptionFormat.FULL
                            events = setOf(FAILED, SKIPPED)
                        }
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if testLogging was not configured")
    void test6() {
        //given kts script with configured java plugin
        var kts = """
                    import org.gradle.api.tasks.testing.logging.TestExceptionFormat
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                    
                    plugins {
                        java
                    }
                    
                    repositories {
                        mavenCentral()
                    }
                    
                    dependencies {
                        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
                    }
                    
                    tasks.withType<Test> {
                        useJUnitPlatform()
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if useJUnitPlatform was not set")
    void test7() {
        //given kts script with configured java plugin
        var kts = """
                    import org.gradle.api.tasks.testing.logging.TestExceptionFormat
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                    import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                    
                    plugins {
                        java
                    }
                    
                    repositories {
                        mavenCentral()
                    }
                    
                    dependencies {
                        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
                        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.2")
                    }
                    
                    tasks.withType<Test> {
                        testLogging {
                            exceptionFormat = TestExceptionFormat.FULL
                            events = setOf(FAILED, SKIPPED)
                        }
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    private Executable executeLesson1Task2(String kts) {
        return () -> new Lesson1(kts).task4();
    }
}
