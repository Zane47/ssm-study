package com.imooc.spring.ioc.bookshop.dao;

public class BookDaoImpl implements BookDao {

    @Override
    public void insert() {
        System.out.println("MySQL Table Book insert one record");
    }
}
