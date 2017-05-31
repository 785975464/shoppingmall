package com.zcy.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcy.shop.utils.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.zcy.shop.model.Category;
import com.zcy.shop.service.CategoryService;

@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category> {
	
    private Map<String, Object> pageMap;
//    private String id;	//类的属性已在BaseAction中的getModel方法获取
//    private String type;
//    private String hot;
    
    public Map<String, Object> getPageMap(){
		return this.pageMap;
    }

	
    public String query() {  

    	pageMap = new HashMap<String, Object>();
    	List<Category> list = categoryService.query();
//    	System.out.println("list:"+((Category)list.get(0)).getType());
    	pageMap.put("data", list);						//存放在pageMap中，由struts解析成json
    	pageMap.put("recordsTotal", list.size());
    	pageMap.put("recordsFiltered", list.size());
//        String listjson = JsonUtil.listToJson(list);
//        String jsonstring="{\"data\":"+listjson+",\"draw\":\"1\",\"recordsTotal\":"+list.size()+",\"recordsFiltered\":"+list.size()+"}";
        return "jsonMap";  
    }
    
    public String delete() {
//    	String context = ServletActionContext.getRequest().getParameter("id");
//    	System.out.println("Category model:"+model.getType());
    	categoryService.delete(model.getId());
    	//如果删除成功就会往下执行，我们将"true"以流的形式传给前台  
        inputStream = new ByteArrayInputStream("success".getBytes()); //将"true"的字节存到流inputStream中
		return "stream";  
    	
    }

    public String save(){
    	categoryService.save(model);
    	inputStream = new ByteArrayInputStream("success".getBytes()); //将"true"的字节存到流inputStream中
		return "stream";
    }
    
    public String edit() {
//    	System.out.println("edit model:"+model.getId());
    	pageMap = new HashMap<String, Object>();
    	model = categoryService.get(model.getId());
    	pageMap.put("type", model.getType());
    	pageMap.put("hot", model.getHot());
		return "jsonMap";  
    	
    }

    
    public String update(){
    	System.out.println("update model:"+model.getId());
    	categoryService.update(model);
    	inputStream = new ByteArrayInputStream("success".getBytes()); //将"true"的字节存到流inputStream中
		return "stream";
    }
}
