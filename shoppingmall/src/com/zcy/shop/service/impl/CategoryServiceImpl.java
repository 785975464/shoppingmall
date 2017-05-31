package com.zcy.shop.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.zcy.shop.model.Category;
import com.zcy.shop.service.CategoryService;
import com.zcy.shop.utils.HibernateSessionFactory;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	public List<Category> queryByHot(boolean hot) {
		// TODO Auto-generated method stub
		String hql = "from Category c where c.hot=:hot";  
        return getSession().createQuery(hql)  
            .setBoolean("hot", hot)  
            .list();
	}
	
//    private SessionFactory sessionFactory; 
//      
//    //Spring会注进来
//    public void setSessionFactory(SessionFactory sessionFactory) {  
//        this.sessionFactory = sessionFactory;  
//    }    
//    protected Session getSession() {  
//        //从当前线程获取session，如果没有则创建一个新的session   
//        return sessionFactory.getCurrentSession();  
//    }  
//    
//    public void update(Category category) { 
//        getSession().update(category);      
//    } 
//    
//    public void save(Category category) {  
//    	getSession().save(category);      
//    }
//	public void delete(int id) {
//		// TODO Auto-generated method stub
//		String hql = "delete Category while id=:id";  
//        getSession().createQuery(hql) //  
//                .setInteger("id", id) //  
//                .executeUpdate();
//	}
//	public Category get(int id) {
//		// TODO Auto-generated method stub
//		return (Category) getSession().get(Category.class, id);
//	}
//	public List<Category> query() {
//		// TODO Auto-generated method stub
//		 String hql = "from Category";  
//	     return getSession().createQuery(hql).list(); 
//	}
	
	// 只需实现CategoryService接口中新增的方法即可，公共方法已经在BaseServiceImpl中实现了
}
