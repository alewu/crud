package com.ale.crud.util.freemarker.test.common.bean.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 * 分页类的封装
 */
@Data
public class PageBean<E> extends PageParams implements Serializable {

    /*总记录数*/
    private Long recordCount;

    /*总页数*/
    private Integer pageCount;

    /*每页显示数据的集合*/
    private List<E> list;

    public PageBean(List<E> list){
        this.list = list;
    }

    public PageBean() {

    }
}