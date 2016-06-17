package com.gwamcc.aii.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.LoginUserDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.LoginUser;
import com.gwamcc.aii.forms.UserForm;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;

import config.Res;

/**
 * 数据字典操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class LoginUserDaoImpl extends DefaultDao implements LoginUserDao {

	@Override
	public LoginUser loadUserByUsername(String username)throws UsernameNotFoundException {
		//查询用户信息

		List<DefaultForm> user=null;
		try {
			user =queryList(UserForm.class,new ConditionDef(new Object[][]{
				{"loginName=:username"},
				{"CanDo.rightid in(:right)"}
			}),new ParamMap().put("username", username)
					.put("right", Res.RIGHTID.ALL));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(user==null||user.isEmpty()||user.size()<1){
			throw new UsernameNotFoundException("用户["+username+"]认证失败!");
		}

		Set<Integer>menus=new HashSet<Integer>();

		for(DefaultForm u:user){
			if (u instanceof UserForm) {
				UserForm um = (UserForm) u;
				menus.add(Res.MENU.GET_BY_ID(um.getRightId()));
			}
		}

		UserForm info=(UserForm)user.get(0);

		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        auths.add(grantedAuthority);
		LoginUser loginUser=new LoginUser();
		loginUser.setUsername(info.getLoginName())
			.setPassword(info.getLoginName())
			.setAccountNonExpired(true)
			.setAccountNonLocked(true)
			.setCredentialsNonExpired(true)
			.setEnabled(true)
			.setAuthorities(auths)
			.setMenus(menus)
			.setUserid(info.getUserId());
		return loginUser;
	}
}
