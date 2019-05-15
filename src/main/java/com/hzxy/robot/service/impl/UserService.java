package com.hzxy.robot.service.impl;

import com.hzxy.robot.entity.UserInfo;

public interface UserService {

	public UserInfo getUserbyName(String userName);

	public String getPasswordbyName(String userName);
}
