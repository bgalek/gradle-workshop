import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.internal.DefaultBuildResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

abstract class Lesson {

    private final String lessonName;
    private final Path projectDir;
    private final GradleRunner gradleRunner = GradleRunner.create();

    Lesson(String lessonName) {
        this.lessonName = lessonName;
        this.projectDir = copyProjectToTmpDir();
    }

    Path copyProjectToTmpDir() {
        try {
            Path tmpDir = Files.createTempDirectory("gradle-workshop");
            System.out.printf("Created temp directory: %s%n", tmpDir.toString());
            Path lessonDir = Files.createDirectory(tmpDir.resolve(lessonName));
            Files.copy(Paths.get("../%s/build.gradle.kts".formatted(lessonName)), lessonDir.resolve("build.gradle.kts"));
            Files.copy(Paths.get("../%s/settings.gradle.kts".formatted(lessonName)), lessonDir.resolve("settings.gradle.kts"));
            copyRecursively(Paths.get("src/test/resources/%s".formatted(lessonName)), lessonDir);
            return lessonDir;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copyRecursively(Path src, Path dest) {
        try {
            Files.walk(src).forEach(file -> copy(src, file, dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copy(Path root, Path src, Path dest) {
        try {
            if (root.getNameCount() == src.getNameCount()) return;
            Files.copy(src, dest.resolve(src.subpath(root.getNameCount(), src.getNameCount())), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BuildResult executeTask(String taskName) {
        try {
            return gradleRunner
                    .withProjectDir(projectDir.toFile())
                    .withArguments("clean", taskName, "--debug")
                    .withDebug(true)
                    .build();

        } catch (Exception e) {
            return new DefaultBuildResult("", List.of());
        }
    }
}
