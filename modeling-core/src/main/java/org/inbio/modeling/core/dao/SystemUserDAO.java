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
package org.inbio.modeling.core.dao;

import java.util.List;
import org.inbio.modeling.core.user.SystemUser;

/**
 * code taken from the m3s application
 * @author jgutierrez
 * @author asanabria
 *
 */
public interface SystemUserDAO extends BaseDAO {


	/**
	 * create a new user in the database
	 * @param newUser
	 */
	public void createUser(SystemUser newUser);

	/**
	 * delete an user that is in the database
	 * @param id
	 */
	public void deleteUserByUsername(String userName);

	/**
	 * Updates the information of a user
	 * @param user
	 */
	public void updateUser(SystemUser user);

	/**
	 * Find an user by its username
	 * @param username
	 * @return
	 */
	public SystemUser findByUsername(String username);

	/**
	 * Return all the users in the database
	 * @return
	 */
	public List<SystemUser> findAll();

}
