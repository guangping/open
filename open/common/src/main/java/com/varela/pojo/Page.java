package com.varela.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lance on 2016/3/23.
 */
public class Page<T> implements Serializable {

    /**
     * 当前页
     */
    private int currPage = 1;
    /**
     * 每页记录数
     */
    private int pageSize = 10;
    /**
     * 总记录数
     */
    private int totalCount=0;
    /**
     * 总页数
     */
    private int totalPage=0;

    /**
     * 数据列表
     * */
    private List<T> data;


    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", data=" + data +
                '}';
    }
}
