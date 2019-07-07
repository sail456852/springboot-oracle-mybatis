import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.config.JavaConfig;
import spring.dao.CommentRedisRepository;
import spring.dao.StudentRedisRepository;
import spring.dto.Comment;
import spring.dto.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/18/2019<br/>
 * Time: 7:12 PM<br/>
 * To change this template use File | Settings | File Templates.
 * http://localhost:8080/index.html
 * http://localhost:8080/index2.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class RedisRepoTest {

    @Autowired
    private StudentRedisRepository studentRepository;

    @Autowired
    private CommentRedisRepository commentRedisRepository;

    @Test
    public void save() {
//        Student student = new Student(
//                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
//        studentRepository.save(student);
        Comment comment = new Comment("testUrl", new ArrayList<String>(){{
            add("testComment1");
            add("testComment2");
        }});

        Comment comment2 = new Comment("testUrl2", new ArrayList<String>(){{
            add("testComment3");
            add("testComment4");
        }});
        Stream<Comment> stream = Stream.of(comment, comment2);
        List<Comment> list = stream.collect(Collectors.toList());
        commentRedisRepository.saveAll(list);
        studentRepository.findAll().forEach(System.out::println);
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

    @Test
    public void printBean() {
        Student engStudent = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
        Student medStudent = new Student(
                "Med2015001", "Gareth Houston", Student.Gender.MALE, 2);
        ArrayList<Student> students = new ArrayList<>();
        students.add(engStudent);
        students.add(medStudent);
        students.forEach(System.err::println);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Failed  won't work
     */
    @Test
    public void redisTemplateSet() {

        String yuzhenTest = "yuzhenTest";
        redisTemplate.opsForValue().set(yuzhenTest, "newValue");
        Object o = redisTemplate.opsForValue().get(yuzhenTest);
        System.err.println("o = " + o);
    }

}