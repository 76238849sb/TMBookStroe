package com.zdj.TMBookStore.po;

import java.io.StringReader;

public class BookDet {
    /**
     * 小图
     */
    private String image_b;
    private Double currPrice;
    private Double price;
    private String bname;
    private String author;
    private String press;
    private String bid;
    private Double discount;
    private String cid;
    private String publishtime;
    private Integer edition;
    private Integer pageNum;
    private Integer wordNum;
    private String printtime;
    private Integer booksize;
    private String paper;
    private String image_w;
    private Integer orderBy;

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getWordNum() {
        return wordNum;
    }

    public void setWordNum(Integer wordNum) {
        this.wordNum = wordNum;
    }

    public String getPrinttime() {
        return printtime;
    }

    public void setPrinttime(String printtime) {
        this.printtime = printtime;
    }

    public Integer getBooksize() {
        return booksize;
    }

    public void setBooksize(Integer booksize) {
        this.booksize = booksize;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getImage_w() {
        return image_w;
    }

    public void setImage_w(String image_w) {
        this.image_w = image_w;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "BookDet{" +
                "image_w='" + image_b + '\'' +
                ", currPrice=" + currPrice +
                ", price=" + price +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", bid='" + bid + '\'' +
                ", discount=" + discount +
                '}';
    }

    public String getImage_b() {
        return image_b;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public Double getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(Double currPrice) {
        this.currPrice = currPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
