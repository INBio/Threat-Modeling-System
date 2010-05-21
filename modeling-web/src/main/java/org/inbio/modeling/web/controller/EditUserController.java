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
package org.inbio.modeling.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.modeling.core.manager.UserManager;
import org.inbio.modeling.core.user.SystemUser;
import org.inbio.modeling.web.form.UserForm;
import org.springframework.security.GrantedAuthority;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class EditUserController extends AbstractFormController {

    private UserManager userManager;

	/** default behavior for direct access (url) */
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors)
												throws Exception {
		UserForm userForm = (UserForm)command;
		SystemUser systemUser = null;

		systemUser =
			(SystemUser) userManager.loadUserByUsername(userForm.getUsername());

		userForm = new UserForm();

		userForm.setUserId(systemUser.getUserId());
		userForm.setUsername(systemUser.getUsername());
		userForm.setFullname(systemUser.getFullname());
		userForm.setEnabled(systemUser.isEnabled());

		GrantedAuthority[] authorities = systemUser.getAuthorities();

		for(int i = 0; i < authorities.length; i++)
			if(authorities[i].getAuthority().equals("ROLE_ADMIN"))
				userForm.setAdmin(true);
			else
				userForm.setUser(true);

		ModelAndView model = null;

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("userDetails");
		model.addObject("userForm", userForm);

        return model;
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception {

		return new ModelAndView("index");
	}

	/* getters & setters */
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}