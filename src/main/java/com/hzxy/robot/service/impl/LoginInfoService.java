package com.hzxy.robot.service.impl;

import com.hzxy.robot.entity.LoginInfo;

import java.util.List;

public interface LoginInfoService {

    List<LoginInfo> getLoginInfo();

    void insertLoginLog(LoginInfo loginInfo);

    void deleteLoginLogs(String[] infoIds);

    List<LoginInfo> selectByConditon(LoginInfo loginInfo);
}
