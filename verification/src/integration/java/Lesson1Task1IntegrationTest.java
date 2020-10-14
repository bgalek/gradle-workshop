import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

public class Lesson1Task1IntegrationTest {

    @Test
    @DisplayName("Should pass if java plugin was applied")
    void test1() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task1(kts));
    }

    @Test
    @DisplayName("Should pass if application plugin (which expands java plugin) was applied")
    void test2() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        application
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(executeLesson1Task1(kts));
    }

    @Test
    @DisplayName("Should not pass if kotlin plugin was applied instead of java plugin")
    void test3() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        kotlin
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task1(kts));
    }

    @Test
    @DisplayName("Should not pass if no plugin was applied")
    void test4() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task1(kts));
    }

    @Test
    @DisplayName("Should not pass if kts script is blank")
    void test5() {
        //given kts script with configured java plugin
        var kts = """
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, executeLesson1Task1(kts));
    }

    private Executable executeLesson1Task1(String kts) {
        return () -> new Lesson1(kts).task1();
    }
}
