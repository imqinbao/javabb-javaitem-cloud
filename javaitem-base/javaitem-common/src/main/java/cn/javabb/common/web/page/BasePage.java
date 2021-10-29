package cn.javabb.common.web.page;

import java.io.Serializable;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/10/29 16:01
 */
public class BasePage implements Serializable {
    private static final long serialVersionUID = 1185040602761806791L;

    private static final Integer DEFAULT_PAGE_NO = 1;

    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer pageNo;

    private Integer pageSize;

    public BasePage() {
        this.pageNo = DEFAULT_PAGE_NO;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public BasePage(Integer pageNo,Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        if (this.pageNo == null || this.pageNo <= 0) {
            this.pageNo = DEFAULT_PAGE_NO;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize <= 0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BasePage{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
