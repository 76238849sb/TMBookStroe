package com.zdj.TMBookStore.po;

import java.util.List;

/**
 * @author 华韵流风
 * @ClassName order
 * @Description TODO
 * @Date 2021/5/26 9:01
 * @packageName com.zdj.TMBookStore.po
 */
public class Order {

    private String oid;
    private String ordertime;
    private Double total;
    /**
     * 订单状态：
     * 1、未付款
     * 2、已付款
     * 3、已发货
     * 4、交易成功
     * 5、已取消
     */
    private Integer status;
    private String address;
    private String uid;

    private List<String> images;

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", total=" + total +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", uid='" + uid + '\'' +
                ", images=" + images +
                '}';
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
