package com.hzxy.robot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzxy.robot.entity.OpenLog;
import com.hzxy.robot.service.impl.OpenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * 操作日志查询
 */
@Controller
@RequestMapping("/openLog")
public class OpenLogController {
    @Autowired
    private OpenLogService openLogService;
    @RequestMapping("/pageList")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<OpenLog> openLogs = openLogService.findAllOpenLogs();
        //需要把Page包装成PageInfo对象才能序列化。该插件默认实现了一个PageInfo，也可自己实现一个分页数据类pageinfo
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo<OpenLog> pageInfo = new PageInfo<OpenLog>(openLogs);
        model.addAttribute("pageInfo",pageInfo);
        return "log/openLogList";
    }
    //日志列表批量删除数据
    @RequestMapping("/deleteLogs")
    public String deleteOpenLogs(String[] operIds){
        openLogService.deleteOpenLogs(operIds);
        return "redirect:/openLog/pageList";
    }
    //条件查询列表批量删除
    @RequestMapping("/deleteLogsCondition")
    public String deleteOpenLogsCondition(String[] operIds){
        openLogService.deleteOpenLogs(operIds);
        return "redirect:/openLog/selectByConditon";
    }
    //条件查询
    @RequestMapping("/selectByConditon")
    public String selectByConditon(Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize,OpenLog openLog){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<OpenLog> openLogs = openLogService.selectByConditon(openLog);
        PageInfo<OpenLog> pageInfo = new PageInfo<OpenLog>(openLogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("openLog",openLog);
        return "log/openLogListCondotion";
    }
}
