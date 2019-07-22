package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 7/22/2019<br/>
 * Time: 1:54 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class LinuxUtil {

    public static void main(String args[]) {
        String s;
        Process p;
        try {
//            p = Runtime.getRuntime().exec("ls -aF");  // UNIX
            p = Runtime.getRuntime().exec("dir -a"); // WIN
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        }
    }
}
