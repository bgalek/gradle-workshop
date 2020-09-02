import org.gradle.testkit.runner.BuildResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Objects.requireNonNull;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Lesson2 extends Lesson {

    Lesson2() {
        super("lesson2");
    }

    @Test
    @DisplayName("should build")
    void testNotDefaultMavenRepositoryAdded() {
        //given
        BuildResult result = executeTask("build");

        //expect
        assertEquals(SUCCESS, requireNonNull(result.task(":build")).getOutcome());
    }
}