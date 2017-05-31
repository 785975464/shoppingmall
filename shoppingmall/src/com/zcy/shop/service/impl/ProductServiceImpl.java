package com.zcy.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zcy.shop.model.Product;
import com.zcy.shop.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
	
	public List<Product> querByCategoryId(int cid) {  
//        String hql = "from Product p join fetch p.category "  
//                + "where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc"; 
        
        String hql = "from Product p "  
                + "where p.commend=true and p.open=true and p.cid=:cid order by p.date desc";  
        return getSession().createQuery(hql)  
            .setInteger("cid", cid)  
            .setFirstResult(0)  
            .setMaxResults(4)  
            .list();  
    } 
}
