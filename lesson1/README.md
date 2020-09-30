# Lesson 1

## Task1

Your first task is to add java plugin to your gradle build using kotlin dsl.

build groovy version:
```     
plugins {
    id 'java'
}
```

docs: <a href="https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block">
gradle documentation about plugins

## Task 2
explore java plugin configuration

## Task 3:

Add library:

group: `com.google.mlkit`
artifactId: `face-detection`
version: `16.0.1`

from google maven: `https://dl.google.com/android/maven2/`

## Task 4

- Add test dependencies (junit 5)
- Enable junit (useJUnitPlatform())
- Enable full exception format using `TestExceptionFormat`
- Show only `FAILED` or `SKIPPED` test results using `TestLogEvent`
