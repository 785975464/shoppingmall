package com.zcy.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zcy.shop.model.Order;
import com.zcy.shop.model.Product;
import com.zcy.shop.model.Sorder;

@Controller("sorderAction")
@Scope("prototype")
public class SorderAction extends BaseAction<Sorder> {
	public String addSorder() {  
        
//		System.out.println("model:"+model);
		System.out.println("model getId:"+model.getId());	//传递进来的是product的id，而不是sorder的
        //1. 根据product.id获取相应的商品数据  
        Product product = productService.get(model.getId());  
          
        //2. 判断当前session是否有购物车，如果没有则创建  
        if(session.get("order") == null || ((Order)session.get("order")).getId() == null) {  
            //创建新的购物车，存储到session中  
        	Order o = new Order();
//        	o.setReceiver("zcy");
//        	o.setPhone("170");
//        	o.setPost("210094");
//        	o.setAddress("南京");
//        	o.setRemark("测试配送信息");
        	orderService.save(o);
            session.put("order", o);  
        }   
        //3. 把商品信息转化为sorder,并且添加到购物车中（判断购物项是否重复）  
        Order order = (Order) session.get("order");  
        order = sorderService.addSorder(order, product);  
          
        //4. 计算购物的总价格  
        order.setTotal(orderService.calcuTotal(order));  
        orderService.update(order);
        //5. 把新的购物车存储到session中  
        session.put("order", order);  
        
        
		List<Sorder> list = orderService.getSorders(order);
		session.put("sorders", list);  
        return "showCart";  
    }
	
	public String updateSorder(){
//		System.out.println("model getId:"+model.getId()+" &model getNumber:"+model.getNumber());
		try{
			Order order = (Order) session.get("order");
			System.out.println("order:"+order);
			order = sorderService.updateSorder(model,order);	//model.getNumber()为标志位，1为增，-1为减
			orderService.update(order);
			session.put("order", order);
			inputStream=new ByteArrayInputStream("success".getBytes());
			System.out.println("update success!");
		}catch(Exception e){
			inputStream=new ByteArrayInputStream("error".getBytes());
			System.out.println("update error!");
		}
		//以流的形式返回新的总价格
//        inputStream = new ByteArrayInputStream(order.getTotal().toString().getBytes());
		return "stream";
		
	}
	
	public String querySale() {
		System.out.println("in querySale!");
        List<Object> jsonList = sorderService.querySale();
        System.out.println("jsonList:"+jsonList);
        
        //将查询出来的list放到值栈的顶端，为什么这样做呢？看下面的解释
        ActionContext.getContext().getValueStack().push(jsonList);
        return "jsonList";
    }
}
