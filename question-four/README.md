# IOUs Manager
Manages IOUs of roommates who have trouble remembering who owes who and how much. Technologies used to create the application include:
1. Java 14
1. [Spring Boot Framework](https://spring.io/projects/spring-boot)
1. [Maven](https://maven.apache.org/)
1. [H2 Database](https://www.h2database.com/html/main.html)

## Requirements
- Java 14

## Build Project
1. Ensure java 14 is on the system path
1. For linux systems change "./mvnw" file permission:
    ```shell script
    chmod +x ./mvnw
    ```
1. Inside current folder run the following command:
    ```shell script
    ./mvnw clean install
     ```
1. For docker image creation, run the following command after the previous step:
    ```shell script
    docker build --tag=question-four .
    ```
   You can also pull the image from the docker repository
   ```shell script
    docker pull jdialogc/public:cmuhatia-icube-question4
   ```
## Running the application
Here are a few ways of running the application:
1. Inside current(question-four) project folder run:
    ```shell script
    ./mvnw spring-boot:run
    ```
1. Using java, run the following command:
    ```shell script
    java -jar target/question-four-1.0.0.RELEASE.jar
    ```
1. As for docker run:
    ```shell script
    docker run -p 9090:9090 question-four 
    ```
After running the application the API documentation can be accessed at http://localhost:9090/swagger-ui.html and 
the embedded database at http://localhost:9090/h2-database. Database credentials:
- Username: root
- Password: test
- JDBC URL: jdbc:h2:~/Documents/iou-test-db

> A live instance of the application can also be found at: [http://34.69.229.194:9090/swagger-ui.html](http://34.69.229.194:9090/swagger-ui.html)

**Note:** 
> The application runs on port 9090. If you want to use a different port add the following environment variable before running the application: 
    ```
    export SERVER_PORT=8080
    ```
replace "8080" with your preferred port. 

