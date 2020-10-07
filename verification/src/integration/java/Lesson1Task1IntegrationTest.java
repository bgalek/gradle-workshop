import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

public class Lesson1Task1IntegrationTest {

    @Test
    @DisplayName("test1")
    void test1() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        java
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(() -> new Lesson1(kts).task1());
    }

    @Test
    @DisplayName("test2")
    void test2() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        application
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertDoesNotThrow(() -> new Lesson1(kts).task1());
    }

    @Test
    @DisplayName("test3")
    void test3() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                        kotlin
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, () -> new Lesson1(kts).task1());
    }

    @Test
    @DisplayName("test4")
    void test4() {
        //given kts script with configured java plugin
        var kts = """
                    plugins {
                    }
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, () -> new Lesson1(kts).task1());
    }

    @Test
    @DisplayName("test5")
    void test5() {
        //given kts script with configured java plugin
        var kts = """
                """;

        //expect task1 passes for given kts
        Assertions.assertThrows(AssertionFailedError.class, () -> new Lesson1(kts).task1());
    }
}
