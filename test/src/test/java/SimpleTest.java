import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Lesson1;

public class SimpleTest {

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

}
