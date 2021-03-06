package com.hzxy.robot.config;

import com.hzxy.robot.dao.PermissionMapper;
import com.hzxy.robot.entity.SysPermission;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

@Configuration
public class ShiroConfig {
	@Autowired
	private PermissionMapper permissionMapper;

	/**
	 * 凭证匹配器
	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * ）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}
	//加入自己的验证方式
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
//		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		System.out.println("myShiroRealm 注册完成");
		return myShiroRealm;
	}

	@Bean

	public Authorizer authorizer(MyShiroRealm myRealm){
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		Collection<Realm> crealms = new ArrayList<>();
		crealms.add(myRealm);
		authorizer.setRealms(crealms);
		return authorizer;
	}



	@Bean
	public Authenticator authenticator(MyShiroRealm myRealm){
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		Collection<Realm> crealms = new ArrayList<>();
		crealms.add(myRealm);
		authenticator.setRealms(crealms);
		return authenticator;
	}


	//自己的权限管理
	@Bean
	public DefaultWebSecurityManager  securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		MyShiroRealm realm = myShiroRealm();
		securityManager.setAuthenticator(authenticator(realm));
		securityManager.setAuthorizer(authorizer(realm));
		securityManager.setRealm(myShiroRealm());
		System.out.println("securityMagger 注册完成");
		return securityManager;
	}



	//Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断     拦截的是静态文件的访问
		filterChainDefinitionMap.put("/statics/**", "anon");
//		filterChainDefinitionMap.put("/login_in", "anon");
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");

		//就说明访问/add这个链接必须要有“权限添加”这个权限和具有“100002”这个角色才可以访问。
		//filterChainDefinitionMap.put("/robotInfo/robotDel", "roles[100002]，perms[权限添加]");
		List<SysPermission> permissionList = permissionMapper.getAllPermission();
		for (SysPermission p : permissionList) {
			filterChainDefinitionMap.put(p.getUrl(), "perms[" + p.getPermission() + "]");    //拦截器中注册所有的权限
		}
//		 filterChainDefinitionMap.put("/robotInfo/robotDel","perms[robotInfo:del]");
		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");


		//解决登录验证通过后无法跳转successurl的问题
		Map map = new LinkedHashMap();
		map.put("authc", new MyFormAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(map);

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		System.out.println("shrioURL"+shiroFilterFactoryBean.getLoginUrl());
		// 登录成功后要跳转的页面链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 访问当前用户未授权界面跳转到403;
			shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		System.out.println("shiroFilter 注册完成");
		return shiroFilterFactoryBean;
	}

	/**
	 * 开启shiro 注解支持. 使以下注解能够生效 :
	 * 需要认证 {@link org.apache.shiro.authz.annotation.RequiresAuthentication RequiresAuthentication}
	 * 需要用户 {@link org.apache.shiro.authz.annotation.RequiresUser RequiresUser}
	 * 需要访客 {@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}
	 * 需要角色 {@link org.apache.shiro.authz.annotation.RequiresRoles RequiresRoles}
	 * 需要权限 {@link org.apache.shiro.authz.annotation.RequiresPermissions RequiresPermissions}
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator app=new DefaultAdvisorAutoProxyCreator();
		app.setProxyTargetClass(true);
		return app;

	}

//	@Bean
//	public ShiroDialect shiroDialect() {
//		return new ShiroDialect();
//	}
}
