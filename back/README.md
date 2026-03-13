# mdd-api

this project is based on spring boot 3.5.6, maven plugin and required JAVA 21

## Start the project

### Dependencies
org.springframework.boot:
spring-boot-starter-data-jdbc
spring-boot-starter-data-jpa
spring-boot-starter-web
spring-boot-devtools
spring-boot-starter-tomcat
spring-boot-starter-security
spring-boot-starter-validation

org.mariadb.jdbc:
mariadb-java-client

org.projectlombok:
lombok

io.jsonwebtoken:
jjwt-api
jjwt-impl
jjwt-jackson

### Run project
mvn clean install
mvn spring-boot:run

### Documentation project OpenApi
org.springdoc
springdoc-openapi-starter-webmvc-ui
version: 2.8.15
link documentation swagger: http://localhost:8080/swagger-ui.html

## Folder created
config -> handling openApiConfig
controller
dto
exception -> handling exception
model
repository
security -> handlingJWT
service