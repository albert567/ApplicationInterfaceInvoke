package com.gwamcc.aii.dao;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gwamcc.aii.forms.LoginUser;

public interface LoginUserDao {

	LoginUser loadUserByUsername(String userName) throws UsernameNotFoundException;

}
