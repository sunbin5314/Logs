package com.hzxy.robot.service;

import com.hzxy.robot.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzxy.robot.dao.UserMapper;
import com.hzxy.robot.service.impl.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserInfo getUserbyName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.getUserbyName(userName);
	}

	@Override
	public String getPasswordbyName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.getPasswordbyName(userName);
	}

}
