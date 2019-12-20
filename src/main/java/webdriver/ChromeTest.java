//package webdriver;
//
//import junit.framework.TestCase;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.junit.runners.BlockJUnit4ClassRunner;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//
///**
// * Created by IntelliJ IDEA.<br/>
// * User: yz<br/>
// * Date: 8/11/2019<br/>
// * Time: 4:13 PM<br/>
// * To change this template use File | Settings | File Templates.
// * Terminal:
// *
// * $ ./chromedriver
// * Started ChromeDriver
// * port=9515
// * version=76.0.3809.68
// * Java:
// *
// * WebDriver driver = new RemoteWebDriver("http://127.0.0.1:9515", DesiredCapabilities.chrome());
// * driver.get("http://www.google.com");
// */
//@RunWith(BlockJUnit4ClassRunner.class)
//public class ChromeTest extends TestCase {
//
//    private static ChromeDriverService service;
//    private WebDriver driver;
//
//    @BeforeClass
//    public static void createAndStartService() throws IOException {
//        service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File("C:\\Windows\\chromedriver.exe"))
//                .usingAnyFreePort()
//                .build();
//        service.start();
//    }
//
//    @AfterClass
//    public static void createAndStopService() {
//        service.stop();
//    }
//
//    @Before
//    public void createDriver() {
//        driver = new RemoteWebDriver(service.getUrl(),
//                DesiredCapabilities.chrome());
//    }
//
//    @After
//    public void quitDriver() {
//        driver.quit();
//    }
//
//    @beans.Test
//    public void testGoogleSearch() {
//        driver.get("http://www.google.com");
//        // rest of the test...
//        Scanner scanner = new Scanner(System.in);
//    }
//}
