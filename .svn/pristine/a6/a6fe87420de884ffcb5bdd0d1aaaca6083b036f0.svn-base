package com.gwamcc.aii.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.LoginUserDao;
import com.gwamcc.aii.forms.LoginUser;

 /* 角色服务实现类
 * @author 范大德
 *
 */
@Service
/*@CacheConfig(cacheNames="application")*/
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginUserDao dao;

	@Override
	public LoginUser loadUserByUsername(String userName)throws UsernameNotFoundException {
		return dao.loadUserByUsername(userName);
	}


}
