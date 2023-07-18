package com.restassured.examplespojo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class ListUserPojo {
    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private List<UserPojo> data;
    private SupportPojo support;


    public List<UserPojo> getData() {
        return data;
    }

    public void setData(List<UserPojo> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @JsonGetter("per_page")
    public int getPerPage() {
        return perPage;
    }

    @JsonSetter("per_page")
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @JsonGetter("total_pages")
    public int getTotalPages() {
        return totalPages;
    }

    @JsonSetter("total_pages")
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public SupportPojo getSupport() {
        return support;
    }

    public void setSupport(SupportPojo support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "ListUserPojo{" +
                "page=" + page +
                ", per_page=" + perPage +
                ", total=" + total +
                ", total_pages=" + totalPages +
                ", data=" + data +
                ", support=" + support +
                '}';
    }
}
