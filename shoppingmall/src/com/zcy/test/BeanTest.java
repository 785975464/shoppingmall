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

//	@Test  //测试Hibernate的开发环境，因为没有整合，可以直接new  
//    public void hihernate() {  
//        CategoryService categoryService = new CategoryServiceImpl();  
//        Category category = new Category("女士正装", true);  
//        categoryService.save(category);  
//    }
	

	
//	@Test //测试Hibernate和Spring整合后  
//    public void hibernateAndSpring() {  
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//		categoryService = (CategoryService) ctx.getBean("categoryService");
//        categoryService.update(new Category(1, "休闲女式", true)); //categoryService通过Spring从上面注入进来的  
//    }
}
