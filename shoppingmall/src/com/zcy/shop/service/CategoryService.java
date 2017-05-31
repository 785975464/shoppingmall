package com.zcy.shop.service;

import java.util.List;

import com.zcy.shop.model.Category;


public interface CategoryService extends BaseService<Category> {  
//	public void save(Category category); 		//插入
//	
//	public void update(Category category);		//更新
//	
//	public void delete(int id); //删除  
//    
//    public Category get(int id); //获取一个Category  
//      
//    public List<Category> query(); //获取全部Category 
	
	//只要添加CategoryService本身需要的新的方法即可，公共方法已经在BaseService中了
	//根据boelen值查询热点或非热点类别  
    public List<Category> queryByHot(boolean hot);
}
