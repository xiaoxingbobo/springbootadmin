package com.xxbb.springbootapi.controller.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> extends Result{
    private int pageNum;
    private int pageSize;
    private int total;
    private List<T> data;
}