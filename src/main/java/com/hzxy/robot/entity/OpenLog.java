package com.hzxy.robot.entity;

import java.io.Serializable;
import java.util.Date;

public class OpenLog implements Serializable {
    private String operId;
    //操作模块
    private String title;
    //请求方法
    private String method;
    //操作人员
    private String operName;
    //请求地址
    private String operUrl;
    //操作地址
    private String operIp;
    //请求参数
    private String operParam;
    //操作时间
    private Date operTime;

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    @Override
    public String toString() {
        return "OpenLog{" +
                "operId='" + operId + '\'' +
                ", title='" + title + '\'' +
                ", method='" + method + '\'' +
                ", operName='" + operName + '\'' +
                ", operUrl='" + operUrl + '\'' +
                ", operIp='" + operIp + '\'' +
                ", operParam='" + operParam + '\'' +
                ", operTime=" + operTime +
                '}';
    }
}
