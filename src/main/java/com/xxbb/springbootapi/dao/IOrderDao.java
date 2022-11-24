package com.xxbb.springbootapi.dao;

import com.xxbb.springbootapi.entity.Order;
import com.xxbb.springbootapi.mapper.OrderMapper;
import com.xxbb.springbootapi.wrapper.OrderQuery;
import com.xxbb.springbootapi.wrapper.OrderUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOrderDao extends IBaseDao<Order, OrderQuery, OrderUpdate, OrderMapper> {
}
