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

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

		Long suffix = Calendar.getInstance().getTimeInMillis();
		//suffix = new Long(1270739103518L);

		System.out.println(" #-> Suffix: " + suffix);

		String m1 = "cobertura_dd";
		String m2 = "redcamino_dd";

		//this.configureEnvironment("Default", suffix);
		//this.setResolution(0.01, suffix);
		//this.importLayer(layerHome ,m1, suffix);
		//this.configureEnvironment("LOC_"+suffix, suffix);
		//this.setResolution(0.01, suffix);
		//this.convertLayer2Raster(m1, suffix);
		//this.importLayer(layerHome, m2, suffix);
		//this.doSimpleReclasification(m1, "COB_AGROP", suffix);
		//this.convertLayer2Raster(m1, suffix);
		//this.convertLayer2Raster(m2, suffix);
		//this.advanceReclasification(m1, suffix);
		//this.getLayerCategories(m1, "RAST", suffix);
		System.out.println("Jaleas de guayaba");

		return new ModelAndView("showResultingMap");
	}


	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		return new ModelAndView("index");
	}
}
