package com.zcy.test;




import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcy.shop.model.Category;
import com.zcy.shop.service.CategoryService;
import com.zcy.shop.service.impl.CategoryServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class SSHTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
//	@Resource
//    private Date date;  
//	
//      
//    @Test //����Spring IOC�Ŀ�������  
//    public void springIoc() {  
//        System.out.println(date);  
//    }  
	
//	@Test  //����Hibernate�Ŀ�����������Ϊû����ϣ�����ֱ��new  
//  public void hihernate() {  
//      CategoryService categoryService = new CategoryServiceImpl();  
//      Category category = new Category("Ůʿ����", true);  
//      categoryService.save(category);  
//  }
	
	@Resource  
    private CategoryService categoryService;
	
	@Test //����Hibernate��Spring��Ϻ�  
    public void hibernateAndSpringSaveMethodTest() {  
        categoryService.save(new Category("��ͯ��װ", true)); //categoryServiceͨ��Spring������ע�������  
    }
	
//	@Test //����Hibernate��Spring��Ϻ�  
//    public void hibernateAndSpring() {  
//        categoryService.update(new Category(1, "Ůʽ����", true)); //categoryServiceͨ��Spring������ע�������  
//    }
}
