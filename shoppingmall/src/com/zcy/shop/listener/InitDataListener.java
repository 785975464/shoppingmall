package com.zcy.shop.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zcy.shop.model.Category;
import com.zcy.shop.model.Product;
import com.zcy.shop.service.CategoryService;
import com.zcy.shop.service.ProductService;
import com.zcy.shop.utils.FileUpload;
import com.zcy.shop.utils.ProductTimerTask;

//用于项目启动的时候数据初始化
//@Component //监听器是web层的组件，它是tomcat实例化的，不是Spring实例化的。不能放到Spring中
public class InitDataListener implements ServletContextListener {

//	private ProductService productService = null;//productService中定义了跟商品相关的业务逻辑 
//    private CategoryService categoryService = null;  
    private ProductTimerTask productTimerTask = null; //定义一个ProductTimerTask对象
    private ApplicationContext context = null; 
    private FileUpload fileUpload = null;
    
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
//		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());          
//        categoryService = (CategoryService) context.getBean("categoryService");//加载类别信息          
//        productService = (ProductService) context.getBean("productService");//加载商品信息  
//  
////        List<List> bigList = new ArrayList<List>(); //bigList中存放一个装有Category类的list
//        List<List<Product>> bigList = new ArrayList<List<Product>>(); //bigList中存放一个装有Category类的list  
//        List<Category> cList = new ArrayList<Category>();
//        cList = categoryService.queryByHot(true);
//        // 1. 查询出热点类别  
//        for(Category category : cList) {  
//            //根据热点类别id获取推荐商品信息  
//        	List<Product> lst = productService.querByCategoryId(category.getId());  
//            bigList.add(lst); //将装有category的list放到bigList中
//        }  
//        // 2. 把查询的bigList交给application内置对象  
//        event.getServletContext().setAttribute("bigList", bigList);
//        event.getServletContext().setAttribute("cList", cList);
		
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());                 
        productTimerTask = (ProductTimerTask) context.getBean("productTimerTask");//从配置文件中获取ProductTimerTask对象  
  
        //把内置对象交给productTimerTask,因为productTimerTask里面是拿不到application的，只能通过监听器set给它  
        productTimerTask.setApplication(event.getServletContext());  
  
        //通过设置定时器，让首页的数据每个一小时同步一次（配置为守护线程）  
        new Timer(true).schedule(productTimerTask, 0, 1000*300);//每个一小时执行一次productTimerTask任务，即更新一下后台数据
        
      //将存储银行图片的数组放到application中，项目启动的时候加载
        fileUpload = (FileUpload) context.getBean("fileUpload");
        event.getServletContext().setAttribute("bankImageList", fileUpload.getBankImage());
	}

}
