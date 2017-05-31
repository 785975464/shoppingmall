package com.zcy.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zcy.shop.model.User;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
      
    public String update() {  
        System.out.println("----update----");  
//        System.out.println(categoryService);
        userService.update(model);
        return "index";  
    }  
      
    public String save() {  
        System.out.println("----save----");  
//        System.out.println(categoryService);//整合前后输出不同 
        System.out.println(model); 
//      categoryService.save(model);
        return "index";  
    }
    
    public String query() {  
        request.put("userList", userService.query());   
        session.put("userList", userService.query());   
        application.put("userList", userService.query());   
        return "index";  
    }
    
    public String login() {  
//    	System.out.println("userAction login!");
        //进行登陆的判断  
        User user = userService.getLoginUser(model);  
        if(user == null) {  
            session.put("error", "登陆失败");  
            return "login";  
        } else {  
            //登录成功，先将用户存储到session中  
            session.put("user", user);  
            //根据session中goURL是否有值而决定页面的跳转  
            if(session.get("goURL") == null) {  
                return "index"; //跳到首页  
            } else {  
                return "goURL";  
            }  
        }  
    } 
}
