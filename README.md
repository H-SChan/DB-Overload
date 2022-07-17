# DB-Overload
데이터베이스 쿼리 속도 테스트

환경
> Spring Boot 2.7.1
> 
> JAVA 17
> 
> Gradle
> 
> Spring Data JPA 2.7.1
> 
> Lombok 1.18.24
> 
> MySQL 8.0.23

테스트 환경
> Junit

<br>

application.properties
```properties
spring.datasource.driver-class-name
spring.datasource.username
spring.datasource.password
spring.datasource.url
spring.jpa.hibernate.ddl-auto
spring.jpa.show-sql
spring.jpa.properties.hibernate.format_sql
```

<br>

build.gradle -> dependencies
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'mysql:mysql-connector-java'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```
