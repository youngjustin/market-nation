# market-nation

Code challenge for Market Nation.

## Spring Intializr

Project started from Spring Intializr (https://start.spring.io/) configured for Java 20.

Must set Gradle wrapper version in gradle-wrapper.properties to something newer like 8.1.1 to get Java 20 support.. seems like Intializr missed this version requirement :)

At any rate I'm setting Java compatibility in Gradle to 17 since this is all that the built-in Github actions supports at this time.
See https://github.com/actions/setup-java

Otherwise, you will see an error similar to

`BUG! exception in phase 'semantic analysis' in source unit '_BuildScript_' Unsupported class file major version 64`

See releases https://gradle.org/releases/ for a list of Gradle versions.

## Local Setup

I developed this on macOS with IntelliJ, so depending on your environment, YMMV.

### Brew

The package manager for macOS that you want and need. Install from https://brew.sh/

### OpenJDK

Install OpenJDK 20 using Brew:

`brew install openjdk@20`

Brew installs this into:
`/usr/local/Cellar/openjdk`

### IntelliJ

Install from https://www.jetbrains.com/idea/

Open IntelliJ and browse to the project.

Configure IntelliJ to use the newly installed JDK:
Cmd+Shift+G to go to hidden folders in the JDK setup window to locate the JDK directory:
`/usr/local/Cellar/openjdk/20/libexec/openjdk.jdk`

Go to IntelliJ menu and select Settings...
Expand Build, Exectuion, Deployment
Expand Build Tools
Select Gradle
Select `market-nation` in project list and set Gradle JVM to Project SDK 20

Otherwise you will see an error similar to
`Could not resolve org.springframework.boot:spring-boot-gradle-plugin:3.0.6`
or
`No matching variant of org.springframework.boot:spring-boot-gradle-plugin:3.0.6 was found. The consumer was configured to find a runtime of a library compatible with Java 11, packaged as a jar, and its dependencies declared externally, as well as attribute 'org.gradle.plugin.api-version' with value '7.6.1' but:`

## Development

### Code Formatting

This application uses Google Java Formatter (via the sherter Gradle plugin).

For IntelliJ, see additional instructions to add VM options required for code parsing:
https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config

To format your code before check-in, run

`./gradlew goJF`

The pipeline verifies code formatting by running

`./gradlew verGJF`

To get gradlew to work on command line, add to gradle.properties:
`org.gradle.java.home=/usr/local/Cellar/openjdk/20.0.1/libexec/openjdk.jdk/Contents/Home`
(make sure path is correct for current installed version)
or just use tasks within IntelliJ since gradle knows how to find toolchains in the IDE.

