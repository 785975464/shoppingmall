package com.zcy.shop.service;

import com.zcy.shop.model.User;


public interface UserService extends BaseService<User> {
	public User getLoginUser(User user);
}
