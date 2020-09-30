import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.internal.DefaultBuildResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

abstract class Lesson {

    private final String ktsFileContent;
    private Path projectDir;
    private final GradleRunner gradleRunner = GradleRunner.create();

    Lesson() {
        this.ktsFileContent = defaultKtsFileContent();
    }

    Lesson(String ktsFileContent) {
        Objects.requireNonNull(ktsFileContent);
        this.ktsFileContent = ktsFileContent;
    }

    private String lessonName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    private String defaultKtsFileContent() {
        try {
            Path path = Paths.get("../%s/build.gradle.kts".formatted(lessonName()));
            return Files.readString(path);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    Lesson(String lessonName, String ktsFileContent){
        this.lessonName = lessonName;
    }

    void sampleJavaProject() {
        this.projectDir = copyProjectToTmpDir("setup1");
    }

    void sampleJavaProjectWithTests() {
        this.projectDir = copyProjectToTmpDir("setup2");
    }

    private Path copyProjectToTmpDir(String setup) {
        try {
            Path tmpDir = Files.createTempDirectory("gradle-workshop");
            System.out.printf("Created temp directory: %s%n", tmpDir.toString());
            Path tmpLessonDir = Files.createDirectory(tmpDir.resolve(lessonName()));
            Files.writeString(tmpLessonDir.resolve("build.gradle.kts"), ktsFileContent);
            Files.copy(Paths.get("../%s/settings.gradle.kts".formatted(lessonName())), tmpLessonDir.resolve("settings.gradle.kts"));
            copyRecursively(Paths.get("src/test/resources/%s/%s".formatted(lessonName(), setup)), tmpLessonDir);
            return tmpLessonDir;
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
