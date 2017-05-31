package com.zcy.shop.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.zcy.shop.service.BaseService;

@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {

	private Class clazz; //clazz中存储了当前操作的类型，即泛型T  
    
	@Resource	//放在属性上面就不会调用set方法，使用反射注进来
	private SessionFactory sessionFactory;  
    
    public BaseServiceImpl() {  
        //下面三个打印信息可以去掉，这里是给自己看的  
//        System.out.println("this代表的是当前调用构造方法的对象" + this);  
//		System.out.println("获取当前this对象的父类信息" + this.getClass().getSuperclass());  
//		System.out.println("获取当前this对象的父类信息(包括泛型信息)" + this.getClass().getGenericSuperclass());  
		//拿到泛型的参数类型  
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
		clazz = (Class)type.getActualTypeArguments()[0];  
	} 
    
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
      
    protected Session getSession() {  
        //从当前线程获取session，如果没有则创建一个新的session  
        return sessionFactory.getCurrentSession();  
    } 
    
	public void save(T t) {
		// TODO Auto-generated method stub
		getSession().save(t); 
	}

	public void update(T t) {
		// TODO Auto-generated method stub
		getSession().update(t);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		System.out.println("BaseServiceImpl delete id:"+id);
		System.out.println(clazz.getSimpleName());  
        String hql = "delete " + clazz.getSimpleName() + " as c where c.id=:id";  
        getSession().createQuery(hql) //  
                  .setInteger("id", id) //  
                  .executeUpdate();
	}

	public T get(int id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(clazz, id);
	}

	public List<T> query() {
		// TODO Auto-generated method stub
		String hql = "from " + clazz.getSimpleName();  
        return getSession().createQuery(hql).list();
	}  

}
