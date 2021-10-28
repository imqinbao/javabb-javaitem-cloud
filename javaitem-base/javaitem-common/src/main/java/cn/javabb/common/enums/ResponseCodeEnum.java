package cn.javabb.common.enums;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/10/28 18:01
 */
public enum ResponseCodeEnum {

    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    EXCEPTION(2, "系统异常"),
    REPEAT_SUBMIT(1001, "重复提交"),
    RPC_ERROR(1002, "连接失败"),
    RPC_TIMEOUT(1003, "连接超时"),
    TOKEN_EXPIRED(1004, "token过期"),
    TOKEN_ERROR(1005, "token校验失败"),
    PARAM_VALID_ERROR(1006, "参数校验失败"),
    PARAM_TYPE_ERROR(1007, "参数类型错误"),
    ;

    private Integer code;
    private String msg;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResponseCodeEnum match(byte code) {
        ResponseCodeEnum[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            ResponseCodeEnum responseCodeEnum = values[i];
            if (responseCodeEnum.getCode() == code) {
                return responseCodeEnum;
            }
        }
        return null;
    }

}
