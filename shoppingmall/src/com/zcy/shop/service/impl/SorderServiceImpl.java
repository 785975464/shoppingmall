package com.zcy.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zcy.shop.model.Order;
import com.zcy.shop.model.Product;
import com.zcy.shop.model.Sorder;
import com.zcy.shop.service.SorderService;

@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService{

//	public void save(Sorder t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void update(Sorder t) {
//		// TODO Auto-generated method stub
//		
//	}


	public Order addSorder(Order order, Product product) {
		// TODO Auto-generated method stub
		boolean isHave = false; //用来标记有没有重复购物项  
        //拿到当前的购物项  
        Sorder sorder = productToSorder(product);  
        
        String hql = "from Sorder s "  
                + "where s.orderid=:orderid";  
		List<Sorder> list = getSession().createQuery(hql)  
            .setInteger("orderid", order.getId())  
            .list(); 
		
        //判断当前购物项是否重复，如果重复，则添加数量即可  
        for(Sorder old : list) {  
            if(old.getProductid().equals(sorder.getProductid())) {  
                //购物项有重复，添加数量即可  
                old.setNumber(old.getNumber() + sorder.getNumber());  
                isHave = true;  
                break;  
            }  
        }  
        //当前购物项在购物车中不存在，新添加即可  
        if(!isHave) {  
//            order.add(sorder);
        	sorder.setOrderid(order.getId());
            save(sorder);
        }  
        return order;
	}

	public Sorder productToSorder(Product product) {
		// TODO Auto-generated method stub
		Sorder sorder = new Sorder();  
        sorder.setProductname(product.getName());  
        sorder.setNumber(1);  
        sorder.setPrice(product.getPrice());  
        sorder.setProductid(product.getId());  
        return sorder;
	}

	public Order updateSorder(Sorder sorder, Order order) {
		// TODO Auto-generated method stub
//		System.out.println("in updateSorder sorder:"+sorder+" & order:"+order+" & order.getId():"+order.getId());
		String hql = "from Sorder s where s.id=:id";  
		List<Sorder> list = getSession().createQuery(hql)  
            .setInteger("id", sorder.getId())  
            .list();
		Sorder s = list.get(0);
		
		String hql2 = "from Order o where o.id=:id";  
		List<Order> list2 = getSession().createQuery(hql2)  
            .setInteger("id", order.getId())  
            .list();
		Order o = list2.get(0);
		
		int flag = sorder.getNumber();
		if(flag>0){
			s.setNumber(s.getNumber()+1);
			o.setTotal(o.getTotal()+s.getPrice());
		}
		else{
			s.setNumber(s.getNumber()-1);
			o.setTotal(o.getTotal()-s.getPrice());
		}
		update(s);
		
        return o;
	}

	public List<Object> querySale() {
        //不用fecth查出来的就是两项
        String hql = "select s.productname, sum(s.number) from Sorder s left join Product p on s.productid=p.id group by s.productid";
        return getSession().createSQLQuery(hql) //使用原生SQL查询
        		//.setFirstResult(0) //
            //.setMaxResults(number) //
            .list();
    }
}
