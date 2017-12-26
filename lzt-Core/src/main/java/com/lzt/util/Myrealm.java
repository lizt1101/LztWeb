package com.lzt.util;

import javax.annotation.Resource;

import com.lzt.Bo.RolesIdsBo;
import com.lzt.service.PermissionService;
import com.lzt.service.RoleService;
import com.lzt.service.UserRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.lzt.entity.User;
import com.lzt.service.UserService;

import java.util.Set;

public class Myrealm extends AuthorizingRealm{
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private UserRoleService userRoleService;

	//权限验证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		RolesIdsBo rolesIdsBo = userRoleService.getRoles(user.getId());
		authorizationInfo.setRoles(rolesIdsBo.getRoleNames());
		Set<String> pers = permissionService.getPers(rolesIdsBo.getRoleIds());
		authorizationInfo.setStringPermissions(pers);
		return authorizationInfo;
	}

	//用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getUserByUsername(username);
		if(user != null){
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"lzt");
			SecurityUtils.getSubject().getSession().setAttribute("CurrentUser", user);
			return authenticationInfo;
		}else{	
			return null;
		}
	}

}
