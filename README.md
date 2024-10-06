# G2A Shopping Tests

This repository contains a Java-based automated testing framework using Playwright and JUnit. The project tests product search and cart functionalities on the G2A website. It uses Docker for consistent and isolated testing environments.

## Project Structure

- **`src/main/java/com/example/pages/`**: Contains page object model classes representing different pages on the G2A website.
- **`src/test/java/com/example/tests/`**: Contains test cases implementing the testing logic.
- **`pom.xml`**: Maven configuration file with dependencies and build settings.
- **`Dockerfile`**: Configuration file for building a Docker image with the testing environment.

## Prerequisites


- **Java 11**
- **Maven**
- **Docker** (optional, for containerized testing)
- **Allure Commandline** (for generating reports)

## Setup with Docker

### Clone the Repository

```sh
git clone https://github.com/Pjoter94/G2A-recruitment-task.git
cd G2A-recruitment-task
```
### Build the Docker Image

```sh
docker build -t g2a_test .
```
### Run test and generate Allure report

```sh
docker run --rm -e product=Gothic2  g2a_test
```
## Running tests locally
To run the tests, use the following Maven command, specifying the product name as a system property:

```sh
mvn test -Dproduct="Gothic 2"
```
Generate and open the report:
```sh
mvn allure:serve
```
Generate single HTML report:
```sh
allure generate --single-file target/allure-results --clean 
```

