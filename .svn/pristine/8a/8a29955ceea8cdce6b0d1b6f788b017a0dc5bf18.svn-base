package com.hzxy.robot.service;

import com.hzxy.robot.dao.PermissionMapper;
import com.hzxy.robot.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionMapper {
    @Autowired
    private  PermissionMapper permissionMapper;

    @Override
    public List<SysPermission> getAllPermission() {
        return permissionMapper.getAllPermission();
    }

    @Override
    public List<SysPermission> getPermissionbyUsername(String username) {
        return permissionMapper.getPermissionbyUsername(username);
    }
}
