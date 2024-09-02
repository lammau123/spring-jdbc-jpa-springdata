# Spring JDBC, JPA and Spring Data

## Project Requirements

Java Development Kit (JDK): JDK 17.
Spring Boot: Use Spring Boot to simplify the setup and development process.
Spring JDBC: For database interaction.
H2 Database: An in-memory database for development and testing.
Maven: For project build and dependency management.
IDE: An Integrated Development Environment eclipse.

## Spring JDBC
The following diagram illustrates the Spring JDBC architecture implemented in this project
![Spring jdbc](/assets/images/jdbc.png)

- JdbcJdbcApplication.java: The main application implements CommandLineRunner to execute Spring JDBC.
- Course.java: Represents the model.
- CourseJdbcRepository.java: Interacts with the H2 database using Spring JDBC template.

## Spring JPA
#### The following diagram illustrates the Spring JPA architecture implemented in this project
![Spring jpa](/assets/images/jpa.png)

- JdbcJpaApplication.java: The main application implements CommandLineRunner to execute Spring JPA.
- Course.java: Represents the model.
- CourseJpaRepository.java: Interacts with the H2 database using Spring JPA template.

## Spring Data
#### The following diagram illustrates the Spring Data architecture implemented in this project
![Spring Data](/assets/images/springdata.png)

- JdbcJpaApplication.java: The main application implements CommandLineRunner to execute Spring Data.
- Course.java: Represents the model.
- CourseSpringDataRepository.java: Interacts with the H2 database using Spring Data template.