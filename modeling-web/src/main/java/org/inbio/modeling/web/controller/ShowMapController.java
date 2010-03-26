/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.modeling.web.controller;

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
		System.out.println("Probando, probando... ");
		return new ModelAndView("showResultingMap");
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
