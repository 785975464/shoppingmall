package com.zcy.shop.service;

import java.util.List;

import com.zcy.shop.model.Order;
import com.zcy.shop.model.Product;
import com.zcy.shop.model.Sorder;

//购物项表
public interface SorderService extends BaseService<Sorder> {
	//添加购物项，返回新的购物车  
    public Order addSorder(Order order, Product product);  
    //把商品数据转化为购物项  
    public Sorder productToSorder(Product product);
    //根据商品id和数量更新商品数量
    public Order updateSorder(Sorder sorder, Order order);
	//查询热点商品的销售量
    public List<Object> querySale();
}
