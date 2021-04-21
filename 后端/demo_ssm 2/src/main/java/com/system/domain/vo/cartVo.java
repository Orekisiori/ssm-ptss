package com.system.domain.vo;


/**
 * 购物车需要显示的数据
 */
public class cartVo {
    private Integer cartId;
    private Integer userId;
    private Integer fId;
    private String fName;
    private Long fPrice;
    private Integer fNumber;

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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Long getfPrice() {
        return fPrice;
    }

    public void setfPrice(Long fPrice) {
        this.fPrice = fPrice;
    }

    public Integer getfNumber() {
        return fNumber;
    }

    public void setfNumber(Integer fNumber) {
        this.fNumber = fNumber;
    }
}
