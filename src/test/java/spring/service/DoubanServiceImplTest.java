package spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.config.JavaConfig;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 7/4/2019<br/>
 * Time: 3:33 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class DoubanServiceImplTest {

    @Autowired
    private DoubanService doubanService;

    @Test
    public void getNextUrlKey() {
        doubanService.addUrlOld("testUrl");
    }
}