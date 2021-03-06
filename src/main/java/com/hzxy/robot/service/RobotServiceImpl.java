package com.hzxy.robot.service;

import com.hzxy.robot.dao.RobotMapper;
import com.hzxy.robot.entity.RobotInfo;
import com.hzxy.robot.service.impl.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    private RobotMapper robotMapper;


    @Override
    public List<RobotInfo> getRobots() {

        return robotMapper.getRobots();
    }

    @Override
    public void addRobot(RobotInfo robot) {
        robotMapper.addRobot(robot);
    }

    @Override
    public void delRobot(RobotInfo robot) {
        robotMapper.deleteRobot(robot);
    }

    @Override
    public void modRobot(RobotInfo robot) {
        robotMapper.modRobot(robot);
    }
}
