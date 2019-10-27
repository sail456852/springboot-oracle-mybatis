package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.dto.Comment;
import spring.response.DoubanCode;
import spring.response.QueryResponseResult;
import spring.response.QueryResult;
import spring.response.ResponseResult;
import spring.service.DoubanService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/28/2019<br/>
 * Time: 5:10 PM<br/>
 * To change this template use File | Settings | File Templates.
 * http://localhost:8080
 */
@Controller
public class DoubanController {

    @Autowired
    private DoubanService doubanService;

    /**
     * Postman usage
     * POST method
     * Put url key - value in params tab, always use url as key not url[] or url[1]
     * Put in Body -> form-data or json won't work
     * http://localhost:8080/addUrl
     * POST -> form-data
     * body data
     * comments:testComment
     * comments:testComment2
     * url:KKKK4
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUrl")
    public ResponseResult addUrl(Comment dto){
        DoubanCode doubanCode;
        if (CollectionUtils.isEmpty(dto.getComments())) {
            doubanCode = doubanService.addUrlOld(dto.getUrl());
        }else{
            doubanCode = doubanService.addUrl(dto);
        }
        ResponseResult responseResult = new ResponseResult(doubanCode);
        return responseResult;
    }

    /**
     * ajax or postman form-data
     * @param dto  don't use @requestBody unsupported media type
     * @return
     */
    @ResponseBody
    @RequestMapping("/removeUrl")
    public ResponseResult removeUrl(Comment dto){
        DoubanCode doubanCode;
        if (CollectionUtils.isEmpty(dto.getComments())) {
            doubanCode = doubanService.removeUrlOld(dto.getUrl());
        }else{
            doubanCode = doubanService.removeUrl(dto.getUrl());
        }
        ResponseResult responseResult = new ResponseResult(doubanCode);
        return responseResult;
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public ResponseResult getAll(){
        Comment dto = new Comment();
        if (CollectionUtils.isEmpty(dto.getComments())) {
            List<String> list = doubanService.getAllOld();
            QueryResult<String> stringQueryResult = new QueryResult<>();
            stringQueryResult.setTotal(list.size());
            stringQueryResult.setList(list);
            QueryResponseResult queryResponseResult = new QueryResponseResult(DoubanCode.SUCCESS, stringQueryResult);
            return queryResponseResult;
        }else{
            ArrayList<Comment> list = new ArrayList<>();
            Iterable<Comment> allUrls = doubanService.getAllUrls();
            allUrls.forEach(list::add);
            QueryResult<Comment> stringQueryResult = new QueryResult<>();
            stringQueryResult.setTotal(list.size());
            stringQueryResult.setList(list);
            QueryResponseResult queryResponseResult = new QueryResponseResult(DoubanCode.SUCCESS, stringQueryResult);
            return queryResponseResult;
        }
    }

    /**
     * http://localhost:8080/removeAll
     * @return
     */
    @ResponseBody
    @RequestMapping("/removeAll")
    public String removeAll(){
        doubanService.cleanAll();
        return "clean done";
    }

}
