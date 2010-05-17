/* Modeling - Application to model threats.
 *
 * Copyright (C) 2010  INBio (Instituto Nacional de Biodiversidad)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.modeling.core.user;


import java.util.Date;
import java.util.StringTokenizer;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

/**
 * @author jgutierrez
 * @author asanabria
 *
 */
public class SystemUser implements UserDetails {

	private Integer userId;
	private String username;
	private String fullname;
	private String password;
	private boolean enabled;
	private String roles;
	private String ROLE_DELIMITER = ",";

	/**
	 * 
	 */
	public SystemUser() {
		super();
	}
	
	
	/**
	 * @param userId
	 * @param username
	 * @param fullname
	 * @param password
	 * @param enabled
	 * @param roles
   * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public SystemUser(Integer userId, String username, String fullname, 
			String password, boolean enabled, String roles, 
			Date creationDate, String createdBy, Date lastModificationDate, 
			String lastModificationBy) {
		this.userId = userId;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public GrantedAuthority[] getAuthorities() {
		
		StringTokenizer st = new StringTokenizer(this.getRoles(), ROLE_DELIMITER);
		GrantedAuthorityImpl[] grantedAuthorityImplArray = new GrantedAuthorityImpl[st.countTokens()];
		
		for(int i =0; st.hasMoreElements();i++)
			grantedAuthorityImplArray[i] = new GrantedAuthorityImpl(st.nextToken());
		
		return grantedAuthorityImplArray;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SystemUser [enabled=" + enabled + ", fullname=" + fullname
				+ ", password=" + password + ", roles="
				+ roles + ", userId=" + userId + ", username=" + username + "]";
	}	

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return this.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return this.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return this.isEnabled();
	}


	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return this.username;
	}	
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return this.password;
	}	
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
