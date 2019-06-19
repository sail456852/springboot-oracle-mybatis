import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.config.JavaConfig;
import spring.dao.StudentRedisRepository;
import spring.dto.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/18/2019<br/>
 * Time: 7:12 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class RedisRepoTest {

    @Autowired
    private StudentRedisRepository studentRepository;

    @Test
    public void save() {
        Student student = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
        studentRepository.save(student);
    }


    @Test
    public void saveList() {

        Student engStudent = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
        Student medStudent = new Student(
                "Med2015001", "Gareth Houston", Student.Gender.MALE, 2);
        studentRepository.save(engStudent);
        studentRepository.save(medStudent);

    }

    @Test
    public void get() {
        Student retrievedStudent =
                studentRepository.findById("Eng2015001").get();
        System.err.println("retrievedStudent = " + retrievedStudent);

        retrievedStudent.setName("Richard Watson");
//        studentRepository.save(student);
//        studentRepository.deleteById(student.getId())

    }
    
    @Test
    public void getAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        students.forEach(System.err::println);
    }

}