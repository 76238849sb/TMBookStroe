package com.zdj.TMBookStore.utils;

import java.util.List;

/**
 * @author 华韵流风
 * @ClassName PageBean
 * @Description TODO
 * @Date 2021/5/15 9:46
 * @packageName cn.softeem.taobao.po
 */
public class PageBean<T> {
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 每页记录数
     */
    private Integer pageCount;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 当前页数
     */
    private Integer pageNow;
    /**
     * 每页记录
     */
    private List<T> list;

    /**
     * 分页的url
     */
    private String url;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalPage=" + totalPage +
                ", pageCount=" + pageCount +
                ", totalCount=" + totalCount +
                ", pageNow=" + pageNow +
                ", list=" + list +
                ", url='" + url + '\'' +
                '}';
    }
}
