package cn.javabb.common.web.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询通用返回结果
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private int code = 0;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 总数量
     */
    private long count;
    /**
     * 当前页数据
     */
    private List<T> data;

    public PageResult() {
    }

    public PageResult(List<T> rows) {
        this(rows, rows == null ? 0 : rows.size());
    }

    public PageResult(List<T> rows, long total) {
        this.count = total;
        this.data = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}