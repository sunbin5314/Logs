package com.hzxy.robot.service;

import com.hzxy.robot.dao.LoginInfoMapper;
import com.hzxy.robot.entity.LoginInfo;
import com.hzxy.robot.service.impl.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginInfoServiceImpl implements LoginInfoService {
    @Autowired
    private LoginInfoMapper loginInfoMapper;
    @Override
    public List<LoginInfo> getLoginInfo() {
        return loginInfoMapper.getLoginInfo();
    }

    @Override
    public void insertLoginLog(LoginInfo loginInfo) {
        loginInfoMapper.insertLoginLog(loginInfo);
    }

    @Override
    public void deleteLoginLogs(String[] infoIds) {
        for (String infoId : infoIds) {
            loginInfoMapper.deleteLoginLogs(infoId);
        }
    }

    @Override
    public List<LoginInfo> selectByConditon(LoginInfo loginInfo) {
        return loginInfoMapper.selectByConditon(loginInfo);
    }
}
