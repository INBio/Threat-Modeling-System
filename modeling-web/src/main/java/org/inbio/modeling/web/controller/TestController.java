/* Modeling - Application to model threats.
 *
 * Copyright (C) 2010  INBio ( Instituto Nacional de Biodiversidad )
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

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.util.List;
import org.inbio.modeling.core.manager.TestManager;

public class TestController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    TestManager testManager;

    @Override
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Starting " + this.getClass());

		List <String> testList = testManager.getTestList();

		for (String value: testList){
			System.out.println("Name: "+value);
		}

        return new ModelAndView("test", "collections", testList);
    }

    public TestManager getTestManager() {
        return testManager;
    }

    public void setTestManager(TestManager testManager) {
        this.testManager = testManager;
    }
}
