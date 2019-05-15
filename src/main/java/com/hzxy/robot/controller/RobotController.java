package com.hzxy.robot.controller;

import com.hzxy.robot.annotation.Log;
import com.hzxy.robot.entity.RobotInfo;
import com.hzxy.robot.entity.UserInfo;
import com.hzxy.robot.service.RobotServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hzxy.robot.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/robotInfo")
public class RobotController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private RobotServiceImpl robotService;

	@ResponseBody
	@RequestMapping("/all")
	public UserInfo list(String userName) {
		UserInfo user = userServiceImpl.getUserbyName(userName);
		System.out.println(user.getUsername());
		return user;
	}

	/**
	 * 机器人查询.
	 * @return
	 */
	@RequestMapping("/robotList")
	@ResponseBody
	//@Log(type = "机器人查询")
//	@RequiresPermissions("robotInfo:view")//权限管理;
	public List<RobotInfo> getRobots(){

		System.out.println("机器人查询");
		return robotService.getRobots();
	}

	/**
	 * 机器人修改;
	 * @return
	 */
	@RequestMapping(value = "/robotMod" ,method = RequestMethod.POST)
//	@RequiresPermissions("robotInfo:add")//权限管理;
	@ResponseBody
	@Log(type = "机器人修改")
	public void robotMod(@RequestBody RobotInfo robot){
		System.out.println(robot.toString());
		robotService.modRobot(robot);
	}

	/**
	 * 机器人添加;
	 * @return
	 */
	@RequestMapping(value = "/robotAdd" ,method = RequestMethod.POST)
//	@RequiresPermissions("robotInfo:add")//权限管理;
	@ResponseBody
	@Log(type = "机器人添加")
	public void robotAdd(@RequestBody RobotInfo robot){
		System.out.println(robot.toString());
		robotService.addRobot(robot);
	}

	/**
	 * 机器人删除;
	 * @return
	 */
	@RequestMapping(value = "/robotDel" ,method = RequestMethod.POST)
//	@RequiresPermissions("robotInfo:del")//权限管理;
	@ResponseBody
	@Log(type = "机器人删除")
	public void userDel(@RequestBody  List<RobotInfo> robot){
		System.out.println("robot  = " + robot.toString());
		for(int i=0; i<robot.size(); i++){
			robotService.delRobot(robot.get(i));
		}

	}
}
