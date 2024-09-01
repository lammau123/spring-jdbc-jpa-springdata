# Spring JDBC, JPA and Spring Data

## Project Requirements

Java Development Kit (JDK): JDK 17.
Spring Boot: Use Spring Boot to simplify the setup and development process.
Spring JDBC: For database interaction.
H2 Database: An in-memory database for development and testing.
Maven: For project build and dependency management.
IDE: An Integrated Development Environment eclipse.

## Spring jdbc
#### Below is the Spring jdbc Architecture which is implemented in this project

![Spring jdbc](/assets/images/jdbc.png)

1. JdbcJpaApplication.java the main application implements CommandLineRunner to execute Spring jdbc
2. Course.java preresents as model
3. CourseJdbcRepository.java is used to interact with H2 database using SPring jdbc template

