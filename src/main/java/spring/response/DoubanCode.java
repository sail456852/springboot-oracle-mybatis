package spring.response;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/30/2019<br/>
 * Time: 10:39 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public enum DoubanCode implements ResultCode {

    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    DUPLIATED_URL(false,10001,"已经存在重复的URL了!"),
    URL_NOT_FOUND(false,10002,"URL没有在服务器中找到!"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private DoubanCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
