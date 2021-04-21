package com.system.domain;

import java.util.List;

public class TFlowInfo {
    private Integer id;

    private Integer fId;

    private String fName;

    private String fImgpath;

    private Long fPrice;

    private String fInfo;

    private Integer fCategoryid;

    private Integer fStock;

    private String fStatus;

    private List<Integer> fIds;

    private String query;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName == null ? null : fName.trim();
    }

    public String getfImgpath() {
        return fImgpath;
    }

    public void setfImgpath(String fImgpath) {
        this.fImgpath = fImgpath == null ? null : fImgpath.trim();
    }

    public Long getfPrice() {
        return fPrice;
    }

    public void setfPrice(Long fPrice) {
        this.fPrice = fPrice;
    }

    public String getfInfo() {
        return fInfo;
    }

    public void setfInfo(String fInfo) {
        this.fInfo = fInfo == null ? null : fInfo.trim();
    }

    public Integer getfCategoryid() {
        return fCategoryid;
    }

    public void setfCategoryid(Integer fCategoryid) {
        this.fCategoryid = fCategoryid;
    }

    public Integer getfStock() {
        return fStock;
    }

    public void setfStock(Integer fStock) {
        this.fStock = fStock;
    }

    public String getfStatus() {
        return fStatus;
    }

    public void setfStatus(String fStatus) {
        this.fStatus = fStatus == null ? null : fStatus.trim();
    }

    public List<Integer> getfIds() {
        return fIds;
    }

    public void setfIds(List<Integer> fIds) {
        this.fIds = fIds;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
