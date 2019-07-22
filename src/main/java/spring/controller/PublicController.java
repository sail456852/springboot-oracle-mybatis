package spring.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ResponseBody
    @RequestMapping("/offlineDowload/{url}")
    public ResponseResult offlineDownload(HttpServletRequest request,
                                          HttpServletResponse response, @PathVariable String url ) throws IOException, URISyntaxException {
//        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/"); // web app directory
        String fileName = FilenameUtils.getName(url);
        String storeFileName = "/tmp/" + fileName;
        boolean b = DownloadUtil.nioDownloaderResumable(storeFileName, url);
        return  b ? new ResponseResult(CommonCode.SUCCESS) : new ResponseResult(CommonCode.FAIL);
    }
}
