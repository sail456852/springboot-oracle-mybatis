package utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by IntelliJ IDEA.<br/>
 * User: Eugene_Wang<br/>
 * Date: 12/30/2019<br/>
 * Time: 3:13 PM<br/>
 * To change this template use File | Settings | File Templates.
 *
 * 		<dependency>
 * 			<groupId>junit</groupId>
 * 			<artifactId>junit</artifactId>
 * 			<version>4.12</version>
 * 			<scope>test</scope>
 * 		</dependency>
 *
 * 		<dependency>
 * 			<groupId>org.json</groupId>
 * 			<artifactId>json</artifactId>
 * 			<version>20190722</version>
 * 		</dependency>
 */
public class JSONUtilTest {

    @Test
    public void testFilterField() throws URISyntaxException {
        String fileName = "filter.json";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            String collect = stream.collect(Collectors.joining());
            JSONObject jsonObj = new JSONObject(collect);
            Object name = jsonObj.get("name");
            // convert to string is a must, or else not found field error
            String s = name.toString();
            JSONObject jsonObject = new JSONObject(s);
            Object type = jsonObject.get("type");
            System.err.println("type = " + type);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


}