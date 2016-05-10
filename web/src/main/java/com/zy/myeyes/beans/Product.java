package com.zy.myeyes.beans;

import javax.persistence.Id;

/**
 * Created by zhougb on 2016/5/9.
 */
public class Product {
    @Id
    private long pid;
    private int type;
    private String productDesc;
    private String productName;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
