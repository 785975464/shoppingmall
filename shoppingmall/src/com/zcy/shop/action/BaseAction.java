package com.zcy.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zcy.shop.model.FileImage;
import com.zcy.shop.service.CategoryService;
import com.zcy.shop.service.OrderService;
import com.zcy.shop.service.ProductService;
import com.zcy.shop.service.SorderService;
import com.zcy.shop.service.StatusService;
import com.zcy.shop.service.UserService;
import com.zcy.shop.utils.EmailUtil;
import com.zcy.shop.utils.FileUpload;

@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,SessionAware,ApplicationAware,ModelDriven<T> {

	//service对象
	@Resource
	protected CategoryService categoryService;  
	@Resource
    protected UserService userService;  
	@Resource
	protected ProductService productService;
	@Resource  
    protected OrderService orderService;  
    @Resource  
    protected SorderService sorderService; 
    @Resource  
    protected StatusService statusService;
    
	//流是用来向前台返回数据的，这个数据是让struts获取的，然后通过流的形式传到前台，所以实现get方法即可
	protected InputStream inputStream;
	
	//封装了图片信息的类  
    protected FileImage fileImage;  
      
    //上传文件工具类  
    @Resource  
    protected FileUpload fileUpload;
    //邮箱工具类  
    @Resource  
    protected EmailUtil emailUtil;
  
    public FileImage getFileImage() {  
        return fileImage;  
    }  
    public void setFileImage(FileImage fileImage) {  
        this.fileImage = fileImage;  
    }
    
    public void setCategoryService(CategoryService categoryService) {  
        this.categoryService = categoryService;  
    }  
    public void setUserService(UserService userService) {  
        this.userService = userService;  
    }
    public void setProductService(ProductService productService) {  
        this.productService = productService;  
    }
    
    public InputStream getInputStream() {  
        return this.inputStream;  
    }
	
	
  //域对象
	protected Map<String, Object> request;  
    protected Map<String, Object> session;  
    protected Map<String, Object> application;
    
    
	public void setApplication(Map<String, Object> application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	//ModelDriven 
	protected T model;  
    
	public T getModel() {	//这里通过解析传进来的T来new一个对应的instance
		// TODO Auto-generated method stub
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();  
        Class clazz = (Class)type.getActualTypeArguments()[0];  
        try {  
            model = (T)clazz.newInstance();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }     
        return model;  
	}  

}
