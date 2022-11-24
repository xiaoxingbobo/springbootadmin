package com.xxbb.springbootapi.service;

import com.xxbb.springbootapi.entity.Order;
import com.xxbb.springbootapi.mapper.OrderMapper;
import com.xxbb.springbootapi.wrapper.OrderQuery;
import com.xxbb.springbootapi.wrapper.OrderUpdate;

public interface IOrderService extends IBaseService<Order, OrderQuery, OrderUpdate, OrderMapper> {

}
