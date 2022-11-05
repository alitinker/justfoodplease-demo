# Just Food Please
### No BS. Just food.

A RESTful and pragmatic recipe API with JWT authentication.
Built with [Kotlin](https://kotlinlang.org/) + [Spring Boot](https://spring.io/projects/spring-boot).\
This is the public version of a personal project, built to assist with teaching concepts in contemporary API development.

----------------

### Prerequisites

- A MySQL database and a valid DB user
  - You can [install an instance](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) to your local machine if you don't already have one
  - Alternatively, use the included `docker-compose.yml` file to create one. You'll need [Docker](https://www.docker.com/products/docker-desktop/) installed to run it.
- You should be able to compile and run Kotlin.
  - [Intellij IDEA](https://www.jetbrains.com/idea/) and Android Studio provide support for Kotlin out of the box. 
  - You can also [install Kotlin tooling separately](https://kotlinlang.org/docs/command-line.html#sdkman)
- A Java SDK (JDK)
  - This project was built with [OpenJDK](https://openjdk.org/) 11
- [Maven](https://maven.apache.org/) for build automation

### Setup

- [Set the project SDK](https://www.jetbrains.com/help/idea/sdk.html#jdk) to the version you want to use
- Sync Maven dependencies
- Configure the database connection:
  - Change the DB url, username and password in the `application.properties` file to your DB credentials
- Set up a new run configuration in order to run the app, and select Spring Boot as the configuration template
- Run the app!
  - The API will be available on `http://localhost:8081`

### Migrations

The database migrations were created with Liquibase and were written using formatted SQL changelog files.
With `org.liquibase:liquibase-core` in the classpath (pom.xml), liquibase migrations will run automatically when the application starts up. There's nothing else you have to do!
It will create the database for you if it doesn't already exist. However, this feature should not be used in production, and is for demonstration purposes only.




