package com.varela.dao.pojo;

import java.io.Serializable;

/**
 * Created by lance on 10/16/2015.
 */
public class DataTables implements Serializable {


    private int sEcho = 1;

    private int iDisplayStart = 1;//记录开始数

    private int iDisplayLength = 20;//没页记录数

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    @Override
    public String toString() {
        return "DataTables{" +
                "sEcho=" + sEcho +
                ", iDisplayStart=" + iDisplayStart +
                ", iDisplayLength=" + iDisplayLength +
                '}';
    }
}
