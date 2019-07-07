package spring.dto;

import com.sun.tracing.dtrace.ModuleAttributes;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/19/2019<br/>
 * Time: 3:54 PM<br/>
 * To change this template use File | Settings | File Templates.
 * you have to have a string id, or int id as least
 */
@Data
@RedisHash("Comment")
public class Comment implements Serializable {

    private Long id;
    private String url;
    private List<String> comments;

    public Comment(String url, List<String> comments) {
        this.url = url;
        this.comments = comments;
    }

    public Comment() {
    }
}
