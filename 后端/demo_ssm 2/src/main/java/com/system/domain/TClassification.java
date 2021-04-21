package com.system.domain;

public class TClassification {
    private Integer id;

    private Integer categoryId;

    private String categoryName;

    private String fId;

    private String query;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
