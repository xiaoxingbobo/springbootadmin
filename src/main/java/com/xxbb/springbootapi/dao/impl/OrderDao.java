package com.xxbb.springbootapi.dao.impl;

import com.xxbb.springbootapi.dao.IOrderDao;
import com.xxbb.springbootapi.entity.Order;
import com.xxbb.springbootapi.mapper.OrderMapper;
import com.xxbb.springbootapi.wrapper.OrderQuery;
import com.xxbb.springbootapi.wrapper.OrderUpdate;
import org.springframework.stereotype.Service;

@Service
public class OrderDao extends BaseDao<Order, OrderQuery, OrderUpdate, OrderMapper> implements IOrderDao {

}
