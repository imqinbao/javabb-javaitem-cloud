package cn.javabb.common.web.page;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.javabb.common.web.domain.PageResult;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collections;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/10/29 15:59
 */
@Slf4j
public class PageResultUtil {

    /**
     *
     * @param list
     * @param pageResult
     */
    public static <T> void setPageResult(List<T> list, PageResult<T> pageResult) {
        if (CollUtil.isNotEmpty(list) && list instanceof Page) {
            Page page = (Page) list;
            pageResult.setPageNo(page.getPageNum());
            pageResult.setPageSize(page.getPageSize());
            pageResult.setCount(Convert.toInt(page.getTotal()));
            pageResult.setData(list);
        }else{
            pageResult = new PageResult<>();
            pageResult.setData(Collections.emptyList());
            pageResult.setCount(0);
            pageResult.setPageNo(0);
            pageResult.setPageSize(0);
        }
    }
}
