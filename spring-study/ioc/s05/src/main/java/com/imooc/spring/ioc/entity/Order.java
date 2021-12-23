package com.imooc.spring.ioc.entity;


public class Order {
    private Double price;
    private Integer quantity;
    private Double total;

    public Order() {
        System.out.println("order NoArgs construct " + this);
    }

    public void init() {
        System.out.println("init()");
        total = price * quantity;
    }

    public void destroy() {
        // 文件, 网络连接, 其他系统方法的调用...
        System.out.println("destroy");
    }



    public void pay() {
        System.out.println("Order pay total: " + total);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        System.out.println("setPrice " + price);
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        System.out.println("setQuantity " + quantity);
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
