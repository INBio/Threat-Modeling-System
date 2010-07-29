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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.inbio.modeling.core.manager.FileManager;
import org.inbio.modeling.core.manager.GrassManager;
import org.inbio.modeling.web.form.ExportData;
import org.inbio.modeling.web.form.ListLayerForm;
import org.inbio.modeling.web.session.CurrentInstanceData;
import org.inbio.modeling.web.form.util.Layer;
import org.inbio.modeling.web.session.SessionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class ExportController extends AbstractFormController {

    private GrassManager grassManagerImpl;
    private FileManager fileManagerImpl;
    private ShowMapController showMapControllerImpl;

    @Override
    protected ModelAndView showForm(HttpServletRequest request
            , HttpServletResponse response
            , BindException errors) {

        return null;
    }


    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request
            , HttpServletResponse response
            , Object command
            , BindException errors) {

        HttpSession session = null;
        String type = null;

        if(errors.hasErrors())
            return showForm(request, response, errors);

        CurrentInstanceData currentInstanceData = null;
        ExportData exportForm = (ExportData)command;

        // retrieve the type.
        type = exportForm.getType();

        // retrieve the session Information.
        session = request.getSession();
        currentInstanceData = SessionUtils.isSessionAlive(session);

        if(currentInstanceData != null){

            if(exportForm.getType().equals("PDF")){
                System.out.println("#-> export a PDF");
            }else if(exportForm.getType().equals("SHP")){
                System.out.println("#-> export a SHP");
            }else{ // PNG
                System.out.println("#-> export a PNG");
            }

        }else{
            Exception ex = new Exception("errors.noSession");
            Logger.getLogger(ColumnController.class.getName()).log(Level.SEVERE, null, ex);
            errors.reject(ex.getMessage());
            return showForm(request, response, errors);
        }


        return showMapControllerImpl.showForm(request, response, errors);
    }

    public FileManager getFileManagerImpl() {
        return fileManagerImpl;
    }

    public void setFileManagerImpl(FileManager fileManagerImpl) {
        this.fileManagerImpl = fileManagerImpl;
    }

    public GrassManager getGrassManagerImpl() {
        return grassManagerImpl;
    }

    public void setGrassManagerImpl(GrassManager grassManagerImpl) {
        this.grassManagerImpl = grassManagerImpl;
    }

    public ShowMapController getShowMapControllerImpl() {
        return showMapControllerImpl;
    }

    public void setShowMapControllerImpl(ShowMapController showMapControllerImpl) {
        this.showMapControllerImpl = showMapControllerImpl;
    }
}