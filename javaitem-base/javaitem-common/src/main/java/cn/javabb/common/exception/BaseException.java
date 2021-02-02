package cn.javabb.common.exception;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/02/02 22:46
 */
public class BaseException extends IException{
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code, String message) {
        super(code, message);
    }

    @Override
    public Integer getCode() {
        return super.getCode();
    }

    @Override
    public void setCode(Integer code) {
        super.setCode(code);
    }
}
