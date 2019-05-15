package com.hzxy.robot.config;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.hzxy.robot.entity.LoginInfo;
import com.hzxy.robot.entity.SysPermission;
import com.hzxy.robot.service.LoginInfoServiceImpl;
import com.hzxy.robot.service.PermissionServiceImpl;
import com.hzxy.robot.service.impl.LoginInfoService;
import com.hzxy.robot.utils.MyUUIDUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzxy.robot.entity.SysRole;
import com.hzxy.robot.entity.UserInfo;
import com.hzxy.robot.service.RoleServiceImpl;
import com.hzxy.robot.service.UserServiceImpl;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/*
 * 自定义类继承AuthorizingRealm，为当前subject
doGetAuthorizationInfo（授权）和doGetAuthenticationInfo（登陆认证）
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private PermissionServiceImpl permissionService;
	@Autowired
	private LoginInfoServiceImpl loginInfoService;
	/**
	 * 为当前subject授权
	 * @return AuthorizationInfo
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("为当前subject授权-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username =(String) principals.getPrimaryPrincipal();
		System.out.println("授权 user " +username);
		List<SysRole> roles = roleService.getRolesByUserName(username);//获取角色
		List<SysPermission> permissions = permissionService.getPermissionbyUsername(username);//获取权限
		for(SysRole role : roles){
			authorizationInfo.addRole(role.getRole());//注入角色
		}
		for(SysPermission permission : permissions){
			System.out.println("permission ； "+ permission.getPermission());
			authorizationInfo.addStringPermission(permission.getPermission());//注入权限
		}
		return authorizationInfo;
	}

	/**
	 * 认证（验证）登录subject身份
	 * 
	 * @return AuthenticationInfo
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("---------------开始登录认证---------------");
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		System.out.println("token.username=" + username);
		System.out.println(token.getCredentials().toString());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		UserInfo user = userService.getUserbyName(username);
		System.out.println("password: "+user.getPassword());
		if (user == null) {
//			throw new UnknownAccountException("用户名或密码错误");
            return null;
		}else {
			//添加登录日志
			//获取request对象
			HttpServletRequest request =
					((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			//获取登录日志对象
			LoginInfo loginInfo = new LoginInfo();
			//登录日志Id
			String Id = MyUUIDUtils.getUUID();
			loginInfo.setInfoId(Id);
			//登录时间
			loginInfo.setLoginTime(new Date());
			//登录名
			loginInfo.setLoginName(username);
			//登录的IP
			String ip = request.getRemoteAddr();
			loginInfo.setIpaddr(ip);
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			//浏览器信息
			String browser = userAgent.getBrowser().toString();
			loginInfo.setBrowser(browser);
			//操作系统
			String os = userAgent.getOperatingSystem().toString();
			loginInfo.setOs(os);
			loginInfoService.insertLoginLog(loginInfo);
		}
		//SimpleAccount没有对密码加密  此处会对账号和密码进行验证
		AuthenticationInfo authenticationInfo = new SimpleAccount(
				username, // 用户名
				user.getPassword(), // 密码
//				ByteSource.Util.bytes("2w@W"), // salt=username+salt 盐加密
				getName() // realm name
		);
//		Session session = SecurityUtils.getSubject().getSession();
//		session.setAttribute("user", user);
		return authenticationInfo;
	}

	/** * 清理缓存权限 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}

}
