package pers.brian.mall.common.api;

/**
 * 枚举了一些常用API操作码
 *
 * @author BrianHu
 * @create 2021-11-11 11:11
 **/
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNAUTHORIZED(401, "暂未登录或session已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "请求路径或参数错误"),
    UNKNOWN_ERROR(999, "未知错误");

    private final long code;

    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
