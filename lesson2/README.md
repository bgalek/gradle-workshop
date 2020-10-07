# Lesson 2

## Task 1:

Create two modules `insurrence` and `credit`. Add them to lesson2 project.

## Task 2:

Create module `account` and add it as a dependency to `credit`

Expected dependency tree: 

`lesson2` -> `insurrence` AND `lesson2` -> `credit` -> `account`

## Task 3:

Explore differences in `implementation`, `api`, `compile` and `runtimeOnly` dependencies types.

```
compileOnly — for dependencies that are necessary to compile your production code but shouldn’t be part of the runtime classpath

implementation (supersedes compile) — used for compilation and runtime

runtimeOnly (supersedes runtime) — only used at runtime, not for compilation

testCompileOnly — same as compileOnly except it’s for the tests

testImplementation — test equivalent of implementation

testRuntimeOnly — test equivalent of runtimeOnly
```

https://docs.gradle.org/current/userguide/building_java_projects.html#sec:java_dependency_management_overview
