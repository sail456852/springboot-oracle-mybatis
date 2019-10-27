package spring.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/19/2019<br/>
 * Time: 5:28 PM<br/>
 * To change this template use File | Settings | File Templates.
 * Single hash item
 * Id will auto
 */
@Data
@RedisHash("Fruit")
public class Fruit implements Serializable {

    public Fruit(String name) {
        this.name = name;
    }

    // For spring data to generate id automatically
    private String id;
    private String name;

}
