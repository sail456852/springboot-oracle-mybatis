package mapper;


import beans.Test;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Eugene_Wang<br/>
 * Date: 12/20/2019<br/>
 * Time: 10:24 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface TestMapper {

    Test selectByPrimaryKey(Date testTimestamp);

    int updateByPrimaryKeySelective(Test record);

}