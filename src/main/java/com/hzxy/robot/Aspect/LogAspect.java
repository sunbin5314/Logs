package com.hzxy.robot.Aspect;

import com.hzxy.robot.annotation.Log;
import com.hzxy.robot.entity.OpenLog;
import com.hzxy.robot.service.OpenLogServiceImpl;
import com.hzxy.robot.utils.MyUUIDUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 操作日志切面类
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private OpenLogServiceImpl openLogService;
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    //配置切入点
    @Pointcut("@annotation(com.hzxy.robot.annotation.Log)")
    public void logPointCut(){}
    /**
     * @param joinPoint 切点
     */
    @AfterReturning("logPointCut()")
    public void doServiceBefore(JoinPoint joinPoint){
        //操作日志实体
        OpenLog openLog = new OpenLog();
        HttpServletRequest request =
                ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        //用户ID
        String id = MyUUIDUtils.getUUID();
        openLog.setOperId(id);
        //获取用户名
        String currentUser = (String) SecurityUtils.getSubject().getPrincipal();
        //用户名
        openLog.setOperName(currentUser);
        System.out.println("currentUser = " + currentUser);
        //请求的IP
        String ip = request.getRemoteAddr();
        openLog.setOperIp(ip);
        /**
         * 请求方法的参数
         * @param joinPoint
         * @return
         */
           String params = Arrays.toString(joinPoint.getArgs());
           openLog.setOperParam(params);
        //请求路径
        String url = request.getRequestURI();
        openLog.setOperUrl(url);
        //操作模块
        String title = getServiceMthodType(joinPoint);
        openLog.setTitle(title);
        //操作时间
        Date date = new Date();
        openLog.setOperTime(date);
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //浏览器信息
        String browser = userAgent.getBrowser().toString();
        //操作系统
        String os = userAgent.getOperatingSystem().toString();
        //请求的方法
        String methodName = (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() +
                "()");
        //请求方法
        openLog.setMethod(methodName);
            log.info("=====service前置通知开始,方法"+methodName+"======");
            log.info("请求方法:"+(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() +
                    "()"));
            log.info("操作模块:"+title);
            log.info("参数:"+params);
            log.info("请求Ip:"+ip);
            log.info("请求的用户:"+ currentUser);
            log.info("请求路径:"+url);
            log.info("操作时间:"+date);
            log.info("浏览器类型:"+os);
            log.info("浏览器类型:"+browser);
            //把日志添加到数据库
            openLogService.insertOpenLog(openLog);
    }
    /**
     * 操作类型，用于区分操作
     */
    private static String getServiceMthodType(JoinPoint joinPoint){
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert targetClass != null;
        Method[] methods = targetClass.getMethods();
        String type = "b";
        for(Method method : methods){
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    type = method.getAnnotation(Log.class).type();
                    break;
                }
            }
        }
        return type;
    }
}
