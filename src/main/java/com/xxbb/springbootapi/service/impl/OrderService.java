package com.xxbb.springbootapi.service.impl;

import com.xxbb.springbootapi.entity.Order;
import com.xxbb.springbootapi.mapper.OrderMapper;
import com.xxbb.springbootapi.service.IOrderService;
import com.xxbb.springbootapi.wrapper.OrderQuery;
import com.xxbb.springbootapi.wrapper.OrderUpdate;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseService<Order, OrderQuery, OrderUpdate, OrderMapper> implements IOrderService {

}

