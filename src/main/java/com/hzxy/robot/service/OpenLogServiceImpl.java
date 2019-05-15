package com.hzxy.robot.service;

import com.hzxy.robot.dao.OpenLogMapper;
import com.hzxy.robot.entity.OpenLog;
import com.hzxy.robot.service.impl.OpenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenLogServiceImpl implements OpenLogService {
    @Autowired
    private OpenLogMapper openLogMapper;
    @Override
    public void insertOpenLog(OpenLog openLog) {
        openLogMapper.insertOpenLog(openLog);
    }

    @Override
    public List<OpenLog> findAllOpenLogs() {
        return openLogMapper.findAllOpenLogs();
    }

    @Override
    public void deleteOpenLogs(String[] operIds) {
        for (String operId : operIds) {
            openLogMapper.deleteOpenLogs(operId);
        }
    }

    @Override
    public List<OpenLog> selectByConditon(OpenLog openLog) {
        return openLogMapper.selectByConditon(openLog);
    }


}
