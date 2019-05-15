package com.hzxy.robot.dao;

import com.hzxy.robot.entity.OpenLog;

import java.util.List;

public interface OpenLogMapper {
    void insertOpenLog(OpenLog openLog);

    List<OpenLog> findAllOpenLogs();

    void deleteOpenLogs(String operId);

    List<OpenLog> selectByConditon(OpenLog openLog);
}
