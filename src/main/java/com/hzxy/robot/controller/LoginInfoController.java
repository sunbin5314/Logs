package com.hzxy.robot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzxy.robot.entity.LoginInfo;
import com.hzxy.robot.service.impl.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 登录日志查询
 */
@Controller
@RequestMapping("/loginLog")
public class LoginInfoController {
    @Autowired
    private LoginInfoService loginInfoService;
    @RequestMapping("/pageList")
   //@Log(type = "登录日志查询")
    public String getLoginInfo(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<LoginInfo> loginInfos = loginInfoService.getLoginInfo();
        //需要把Page包装成PageInfo对象才能序列化。该插件默认实现了一个PageInfo，也可自己实现一个分页数据类pageinfo
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo<LoginInfo> pageInfo = new PageInfo<LoginInfo>(loginInfos);
        model.addAttribute("pageInfo",pageInfo);
        return "log/loginLogList";
    }
    @RequestMapping("/deleteLoginLogs")
    public String deleteLoginLogs(String[] infoIds){
        loginInfoService.deleteLoginLogs(infoIds);
        return "redirect:/loginLog/pageList";
    }
    @RequestMapping("/selectByConditon")
    public String selectByConditon(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize,LoginInfo loginInfo){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<LoginInfo> loginInfos = loginInfoService.selectByConditon(loginInfo);
        //需要把Page包装成PageInfo对象才能序列化。该插件默认实现了一个PageInfo，也可自己实现一个分页数据类pageinfo
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo<LoginInfo> pageInfo = new PageInfo<LoginInfo>(loginInfos);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("loginInfo",loginInfo);
        return "log/loginLogListCondition";
    }
}
