package com.hzxy.robot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzxy.robot.dao.RoleMapper;
import com.hzxy.robot.entity.SysRole;
import com.hzxy.robot.service.impl.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<SysRole> getRolesByUserName(String userName) {
		// TODO Auto-generated method stub
		return roleMapper.getRolesByUserName(userName);
	}

}
