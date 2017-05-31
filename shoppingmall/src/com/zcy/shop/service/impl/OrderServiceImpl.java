package com.zcy.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zcy.shop.model.Order;
import com.zcy.shop.model.Sorder;
import com.zcy.shop.service.OrderService;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

	public double calcuTotal(Order order) {
		// TODO Auto-generated method stub
		String hql = "from Sorder s "  
                + "where s.orderid=:orderid";  
		List<Sorder> list = getSession().createQuery(hql)  
            .setInteger("orderid", order.getId())  
            .list();  
        
		double total = 0.0;  
        for(Sorder sorder : list) {  
            total += sorder.getNumber() * sorder.getPrice();  
        }  
        return total;
	}

	public List<Sorder> getSorders(Order order){
		String hql = "from Sorder s "  
                + "where s.orderid=:orderid";  
		List<Sorder> list = getSession().createQuery(hql)  
            .setInteger("orderid", order.getId())  
            .list();
		return list; 
	}
	
//	public List<Sorder> getAllSorders(Order order) {
//		// TODO Auto-generated method stub
//		String hql = "from Sorder s "  
//                + "where s.orderid=:orderid";  
//        return getSession().createQuery(hql)  
//            .setInteger("cid", order.getId())  
//            .list();  
//	}

}
