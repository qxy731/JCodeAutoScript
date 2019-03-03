package com.good.comm.web;

import java.util.HashMap;


public class WebRequest {

    private int start;
    private int length;
    private HashMap<Order, String>[] norder;
    
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    

    public HashMap<Order, String>[] getNorder() {
        return norder;
    }
    public void setNorder(HashMap<Order, String>[] order) {
        this.norder = order;
    }


    public enum Order {
        column,
        dir
    } 
}
