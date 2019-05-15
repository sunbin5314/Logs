package com.hzxy.robot.service.impl;

import com.hzxy.robot.entity.RobotInfo;

import java.util.List;

public interface RobotService {
    public List<RobotInfo> getRobots();

    public void addRobot(RobotInfo robot);

    public void delRobot(RobotInfo robot);

    public void modRobot(RobotInfo robot);

}
