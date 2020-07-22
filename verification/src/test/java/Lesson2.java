import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Lesson2 {

    @Test
    @DisplayName("should build")
    void testNotDefaultMavenRepositoryAdded() {
        //given
        BuildResult result = GradleRunner.create()
                .withProjectDir(Paths.get("../lesson2").toFile())
                .withArguments("build")
                .build();

        //expect
        assertEquals(SUCCESS, requireNonNull(result.task(":build")).getOutcome());
    }
}