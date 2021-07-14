package com.zdj.TMBookStore.po;

/**
 * @author 华韵流风
 * @ClassName CategoryListTwo
 * @Description TODO
 * @Date 2021/5/23 11:23
 * @packageName com.zdj.TMBookStore.po
 */
public class CategoryListTwo {
    /**
     * 二级分类名
     */
    private String cname;
    private String cid;
    private String desc;
    private String pid;
    private Integer orderBy;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "CategoryListTwo{" +
                "cname='" + cname + '\'' +
                ", cid='" + cid + '\'' +
                ", desc='" + desc + '\'' +
                ", pid='" + pid + '\'' +
                ", orderBy=" + orderBy +
                '}';
    }
}
