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
package org.inbio.modeling.core.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import org.inbio.modeling.core.manager.ExportManager;

/**
 *
 * @author asanabria
 */
public class ExportManagerImpl implements ExportManager {

    private String mapsHome;
    private String tempDir;

    @Override
    public FileInputStream exportImage(String layerName, long suffix) throws Exception{

        File f = new File(mapsHome+layerName+"_T_"+suffix+".png");

        FileInputStream fis = null;

        if(f.exists()){
            fis = new FileInputStream(f);
        }

        return fis;
    }

    @Override
    public FileInputStream exportShapefile(String layerName, long suffix) throws Exception {

        File f = new File(tempDir+layerName+"_T_"+suffix+".zip");
        FileInputStream fis = null;

        if(f.exists()){
            fis = new FileInputStream(f);
        }

        return fis;
    }

    public String getMapsHome() {
        return mapsHome;
    }

    public void setMapsHome(String mapsHome) {
        this.mapsHome = mapsHome;
    }

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }


}