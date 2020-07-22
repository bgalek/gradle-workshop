import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Lesson1 {

    @Test
    @DisplayName("should declare java plugin")
    void task1() {
        //given
        BuildResult result = buildTask("components");

        //expect
        assertTrue(result.getTasks().stream().map(BuildTask::getPath).anyMatch(it -> it.contains(":components")), "required task not found");
        assertEquals(SUCCESS, result.task(":components").getOutcome(), "Build was not successful");
        assertTrue(result.getOutput().contains("classes dir: build/classes/java/main"), "No Java plugin detected");
    }

    @Test
    @DisplayName("should define target and source for java")
    void task2() {
        //given
        BuildResult result = buildTask("build");

        //expect
        assertTrue(false);
    }

    @Test
    @DisplayName("should declare java plugin")
    void task3() {
        //given
        BuildResult result = buildTask("dependencies");

        //expect
        assertTrue(result.getTasks().stream().map(BuildTask::getPath).anyMatch(it -> it.contains(":dependencies")), "required task not found");
        assertTrue(result.getOutput().contains("com.google.mlkit:face-detection:16.0.1"), "mlkit face-detection dependency not found");
        assertFalse(result.getOutput().contains("com.google.mlkit:face-detection:16.0.1 FAILED"), "could not find face-detection jar");
        assertEquals(SUCCESS, requireNonNull(result.task(":dependencies")).getOutcome());
    }

    private BuildResult buildTask(String taskName) {
        return GradleRunner.create()
                .withProjectDir(Paths.get("../lesson1").toFile())
                .withArguments(taskName)
                .build();
    }
}