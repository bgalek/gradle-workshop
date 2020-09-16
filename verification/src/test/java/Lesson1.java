import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.io.File.separator;
import static java.util.Objects.requireNonNull;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Lesson1 extends Lesson {

    Lesson1() {
        super("lesson1");
    }

    @Test
    @DisplayName("should declare java plugin")
    void task1() {
        //given
        sampleJavaProject();

        //when
        BuildResult result = executeTask("components");

        //then
        assertTrue(result.getTasks().stream().map(BuildTask::getPath).anyMatch(it -> it.contains(":components")), "required task not found");
        assertEquals(SUCCESS, result.task(":components").getOutcome(), "Build was not successful");
        assertTrue(result.getOutput().contains("classes dir: build" + separator + "classes" + separator + "java" + separator + "main"), "No Java plugin detected");
    }

    @Test
    @DisplayName("should define target and source for java")
    void task2() {
        //given
        sampleJavaProject();

        //when
        BuildResult result = executeTask("build");

        //then
        assertTrue(result.getOutput().contains("BUILD SUCCESSFUL"), "Gradle build failed");
        assertTrue(result.getOutput().contains("Compiler arguments: -source 11 -target 11"), "source and target compatibility must be set to 11 JVM version.");
    }

    @Test
    @DisplayName("should declare some external dependencies")
    void task3() {
        //given
        sampleJavaProject();

        //when
        BuildResult result = executeTask("dependencies");

        //then
        assertTrue(result.getTasks().stream().map(BuildTask::getPath).anyMatch(it -> it.contains(":dependencies")), "required dependencies task not found");
        assertTrue(result.getOutput().contains("com.google.mlkit:face-detection:16.0.1"), "mlkit face-detection dependency not found");
        assertFalse(result.getOutput().contains("com.google.mlkit:face-detection:16.0.1 FAILED"), "could not find face-detection jar");
        assertEquals(SUCCESS, requireNonNull(result.task(":dependencies")).getOutcome());
    }

    @Test
    @DisplayName("should configure junit")
    void task4() {
        //given a java project with tests
        sampleJavaProjectWithTests();

        //when execute dependencies task
        BuildResult result = executeTask("dependencies");

        //then junit dependencies are declared
        assertTrue(result.getTasks().stream().map(BuildTask::getPath).anyMatch(it -> it.contains(":dependencies")), "required dependencies task not found");
        assertTrue(result.getOutput().contains("org.junit.jupiter:junit-jupiter-api:5"), "jupiter junit dependency not found");

        //when execute test task
        BuildResult result2 = executeTask("test");

        //then all tests pass
        assertTrue(result2.getOutput().contains("Gradle Test Run :test PASSED"), "Tests not passed");
        assertTrue(result2.getOutput().contains("Creating Gradle Test Executor"), "JUnitPlatform was not used");
    }

}