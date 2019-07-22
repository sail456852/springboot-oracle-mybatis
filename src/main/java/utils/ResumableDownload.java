package utils;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 7/22/2019<br/>
 * Time: 5:41 PM<br/>
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.net.*;

public class ResumableDownload {

    private static long transferDataAndGetBytesDownloaded(URLConnection downloadFileConnection, File outputFile) throws IOException {

        long bytesDownloaded = 0;
        try (InputStream is = downloadFileConnection.getInputStream(); OutputStream os = new FileOutputStream(outputFile, true)) {

            byte[] buffer = new byte[1024];

            int bytesCount;
            while ((bytesCount = is.read(buffer)) > 0) {
                os.write(buffer, 0, bytesCount);
                bytesDownloaded += bytesCount;
            }
        }
        return bytesDownloaded;
    }

    public static long downloadFileWithResume(String downloadUrl, String saveAsFileName) throws IOException, URISyntaxException {
        File outputFile = new File(saveAsFileName);
        URLConnection downloadFileConnection = DownloadUtil.addFileResumeFunctionality(downloadUrl, outputFile);
        return transferDataAndGetBytesDownloaded(downloadFileConnection, outputFile);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        downloadFileWithResume( "http://wangyuzhen.club/downloadTest.zip", "D:\\downloadTest.zip");
    }
}
