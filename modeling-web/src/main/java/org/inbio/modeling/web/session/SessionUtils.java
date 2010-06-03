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
package org.inbio.modeling.web.session;

import javax.servlet.http.HttpSession;


public class SessionUtils {

	/** retrieve and validate the data stored in the session
	 *
	 * @param session
	 * @return
	 */
	public static CurrentInstanceData isSessionAlive(HttpSession session){

		CurrentInstanceData current = null;

		// if the session expire or the server restarted while you still working
		// in the application
		if ( session == null)
			return null;

		current = (CurrentInstanceData)session.getAttribute("CurrentSessionInfo");

		// download the info
		if(current == null)
			return null;

		if(current.getUserSessionId() == null)
			return null;

		return current;
	}
}