package com.hzxy.robot.dao;

import com.hzxy.robot.entity.RobotInfo;

import java.util.List;

public interface RobotMapper {

    public List<RobotInfo> getRobots();


    public void addRobot(RobotInfo robot);

    public void deleteRobot(RobotInfo robot);

    public void modRobot(RobotInfo robot);
}
