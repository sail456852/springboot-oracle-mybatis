package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Runtime.getRuntime;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 7/22/2019<br/>
 * Time: 1:54 PM<br/>
 * To change this template use File | Settings | File Templates.
 * @author Eugene_Wang
 */
public class LinuxUtil {

    public static void main(String args[]) {
        String s;
        Process p;
        try {
            // UNIX
//            p = Runtime.getRuntime().exec("ls -aF");
            // WIN
            p = getRuntime().exec("dir -a");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null) {
                System.out.println("line: " + s);
            }
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        }
    }
}
