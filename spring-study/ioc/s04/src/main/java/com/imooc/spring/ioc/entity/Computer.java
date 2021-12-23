package com.imooc.spring.ioc.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Computer {
    private String brand;
    private String type;
    private String sn;
    private Double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
