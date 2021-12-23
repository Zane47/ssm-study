package com.imooc.spring.ioc.bookshop.service;

import com.imooc.spring.ioc.bookshop.dao.BookDao;

public class BookService {

    // 不需要new, 在IOC容器启动过程中会自动注入
    private BookDao bookDao;

    /**
     * 采购新书
     */
    public void purchase() {
        System.out.println("purchase start");
        bookDao.insert();
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
