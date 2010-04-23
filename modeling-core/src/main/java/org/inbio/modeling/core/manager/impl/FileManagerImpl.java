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

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.manager.FileManager;

/**
 *
 * @author asanabria
 */
public class FileManagerImpl implements FileManager {

	protected final Log logger = LogFactory.getLog(getClass());

	private String tempHome;
	private String fileName;
	private String fileExtension;

	@Override
	public void writeReclasFile(LayerDTO layer, Long suffix){

		FileOutputStream fos = null;
		DataOutputStream dos = null;
		String[] value = null;
		String line = null;
		File file = null;
		int index = 0;

		try {

			file = new File(tempHome+fileName+suffix+fileExtension);
			fos  = new FileOutputStream(file);
			dos  = new DataOutputStream(fos);

			for(CategoryDTO category : layer.getCategories()){

				if(this.validateFormat(category)){

					if(category.isInterval()){
						value = category.getValue().split("-");
						category.setValue(value[0] + " thru " + value[1]);
					}

					line = category.getValue() +
						" = " + index + " " + category.getDescription() + "\n";
					index++;
					dos.writeUTF(line);
				}
			}

			dos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean validateFormat(CategoryDTO category){

		return true;
	}

	/* getters y setters */
	public String getTempHome() {
		return tempHome;
	}

	public void setTempHome(String tempHome) {
		this.tempHome = tempHome;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
}
