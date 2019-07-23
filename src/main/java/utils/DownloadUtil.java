package utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 7/22/2019<br/>
 * Time: 1:54 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class DownloadUtil {
    static BlockingQueue<Runnable> runnables = new ArrayBlockingQueue<Runnable>(1024);
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, runnables);

    public static void main(String[] args) throws IOException, URISyntaxException {
        long start = System.currentTimeMillis();
        singleThreadIODownloader("D:\\downloadTest1.zip", "http://wangyuzhen.club/downloadTest.zip");
        long middle = System.currentTimeMillis();
        nioDownloaderResumable("D:\\downloadTest2.zip", "http://wangyuzhen.club/downloadTest.zip");
        long end = System.currentTimeMillis();
        System.err.println(middle - start); // sing first 649 , nio first 393
        System.err.println(end - middle); // nio second 160, sing second 135
    }

    public static URLConnection addFileResumeFunctionality(String downloadUrl, File outputFile) throws IOException, URISyntaxException, ProtocolException, ProtocolException {
        long existingFileSize = 0L;
        URLConnection downloadFileConnection = new URI(downloadUrl).toURL().openConnection();

        if (outputFile.exists() && downloadFileConnection instanceof HttpURLConnection) {
            HttpURLConnection httpFileConnection = (HttpURLConnection) downloadFileConnection;
            HttpURLConnection tmpFileConn = (HttpURLConnection) downloadFileConnection;
            tmpFileConn.setRequestMethod("HEAD");
            long fileLength = tmpFileConn.getContentLengthLong();
            existingFileSize = outputFile.length();

            if (existingFileSize < fileLength) {
                httpFileConnection.setRequestProperty("Range", "bytes=" + existingFileSize + "-" + fileLength);
            } else {
                throw new IOException("File Download already completed.");
            }
        }
        return downloadFileConnection;
    }

    public static void downloadWithApacheCommons(String url, String localFilename) {

        int CONNECT_TIMEOUT = 10000;
        int READ_TIMEOUT = 10000;
        try {
            FileUtils.copyURLToFile(new URL(url), new File(localFilename), CONNECT_TIMEOUT, READ_TIMEOUT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * the data can be transferred directly from the filesystem cache to our file without copying any bytes into the application memory.
     *
     * @param fileName
     * @param url
     * @return
     * @throws IOException
     */
    public static boolean nioDownloaderResumable(String fileName, String url) throws IOException, URISyntaxException {
        Future<Boolean> future = executor.submit(() -> {
            File outputFile = new File(fileName);
            URLConnection downloadFileConnection = DownloadUtil.addFileResumeFunctionality(url, outputFile);
            try (ReadableByteChannel readableByteChannel = Channels.newChannel(downloadFileConnection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                 FileChannel fileChannel = fileOutputStream.getChannel()) {
                 fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                 fileOutputStream.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        // future.isDone()
        return true;
    }

    public static void singleThreadIODownloader(String fileName, String url) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("DownloadUtil.main " + e);
        }
    }

    private class Downloader implements Runnable {
        private final URL url;

        public Downloader(URL url) {
            this.url = url;
        }

        private String readAll(Reader reader) throws IOException {
            StringBuilder builder = new StringBuilder();
            int read = 0;
            while ((read = reader.read()) != -1) {
                builder.append((char) read);
            }
            return builder.toString();
        }

        @Override
        public void run() {
            try {
                Reader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String result = readAll(reader);
                    System.out.printf("Read %d characters from %s\n", result.length(), url);
                } finally {
                    if (reader != null)
                        reader.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Multi-thread web character downloader
     *
     * @throws MalformedURLException
     */
    public void runIt() throws MalformedURLException {
        executor.submit(new Downloader(new URL("http://www.baidu.com")));
        executor.submit(new Downloader(new URL("http://www.douban.com")));
//        executor.shutdown();
    }

}
