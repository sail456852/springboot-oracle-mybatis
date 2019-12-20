import beans.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.config.JavaConfig;
import spring.dao.PeopleDAO;
import spring.dto.People;

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
public class PeopleDAOTest {

    @Autowired
    private PeopleDAO peopleDAO;

    @Test
    public void listAll() {
        System.err.println("PeopleDAOTest.listAll ");
        List<People> people = peopleDAO.listAll();
        people.forEach(System.err::println);
    }
}