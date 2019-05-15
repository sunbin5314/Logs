package com.hzxy.robot.service.impl;


import com.hzxy.robot.entity.SysPermission;

import java.util.List;

public interface PermissionService {

    public List<SysPermission> getAllPermission();

    public List<SysPermission> getPermissionbyUsername(String username);

}
