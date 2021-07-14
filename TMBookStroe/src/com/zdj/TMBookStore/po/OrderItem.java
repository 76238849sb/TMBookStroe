package com.zdj.TMBookStore.po;

/**
 * @author 华韵流风
 * @ClassName OrderItem
 * @Description TODO
 * @Date 2021/5/26 9:04
 * @packageName com.zdj.TMBookStore.po
 */
public class OrderItem {

    private String orderItemId;
    private Integer quantity;
    private Double subtotal;
    private String bid;
    private String bname;
    private Double currPrice;
    private String image_b;
    private String oid;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId='" + orderItemId + '\'' +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", bid='" + bid + '\'' +
                ", bname='" + bname + '\'' +
                ", currPrice=" + currPrice +
                ", image_b='" + image_b + '\'' +
                ", oid='" + oid + '\'' +
                '}';
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Double getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(Double currPrice) {
        this.currPrice = currPrice;
    }

    public String getImage_b() {
        return image_b;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
