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
#Mybatis print sql with parameters
1. replace BaseJdbcLogger.java in mybatis.jar with main/java/same_path_to_base_jdbc_logger
rewrite debug function with the demo ( for case you can't download maven jar (offline))
2. log4jdbc maven method (online)
3. springboot, with mybatis-plus method (online)
4. rewrite mybatis interceptor method (offline)

#BUG list 
1.xml if test bug
~~~xml
   <select id="getPeopleByAge" parameterType="java.lang.Integer" resultType="spring.dto.People">
        SELECT * FROM people
        <where>
            <if test="age != null">
                age = #{age}
            </if>
        </where>
~~~
> org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'age' in 'class java.lang.Integer'

## remove if test tag, works fine, tell me why?

## to server static content you need to have resources/public 
classpath means resources fold path don't put public along with main folder

