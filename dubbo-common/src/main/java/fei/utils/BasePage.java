package fei.utils;

import java.io.Serializable;

/**
 * @author xiaoshijiu
 * @location DubboTest/fei.utils
 * @date 2019/4/16
 */
public class BasePage implements Serializable {

    private static final long serialVersionUID = 1L;

    //当前页
    private Integer pageNum;
    //总页数
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        //默认查询第一页
        if (pageNum == null) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        //默认一页显示10条数据
        if (pageSize == null) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }
}
