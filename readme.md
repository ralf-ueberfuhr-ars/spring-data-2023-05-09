# Getting Started

## First Steps

### Launch the application

Run the `CustomerServiceApplication` class directly or use Maven:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

or build the JAR and run

```bash
mvn package
java -jar -Dspring.profiles.active=dev target/customer-service-spring-data-0.0.1-SNAPSHOT.jar
```

## Liquibase

We can run the application locally using the `liquibase` profile. In contrast to the `dev` profile,
this will disable automatic schema generation by Hibernate and enable Liquibase ChangeLog Activation.
This will connect to another local database, so we have both databases, one for each profile.

### Changelog Generation

We can use the `liquibase-maven-plugin:generateChangeLog` goal to initially generate a sample changelog
from the database that was created by Hibernate. Therefore, we need to activate the `liquibase` Maven profile.

```bash
mvn liquibase:generateChangeLog -Pliquibase
```

## Further

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#using.devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

