package spring.service;

import spring.dto.Comment;
import spring.response.CommonCode;
import spring.response.DoubanCode;

import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/28/2019<br/>
 * Time: 5:11 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface DoubanService {

    Iterable<Comment> getAllUrls();

    void cleanAll();

    DoubanCode removeUrl(String url);

    DoubanCode addUrl(Comment dto);

    DoubanCode addUrlOld(String url);

    DoubanCode removeUrlOld(String url);

    List<String> getAllOld();

    CommonCode addInstantSearchEnter(String inputedText);

    List<String> instantSearch(String inputedText);
}
