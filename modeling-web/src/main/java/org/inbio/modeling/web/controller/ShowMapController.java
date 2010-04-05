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

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inbio.system.command.impl.SystemCommandExecutorImpl;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author asanabria
 */
public class ShowMapController extends AbstractFormController {

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {


		List<String> commands = new ArrayList<String>();
//		commands.add("/bin/sh");
//		commands.add("-c");
//		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/process.sh /home/asanabria/Layers/cobertura_dd.shp 15 /home/asanabria/Layers/redcamino_dd.shp 85 500");
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/import.sh /home/asanabria/Layers/cobertura_dd.shp 15 /home/asanabria/Layers/redcamino_dd.shp 85");

		SystemCommandExecutorImpl commandExecutor = new SystemCommandExecutorImpl(commands);
		int result = commandExecutor.executeCommand();
		StringBuilder stdout = commandExecutor.getStandardOutput();
		StringBuilder stderr = commandExecutor.getStandardError();

		System.out.println("The numeric result of the command was: " + result);
		System.out.println("STDOUT");
		System.out.println(stdout);
		System.out.println("STDERR");
		System.out.println(stderr);

		return new ModelAndView("showResultingMap");
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		return new ModelAndView("index");
	}
}
