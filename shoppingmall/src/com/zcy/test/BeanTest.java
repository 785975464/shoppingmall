package com.zcy.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zcy.shop.model.Category;
import com.zcy.shop.service.CategoryService;
import com.zcy.shop.service.impl.CategoryServiceImpl;

public class BeanTest {

	/**
	 * @param args
	 */
	@Resource  
    private CategoryService categoryService;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//		Object o = ctx.getBean("date");
		System.out.println(ctx.getBean("date"));
	}

//	@Test  //����Hibernate�Ŀ�����������Ϊû�����ϣ�����ֱ��new  
//    public void hihernate() {  
//        CategoryService categoryService = new CategoryServiceImpl();  
//        Category category = new Category("Ůʿ��װ", true);  
//        categoryService.save(category);  
//    }
	

	
//	@Test //����Hibernate��Spring���Ϻ�  
//    public void hibernateAndSpring() {  
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//		categoryService = (CategoryService) ctx.getBean("categoryService");
//        categoryService.update(new Category(1, "����Ůʽ", true)); //categoryServiceͨ��Spring������ע�������  
//    }
}
