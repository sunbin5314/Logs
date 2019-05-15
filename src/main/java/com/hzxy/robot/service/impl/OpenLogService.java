package com.hzxy.robot.service.impl;

import com.hzxy.robot.entity.OpenLog;

import java.util.List;

public interface OpenLogService {

    public void insertOpenLog(OpenLog openLog);

    List<OpenLog> findAllOpenLogs();

    void deleteOpenLogs(String[] operIds);

    List<OpenLog> selectByConditon(OpenLog openLog);
}
