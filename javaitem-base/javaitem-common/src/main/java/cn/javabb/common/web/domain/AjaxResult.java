package cn.javabb.common.web.domain;


import cn.javabb.common.constant.ConsVal;

import java.util.HashMap;

/**
 * 接口返回结果对象
 */
public class AjaxResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private static final String CODE_NAME = "code";  // 状态码字段名称
    private static final String MSG_NAME = "msg";  // 状态信息字段名称
    private static final String DATA_NAME = "data";  // 数据字段名称
    private static final int DEFAULT_OK_CODE = ConsVal.RESULT_OK_CODE;  // 默认成功码
    private static final int DEFAULT_ERROR_CODE = ConsVal.RESULT_ERROR_CODE;  // 默认失败码
    private static final String DEFAULT_OK_MSG = "操作成功";  // 默认成功msg
    private static final String DEFAULT_ERROR_MSG = "操作失败";  // 默认失败msg

    private AjaxResult() {
    }

    /**
     * 返回成功
     */
    public static AjaxResult ok() {
        return ok(null);
    }

    /**
     * 返回成功
     */
    public static AjaxResult ok(String message) {
        return ok(DEFAULT_OK_CODE, message);
    }

    /**
     * 返回成功
     */
    public static AjaxResult ok(int code, String message) {
        if (message == null) message = DEFAULT_OK_MSG;
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put(CODE_NAME, code);
        ajaxResult.put(MSG_NAME, message);
        return ajaxResult;
    }

    /**
     * 返回失败
     */
    public static AjaxResult error() {
        return error(null);
    }

    /**
     * 返回失败
     */
    public static AjaxResult error(String message) {
        return error(DEFAULT_ERROR_CODE, message);
    }

    /**
     * 返回失败
     */
    public static AjaxResult error(int code, String message) {
        if (message == null) message = DEFAULT_ERROR_MSG;
        return ok(code, message);
    }

    public AjaxResult setCode(Integer code) {
        return put(CODE_NAME, code);
    }

    public AjaxResult setMsg(String message) {
        return put(MSG_NAME, message);
    }

    public AjaxResult setData(Object object) {
        return put(DATA_NAME, object);
    }

    public Integer getCode() {
        Object o = get(CODE_NAME);
        return o == null ? null : Integer.parseInt(o.toString());
    }

    public String getMsg() {
        Object o = get(MSG_NAME);
        return o == null ? null : o.toString();
    }

    public Object getData() {
        return get(DATA_NAME);
    }

    @Override
    public AjaxResult put(String key, Object object) {
        if (key != null && object != null) super.put(key, object);
        return this;
    }

}