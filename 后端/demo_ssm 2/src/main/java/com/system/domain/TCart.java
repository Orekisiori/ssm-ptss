package com.system.domain;

public class TCart {
    private Integer id;

    private Integer cartId;

    private Integer userId;

    private Integer fId;

    private Integer fNumber;

    private String querykey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getfNumber() {
        return fNumber;
    }

    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }

    public String getQuerykey() {
        return querykey;
    }

    public void setQuerykey(String querykey) {
        this.querykey = querykey;
    }
}
