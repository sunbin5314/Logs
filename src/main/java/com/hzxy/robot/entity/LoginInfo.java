package com.hzxy.robot.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginInfo implements Serializable {
    private String infoId;
    private String loginName;
    //ip地址
    private String ipaddr;
    //浏览器类型
    private String browser;
    //操作系统
    private String os;
    //登录时间
    private Date loginTime;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }


    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "infoId=" + infoId +
                ", loginName='" + loginName + '\'' +
                ", ipaddr='" + ipaddr + '\'' +
                ", browser='" + browser + '\'' +
                ", os='" + os + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
