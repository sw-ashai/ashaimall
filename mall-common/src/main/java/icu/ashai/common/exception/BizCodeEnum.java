package icu.ashai.common.exception;

/**
 * @author Ashai
 * @date 2021/12/11 下午 11:11
 * @Description 错误码枚举
 */
public enum BizCodeEnum {
    /**
     * 系统未知异常代码
     */
    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    /**
     * 字段验证失败异常代码
     */
    VALID_EXCEPTION(10001,"参数格式校验失败");

    private final int code;
    private final String msg;

    BizCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
