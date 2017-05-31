package com.zcy.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zcy.shop.model.Category;
import com.zcy.shop.model.User;
import com.zcy.shop.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	public User getLoginUser(User user) {
		// TODO Auto-generated method stub
//		System.out.println("UserServiceImpl getLoginUser!");
//		System.out.println("user.getUsername():"+user.getUsername()+" &user.getPassword():"+user.getPassword());
		String hql = "from User u where u.username=:username and u.password=:password";  
        List<User> list = getSession().createQuery(hql)  
            .setString("username", user.getUsername()) 
            .setString("password", user.getPassword())
            .list();
        if(list.size()>0){
        	return list.get(0);
        }
        else{
        	return null;
        }
//        return (User) getSession().createQuery(hql) //  
//        .setString("username", user.getUsername()) //  
//        .setString("password", user.getPassword()) //  
//        .uniqueResult();
	}
}
