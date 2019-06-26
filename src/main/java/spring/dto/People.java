package spring.dto;

import java.io.Serializable;

/**
 *  FOR MYSQL DEMO !
 */
public class People implements Serializable{

    private String name;
    private String sex;
    private Integer age;

    People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
