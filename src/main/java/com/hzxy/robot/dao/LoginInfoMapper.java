package com.hzxy.robot.dao;

import com.hzxy.robot.entity.LoginInfo;

import java.util.List;

public interface LoginInfoMapper {
    List<LoginInfo> getLoginInfo();
    void insertLoginLog(LoginInfo loginInfo);

    void deleteLoginLogs(String infoId);

    List<LoginInfo> selectByConditon(LoginInfo loginInfo);
}
