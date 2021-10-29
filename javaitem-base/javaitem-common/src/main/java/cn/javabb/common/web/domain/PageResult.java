package cn.javabb.common.web.domain;

import cn.hutool.core.convert.Convert;
import cn.javabb.common.web.page.BasePage;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询通用返回结果
 */
public class PageResult<T> extends BasePage {
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

    public PageResult(IPage<T> iPage) {
        super((int) iPage.getCurrent(), (int) iPage.getSize());
        this.data = iPage.getRecords();
        this.count = Convert.toLong(iPage.getTotal());
    }

    public void setIPage(IPage<T> iPage) {
        this.data = iPage.getRecords();
        this.count = Convert.toLong(iPage.getTotal());
        this.setPageNo(Convert.toInt(iPage.getCurrent()));
        this.setPageSize(Convert.toInt(iPage.getSize()));
    }

    public PageResult(List<T> rows) {
        this(rows, rows == null ? 0 : rows.size());
    }

    public PageResult(List<T> rows, long total) {
        this.count = total;
        this.data = rows;
    }
    public PageResult(Integer pageNo,Integer pageSize,List<T> rows, long total) {
        super(pageNo, pageSize);
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
