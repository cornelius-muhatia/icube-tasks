# Game of Darts
Emulates the game of darts with 4 different score levels. Developed with Java 14.

## Requirements
- Java 14

## Build Project
1. Ensure java 14 is on the system path
1. Run the following command in the current directory:
    ```shell script
    ./mvnw clean install
    ```
## Running The Applications
You can run the application either using java or maven
### Maven
In the current directory run:
```shell script
./mvnw exec:java -Dexec.mainClass="com.cmuhatia.icube.question.one.Darts" -Dexec.args="-x=30 -y=40"
```
Replace -x and -y values with values you want to test.
### Java
In the current directory run:
```shell script
java -jar target/question-one-1.0.0.RELEASE.jar -x=5 -y=6

```