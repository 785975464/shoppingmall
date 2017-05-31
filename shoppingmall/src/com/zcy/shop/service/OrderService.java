package com.zcy.shop.service;

import java.util.List;

import com.zcy.shop.model.Order;
import com.zcy.shop.model.Sorder;

//购物车（订单）表
public interface OrderService extends BaseService<Order> {
	//计算购物总价格
	public double calcuTotal(Order order);

	public List<Sorder> getSorders(Order order);
	
//	public List<Sorder> getAllSorders(Order order);
}
