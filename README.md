Spring Boot Oracle Example
To run this example you will need to download and install the Oracle JDBC driver.
You can install the Oracle Jar into your local Maven repsository using this command: mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar


 mvn install:install-file -Dfile=ojdbc7-12.1.0.2.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
 
1. 最简单的MYSQL 依赖启动SPRINGBOOT MYSQL MYBATIS 查询远程MYSQL
~~~xml
    <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.0</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
~~~

~~~properties
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:fakeSchema://fakeurl:3306/fakeDatabase
spring.datasource.username=root
spring.datasource.password=FakePassword
~~~


        
