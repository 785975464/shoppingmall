package com.zcy.shop.action;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcy.shop.model.Product;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
    
	private Map<String, Object> pageMap;
	public Map<String, Object> getPageMap(){
		return this.pageMap;
    }
	
	public String query() {  
    	pageMap = new HashMap<String, Object>();
    	List<Product> list = productService.query();
    	pageMap.put("data", list);						//存放在pageMap中，由struts解析成json
    	pageMap.put("recordsTotal", list.size());
    	pageMap.put("recordsFiltered", list.size());
        return "jsonMap";  
    }
	
    public String delete() {
    	productService.delete(model.getId());
        inputStream = new ByteArrayInputStream("success".getBytes()); //将"true"的字节存到流inputStream中
		return "stream";  
    }
      
    public String save(){
    	//fileUpload工具类被抽取了，uploadFile方法直接接受一个fileImage对象，返回新的图片名  
        String pic = fileUpload.uploadFile(fileImage);
        model.setPic(pic);
    	model.setDate(new Timestamp(new Date().getTime()));		//将当前时间转为timestamp
    	productService.save(model);
    	inputStream = new ByteArrayInputStream("success".getBytes()); //将"true"的字节存到流inputStream中
		return "stream";
    }
    
    public String edit() {
//    	System.out.println("edit model:"+model.getId());
    	pageMap = new HashMap<String, Object>();
    	model = productService.get(model.getId());
    	pageMap.put("name", model.getName());
    	pageMap.put("price", model.getPrice());
    	pageMap.put("pic", model.getPic());
    	pageMap.put("cid", model.getCid());
    	pageMap.put("commend", model.getCommend());
    	pageMap.put("open", model.getOpen());
    	pageMap.put("remark", model.getRemark());
    	pageMap.put("xremark", model.getXremark());
    	pageMap.put("date", model.getDate());
		return "jsonMap";  
    }
    
    public String update(){
    	System.out.println("update model:"+model.getId());
    	String pic;
    	if(fileImage==null || fileImage.equals("")){
    		pic = model.getPic();
    	}
    	else{
    		pic = fileUpload.uploadFile(fileImage);
    	}
        model.setPic(pic);
    	productService.update(model);
    	inputStream = new ByteArrayInputStream("success".getBytes()); //将"true"的字节存到流inputStream中
		return "stream";
    }
}
