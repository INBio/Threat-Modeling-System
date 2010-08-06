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
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class UpdateUserInfoController extends AbstractFormController {

    private UserManager userManager;


	/** default behavior for direct access (url) */
	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request
												, HttpServletResponse response
												, Object command
												, BindException errors)
												throws Exception {

		UserForm userForm = (UserForm)command;
		String uri = request.getRequestURI();

		if(uri.matches(".*deleteUser.*")){
			this.deleteUser(userForm);
		}else if(uri.matches(".*updateUser.*")){
                    if(!userForm.getPassword1().equals(userForm.getPassword2()))
                    {
                        Exception ex = new Exception("errors.passwordNotMatch");
                        //Logger.getLogger(ColumnController.class.getName()).log(Level.SEVERE, null, ex);
                        errors.reject(ex.getMessage());
                        return showForm(request, response, errors);
                    }
                    else
                    {
			if ( userForm.getUserId() == null)
				this.newUser(userForm);
			else                        
                            this.updateUser(userForm);
                    }
		}

		ModelAndView model = null;

		// Send the layer list to the JSP
		model = new ModelAndView();
		model.setViewName("redirect:listUsers.html");

        return model;
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request
									, HttpServletResponse response
									, BindException errors)
									throws Exception
        {

		ModelAndView model = new ModelAndView();
                if(errors != null && errors.hasErrors())
                    model.addAllObjects(errors.getModel());

                model.setViewName("admin/userDetails");
                return model;
	}

	private void deleteUser(UserForm user){
		this.userManager.deleteUser(user.getUsername());
	}

	private void updateUser(UserForm user){
            
		String roles = "";
		SystemUser su = null;

		su = (SystemUser)this.userManager.loadUserByUsername(user.getUsername());

		Md5PasswordEncoder enc = new Md5PasswordEncoder();
		String passEnc = enc.encodePassword(user.getPassword1(), null);

		su.setUserId(user.getUserId());
		su.setUsername(user.getUsername());
		su.setFullname(user.getFullname());
		su.setPassword(passEnc);
		su.setEnabled(user.isEnabled());

		if(user.isAdmin())
			roles = "ROLE_ADMIN";

		if(user.isAdmin() && user.isUser())
			roles += SystemUser.ROLE_DELIMITER+"ROLE_USER";
		else
			roles = "ROLE_USER";

		su.setRoles(roles);

		this.userManager.updateUser(su);
	}

	private void newUser(UserForm user){

		String roles = "";

		SystemUser su = new SystemUser();

		Md5PasswordEncoder enc = new Md5PasswordEncoder();
		String passEnc = enc.encodePassword(user.getPassword1(), null);

		su.setUsername(user.getUsername());
		su.setFullname(user.getFullname());
		su.setPassword(passEnc);
		su.setEnabled(user.isEnabled());

		if(user.isAdmin())
			roles = "ROLE_ADMIN";

		if(user.isAdmin() && user.isUser())
			roles += SystemUser.ROLE_DELIMITER+"ROLE_USER";
		else
			roles = "ROLE_USER";

		su.setRoles(roles);


		this.userManager.createUser(su);


	}


	/* getters & setters */
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
