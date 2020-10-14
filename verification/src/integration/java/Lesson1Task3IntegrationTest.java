import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

public class Lesson1Task3IntegrationTest {

    @Test
    @DisplayName("Should pass if repositories ware configured and dependencies ware added")
    void test1() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        sourceCompatibility = JavaVersion.VERSION_11
                        targetCompatibility = JavaVersion.VERSION_11
                    }
                    
                    repositories {
                        maven("https://dl.google.com/android/maven2/")
                    }
                    
                    dependencies {
                        implementation("com.google.mlkit:face-detection:16.0.1")
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should pass if repositories ware configured and dependencies ware added but java plugin was not configured")
    void test2() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    repositories {
                        maven("https://dl.google.com/android/maven2/")
                    }
                    
                    dependencies {
                        implementation("com.google.mlkit:face-detection:16.0.1")
                        implementation("com.google.guava:guava:29.0-jre")
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if https://dl.google.com/android/maven2/ dependence was not add")
    void test3() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        sourceCompatibility = JavaVersion.VERSION_11
                        targetCompatibility = JavaVersion.VERSION_11
                    }
                    
                    repositories {
                        mavenCentral()
                    }
                    
                    dependencies {
                        implementation("com.google.mlkit:face-detection:16.0.1")
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if face-detection dependence was not add")
    void test4() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        sourceCompatibility = JavaVersion.VERSION_11
                        targetCompatibility = JavaVersion.VERSION_11
                    }
                    
                    repositories {
                        maven("https://dl.google.com/android/maven2/")
                    }
                    
                    dependencies {
                        implementation("com.google.guava:guava:29.0-jre")
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if no dependencies was configured")
    void test5() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        sourceCompatibility = JavaVersion.VERSION_11
                        targetCompatibility = JavaVersion.VERSION_11
                    }
                    
                    repositories {
                        maven("https://dl.google.com/android/maven2/")
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    private Executable executeLesson1Task2(String kts) {
        return () -> new Lesson1(kts).task3();
    }
}
