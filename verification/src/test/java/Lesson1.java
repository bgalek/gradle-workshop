import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;
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
        Path projectDir = copyProjectToTmpDir();
        BuildResult result = buildTask(projectDir, "components");

        //expect
        assertTrue(result.getTasks().stream().map(BuildTask::getPath).anyMatch(it -> it.contains(":components")), "required task not found");
        assertEquals(SUCCESS, result.task(":components").getOutcome(), "Build was not successful");
        assertTrue(result.getOutput().contains("classes dir: build" + separator + "classes" + separator + "java" + separator + "main"), "No Java plugin detected");
    }

    @Test
    @DisplayName("should define target and source for java")
    void task2() {
        //given
        Path projectDir = copyProjectToTmpDir();
        BuildResult result = buildTask(projectDir, "build");

        //expect
        assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"), "Build failed");
        assertTrue(result.getOutput().contains("Compiler arguments: -source 11 -target 11"), "source and target compatibility must be set to 11 JVM version.");
    }

    @Test
    @DisplayName("should declare java plugin")
    void task3() {
        //given
        Path projectDir = copyProjectToTmpDir();
        BuildResult result = buildTask(projectDir, "dependencies");

        //expect
        assertTrue(result.getTasks().stream().map(BuildTask::getPath).anyMatch(it -> it.contains(":dependencies")), "required task not found");
        assertTrue(result.getOutput().contains("com.google.mlkit:face-detection:16.0.1"), "mlkit face-detection dependency not found");
        assertFalse(result.getOutput().contains("com.google.mlkit:face-detection:16.0.1 FAILED"), "could not find face-detection jar");
        assertEquals(SUCCESS, requireNonNull(result.task(":dependencies")).getOutcome());
    }

    private Path copyProjectToTmpDir() {
        try {
            Path tmpDir = Files.createTempDirectory("gradle-workshop");
            Path lessonDir = Files.createDirectory(tmpDir.resolve("lesson1"));
            Path srcDir = Files.createDirectory(lessonDir.resolve("src"));
            Path srcMainDir = Files.createDirectory(srcDir.resolve("main"));
            Path srcMainJavaDir = Files.createDirectory(srcMainDir.resolve("java"));
            Files.copy(Paths.get("../lesson1/build.gradle.kts"), lessonDir.resolve("build.gradle.kts"));
            Files.copy(Paths.get("../lesson1/settings.gradle.kts"), lessonDir.resolve("settings.gradle.kts"));
            Files.copy(Paths.get("src/test/resources/lesson1/src/main/java/HelloWorld.java"), srcMainJavaDir.resolve("HelloWorld.java"));
            return lessonDir;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BuildResult buildTask(Path projectDir, String taskName) {
        return GradleRunner.create()
                .withProjectDir(projectDir.toFile())
                .withArguments("clean", taskName, "--debug")
                .withDebug(true)
                .build();
    }
}