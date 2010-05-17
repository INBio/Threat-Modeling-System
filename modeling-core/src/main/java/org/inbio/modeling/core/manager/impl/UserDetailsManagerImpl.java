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
package org.inbio.modeling.core.manager.impl;

import org.inbio.modeling.core.dao.SystemUserDAO;
import org.inbio.modeling.core.user.SystemUser;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * @author jgutierrez
 *
 */
public class UserDetailsManagerImpl implements UserDetailsManager {
	
	private SystemUserDAO systemUserDAO;

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#createUser(org.springframework.security.userdetails.UserDetails)
	 */
	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#updateUser(org.springframework.security.userdetails.UserDetails)
	 */
	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsManager#userExists(java.lang.String)
	 */
	@Override
	public boolean userExists(String username) {
		
		return systemUserDAO.findByUsername(username) != null;
			
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		SystemUser su = systemUserDAO.findByUsername(username);

		if(su==null)
			throw new UsernameNotFoundException("username does not exist in the database.");
		
		return su;

	}

	/**
	 * @return the systemUserDAO
	 */
	public SystemUserDAO getSystemUserDAO() {
		return systemUserDAO;
	}

	/**
	 * @param systemUserDAO the systemUserDAO to set
	 */
	public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
		this.systemUserDAO = systemUserDAO;
	}

}
