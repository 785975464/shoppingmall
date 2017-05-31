package com.zcy.shop.action;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcy.shop.model.Order;
import com.zcy.shop.model.Status;
import com.zcy.shop.model.User;

@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends BaseAction<Order>{
	
	@Override 
	public Order getModel() {  
        model = (Order) session.get("order");  
        return model;  
    }
	
	public String save(){
		User u = (User)session.get("user");
		model.setUserid(u.getId());
		Status st = new Status("正常");
		statusService.save(st);			//保存订单状态
		model.setStatus(st.getId());
		model.setDate(new Timestamp(new Date().getTime()));		//将当前时间转为timestamp
		orderService.update(model);
		
		//此时购物车已经入库，那么原来session中的购物车就应该清空  
        session.put("oldOrder", session.get("order"));//先将原来的购物车信息保存下来，因为后面付款的时候还需要相关信息  
        session.put("order", new Order());//new一个新的空购物车（相当于清空了购物车），还可以方便用户再买~ 
		return "bank";
	}
}
