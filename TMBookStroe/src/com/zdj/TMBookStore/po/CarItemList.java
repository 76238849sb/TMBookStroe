package com.zdj.TMBookStore.po;

/**
 * @author 华韵流风
 * @ClassName CarItemList
 * @Description TODO
 * @Date 2021/5/29 16:37
 * @packageName com.zdj.TMBookStore.po
 */
public class CarItemList {
    private String cartItemId;
    private String bid;
    private String image_b;
    private String bname;
    private Double currPrice;
    private Integer quantity;
    private Double subtotal;

    @Override
    public String toString() {
        return "CarItemList{" +
                "cartItemId='" + cartItemId + '\'' +
                ", bid='" + bid + '\'' +
                ", image_b='" + image_b + '\'' +
                ", bname='" + bname + '\'' +
                ", currPrice=" + currPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getImage_b() {
        return image_b;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
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
}
