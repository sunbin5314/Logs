package com.hzxy.robot.dao;

import java.util.List;

import com.hzxy.robot.entity.SysRole;

public interface RoleMapper {

	public List<SysRole> getRolesByUserName(String userName);
}
