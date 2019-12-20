//package webdriver;
//
//import org.junit.Before;
//import org.junit.beans.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * Created by IntelliJ IDEA.<br/>
// * User: yz<br/dfsdf
// * Date: 8/11/2019<br/>
// * Time: 3:47 PM<br/>
// * To change this template use File | Settings | File Templates.
// * <bold>  just run chromedriver.exe in bash.exe or cmd.exe in IDEA </bold>
// *  it's really cool
// *
// */
//public class ChromiumDemo<test> {
//
//    private WebDriver remoteWebDriver;
//
//    /**
//     * RUN CHROMEDRIVER.exe BEFORE this !!!
//     * @throws MalformedURLException
//     */
//    @Before
//    public void connectToRemoteWebDriver() throws MalformedURLException {
//        remoteWebDriver = new RemoteWebDriver(new URL("http://127.0.0.1:9515"), DesiredCapabilities.chrome());
//    }
//
//    /**
//     * PURE SELENIUM DEMO
//     */
//    @beans.Test
//    public void testGoogleSearch() {
//        try {
//            // Optional, if not specified, WebDriver will search your path for chromedriver.
//            System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");
//            remoteWebDriver.get("http://www.google.com/xhtml");
//            Thread.sleep(5000);  // Let the user actually see something!
//            WebElement searchBox = remoteWebDriver.findElement(By.name("q"));
//            searchBox.sendKeys("ChromeDriver");
//            searchBox.submit();
//            Thread.sleep(5000);  // Let the user actually see something!
//            remoteWebDriver.quit();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @beans.Test
//    public void testJDCardSelectAllAndOrder() {
//
//    }
//
//
//}
