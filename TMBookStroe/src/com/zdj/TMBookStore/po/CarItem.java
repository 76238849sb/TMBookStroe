package com.zdj.TMBookStore.po;

/**
 * @author 华韵流风
 * @ClassName car
 * @Description TODO
 * @Date 2021/5/28 20:17
 * @packageName com.zdj.TMBookStore.po
 */
public class CarItem {

    private String cartItemId;
    private Integer quantity;
    private String bid;
    private String uid;
    private Integer orderBy;

    @Override
    public String toString() {
        return "Car{" +
                "cartItemId='" + cartItemId + '\'' +
                ", quantity=" + quantity +
                ", bid='" + bid + '\'' +
                ", uid='" + uid + '\'' +
                ", orderBy=" + orderBy +
                '}';
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }
}
