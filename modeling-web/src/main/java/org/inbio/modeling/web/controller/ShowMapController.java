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
import java.util.Calendar;
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

		int result = 0;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		List<String> commands = null;
		SystemCommandExecutorImpl commandExecutor = null;

//		Long suffix = Calendar.getInstance().getTimeInMillis();
		Long suffix = new Long(1270573092378L);
		System.out.println(" #-> Suffix: " + suffix);

/* Step 1 */
		/*
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/configureGISRC.sh Default " + suffix);

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
		 *
		 */

/* Step 2 */
		/*
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/importSHP.sh /home/asanabria/Layers/cobertura_dd.shp " + suffix);

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

		 */

/* Step 3 */
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/configureGISRC.sh LOC_"+suffix+" "+suffix);

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

/* Step 4 */
		/*
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/importSHP.sh /home/asanabria/Layers/redcamino_dd.shp " + suffix);

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
		 *
		 */

/* Step 5 */
		/*
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/toRaster.sh /home/asanabria/Layers/cobertura_dd.shp " + suffix);

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
		 *
		 */


/* Step 6 */
		/*
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/toRaster.sh /home/asanabria/Layers/redcamino_dd.shp " + suffix);

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
		 *
		 */

/* Step 6 */
		/*
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/doMapAlgebra.sh " + suffix + " " +
			"/home/asanabria/Layers/redcamino_dd.shp " + 10 + " " +
			"/home/asanabria/Layers/cobertura_dd.shp " + 90 + " ");

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
		 *
		 */

/* Step 7 */
		/*
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/exportPNG.sh " + suffix + " ");

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
		 *
		 */

/* Step 8 */
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add("/bin/sh");
		commands.add("-c");
		commands.add("/home/asanabria/Projects/active/iabin/modelado/src/modeling-scripts/getMinMaxValues.sh /home/asanabria/Layers/cobertura_dd.shp " + suffix + " ");

		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
		System.out.println(" --- Ultimo --- ");

		return new ModelAndView("showResultingMap");
	}

	private void printThis(int exitCode, StringBuilder stdout, StringBuilder stderr){

		System.out.println("Exit code: " + exitCode);
		System.out.println("stdout: ");
		System.out.println(stdout);
		System.out.println("stderr: ");
		System.out.println(stderr);
		System.out.println(" #-> ***************************************** <-#");
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		return new ModelAndView("index");
	}
}
