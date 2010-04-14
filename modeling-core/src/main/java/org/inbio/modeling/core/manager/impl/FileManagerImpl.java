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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.modeling.core.dto.IntervalDTO;
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

	@Override
	public void writeReclasFile(LayerDTO layer, Long suffix){

		FileOutputStream fos = null;
		DataOutputStream dos = null;
		String line = null;
		File file = null;
		int index = 0;

		IntervalDTO inte = null;
		layer = new LayerDTO();
		List<IntervalDTO>  list = new ArrayList<IntervalDTO>();

		for(int i = 0; i < 5 ;i++){
			inte = new IntervalDTO();

			inte.setMin(new Double(i*500.698));
			inte.setMax(new Double(i*700.698));
			inte.setDescription(" Category "+i);
			list.add(inte);
		}

		layer.setIntervals(list);


		try {

			file = new File(tempHome+fileName+suffix);
			fos  = new FileOutputStream(file);
			dos  = new DataOutputStream(fos);

			for(IntervalDTO interval : layer.getIntervals()){

				if(interval.getMin() == interval.getMax()){
					line = interval.getMin() +
							" = " + index++ + " " + interval.getDescription() +"\n";
				}else{
					line = interval.getMin() + " thru " + interval.getMax() +
							" = " + index++ + " " + interval.getDescription() + "\n";
				}

				dos.writeUTF(line);
			}

			dos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
