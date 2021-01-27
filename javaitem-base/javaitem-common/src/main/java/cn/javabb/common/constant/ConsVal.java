package cn.javabb.common.constant;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/20 21:40
 */
public class ConsVal {

    /* 返回结果统一 */
    public static final int RESULT_OK_CODE = 0;  // 默认成功码
    public static final int RESULT_ERROR_CODE = 1;  // 默认失败码

    /* token 设置 */
    public static final Long TOKEN_EXPIRE_TIME = 60 * 60 * 24L;  // token过期时间,单位秒
    public static final int TOKEN_WILL_EXPIRE = 30;  // token将要过期自动刷新,单位分钟
    public static final String TOKEN_KEY = "ULgNsWJ8rPjRtnjzX/Gv2RGS80Ksnm/ZaLpvIL+NrBg=";  // 生成token的key


}
