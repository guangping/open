package com.varela.dao.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lance on 10/16/2015.
 */
public class PageDataTables<T> implements Serializable {

    private List<T> data;//数据集

    private long sEcho = 1;

    private long iDisplayStart = 1;//记录开始数

    private long iDisplayLength = 20;//没页记录数

    private long iTotalRecords = 0;//总记录数

    private long iTotalDisplayRecords = 0;//总记录数

    public PageDataTables() {
    }

    public PageDataTables(long sEcho, long iDisplayLength, List<T> data, long iTotalRecords) {
        this.iDisplayLength = iDisplayLength;
        this.sEcho = sEcho;
        this.data = data;
        this.iTotalRecords = iTotalRecords;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getsEcho() {
        return sEcho;
    }

    public void setsEcho(long sEcho) {
        this.sEcho = sEcho;
    }

    public long getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(long iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public long getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(long iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    @Override
    public String toString() {
        return "PageDataTables{" +
                "data=" + data +
                ", sEcho=" + sEcho +
                ", iDisplayStart=" + iDisplayStart +
                ", iDisplayLength=" + iDisplayLength +
                ", iTotalRecords=" + iTotalRecords +
                ", iTotalDisplayRecords=" + iTotalDisplayRecords +
                '}';
    }
}
