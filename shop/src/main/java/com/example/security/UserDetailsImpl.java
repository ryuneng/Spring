package com.example.security; // 20240307 Day12

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 6793590474713548621L;
	
	private String username;
	private String password;
	private String name;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(String username, String password, String name, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.authorities = authorities;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
