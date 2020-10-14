import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

public class Lesson1Task2IntegrationTest {

    @Test
    @DisplayName("Should pass if sourceCompatibility and targetCompatibility ware set")
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
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task2(kts));
    }

    @Test
    @Disabled("TODO fix Lesson 1 Task 2 verification")
    @DisplayName("Should not pass if only sourceCompatibility was set")
    void test2() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        sourceCompatibility = JavaVersion.VERSION_11
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if only targetCompatibility was set")
    void test3() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        targetCompatibility = JavaVersion.VERSION_11
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    @Test
    @DisplayName("Should not pass if incorrect java version was set")
    void test4() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                    
                    configure<JavaPluginConvention> {
                        sourceCompatibility = JavaVersion.VERSION_9
                        targetCompatibility = JavaVersion.VERSION_9
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task2(kts));
    }

    private Executable executeLesson1Task2(String kts) {
        return () -> new Lesson1(kts).task2();
    }
}
