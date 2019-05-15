package com.hzxy.robot.service.impl;

import java.util.List;

import com.hzxy.robot.entity.SysRole;

public interface RoleService {

	public List<SysRole> getRolesByUserName(String userName);
}
