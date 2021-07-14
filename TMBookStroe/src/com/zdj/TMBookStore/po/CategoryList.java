package com.zdj.TMBookStore.po;

import java.util.List;
import java.util.Map;

/**
 * @author 华韵流风
 * @ClassName CategoryList
 * @Description TODO
 * @Date 2021/5/23 9:48
 * @packageName com.zdj.TMBookStore.po
 */
public class CategoryList {
    private String cid;
    private String cname;
    private String desc;
    private Integer orderBy;
    /**
     * 一级分类名
     */


    private List<CategoryListTwo> categoryListTwos;

    @Override
    public String toString() {
        return "CategoryList{" +
                "cid='" + cid + '\'' +
                ", orderBy=" + orderBy +
                ", desc='" + desc + '\'' +
                ", cname='" + cname + '\'' +
                ", categoryListTwos=" + categoryListTwos +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<CategoryListTwo> getCategoryListTwos() {
        return categoryListTwos;
    }

    public void setCategoryListTwos(List<CategoryListTwo> categoryListTwos) {
        this.categoryListTwos = categoryListTwos;
    }
}
