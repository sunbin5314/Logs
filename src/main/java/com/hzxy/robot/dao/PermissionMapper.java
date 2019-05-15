package com.hzxy.robot.dao;

import com.hzxy.robot.entity.SysPermission;

import java.util.List;

public interface PermissionMapper {

    public List<SysPermission> getAllPermission();

    public List<SysPermission> getPermissionbyUsername(String username);

}
