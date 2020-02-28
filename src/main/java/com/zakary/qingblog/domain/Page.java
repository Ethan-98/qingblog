package com.zakary.qingblog.domain;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Repository;

/**
 * @ClassNamePage
 * @Description
 * @Author
 * @Date2020/2/21 8:52
 * @Version V1.0
 **/
@Repository
public class Page {
    int pageSize;
    int pageNo;
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
