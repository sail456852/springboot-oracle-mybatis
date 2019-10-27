package spring;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import spring.service.storage.StorageProperties;

/**
 *  http://localhost:8080/
 *  1. to disable JDBC connection for temporary testing
 //@SpringBootApplication
 @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
 */
//@MapperScan("spring.dao")
//@SpringBootApplication( scanBasePackages = "spring") // scanned in JavaConfig.java
@SpringBootApplication()
@EnableConfigurationProperties(StorageProperties.class)
// uncomment this to exclude JDBC part, use the following, @Requestmapping won't work  /test will fail
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.err.println(applicationContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Application.applicationContext = applicationContext;
    }
}

