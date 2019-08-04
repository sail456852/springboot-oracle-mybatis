package spring.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.response.CommonCode;
import spring.response.ResponseResult;
import utils.DownloadUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/28/2019<br/>
 * Time: 5:10 PM<br/>
 * To change this template use File | Settings | File Templates.
 * http://localhost:8080
 */
@Controller
public class PublicController {

    /**
     * use post to download something, multi-threading downloading
     * @param request
     * @param response
     * @param url http://wangyuzhen.club:8080/offlineDownload/http://wangyuzhen.club/downloadTest.zip
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    @ResponseBody
    @RequestMapping(value = "/offlineDownload")
    public ResponseResult offlineDownload(HttpServletRequest request,
                                          HttpServletResponse response, @RequestParam String url ) throws IOException, URISyntaxException {
//        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/"); // web app directory
        String osName = System.getProperty("os.name");
        System.err.println("url = " + url);
        String fileName = FilenameUtils.getName(url);
        String storeFileName = "/tmp/" + fileName;
        if (osName.contains("Windows")) {
            storeFileName = "D:\\" + fileName;
        }
        System.err.println("storeFileName = " + storeFileName);
        boolean b = DownloadUtil.nioDownloaderResumable(storeFileName, url);
        return  b ? new ResponseResult(CommonCode.SUCCESS) : new ResponseResult(CommonCode.FAIL);
    }
}
