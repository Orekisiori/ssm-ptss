package com.system.domain.dto;

/**
 * crateOrder的商品集合类
 */
public class orderFlowInfoDto {
    private Integer fId;
    private String fName;
    private Long fPrice;
    private Integer fNumber;

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
