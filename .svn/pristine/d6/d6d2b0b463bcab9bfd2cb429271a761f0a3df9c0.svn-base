package com.gwamcc.aii.forms;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser extends DefaultForm implements UserDetails,Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String username;
	private String password;
	private boolean accountNonExpired;//账户失效
	private boolean accountNonLocked;//账户锁定
	private boolean credentialsNonExpired;//凭证失效
	private boolean enabled;//启用
	private Collection<? extends GrantedAuthority> authorities;
	private Collection<Integer> menus;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}



	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public LoginUser setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
		return this;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public LoginUser setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
		return this;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public LoginUser setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
		return this;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public LoginUser setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public LoginUser setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
		return this;
	}


	public Collection<Integer> getMenus() {
		return menus;
	}
	public LoginUser setMenus(Collection<Integer> menus) {
		this.menus = menus;
		return this;
	}

	public String getUsername() {
		return username;
	}
	public LoginUser setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public LoginUser setPassword(String password) {
		this.password = password;
		return this;
	}
}
