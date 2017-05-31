package com.zcy.shop.service;

import java.util.List;

import com.zcy.shop.model.Product;


public interface ProductService extends BaseService<Product> {
	//根据热点类别查询推荐商品（仅仅查询前4个）  
    public List<Product> querByCategoryId(int cid);
}
