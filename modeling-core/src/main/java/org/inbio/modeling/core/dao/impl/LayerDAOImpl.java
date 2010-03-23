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

package org.inbio.modeling.core.dao.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import org.inbio.modeling.core.dao.LayerDAO;

public class LayerDAOImpl extends BaseDAOImpl implements LayerDAO {

	public LayerDAOImpl(){
		super();
	}

	@Override
	public List<String> getLayerList() {

		String dirName = System.getenv("HOME")+"/Layers/";

		System.out.println(dirName);

		List<String> names = new ArrayList<String>();

		try{
			File dir = new File(dirName);
			String childrenName = null;
			String[] children = dir.list(filter);

			if (children == null) {
				//Either dir does not exist or is not a directory
			} else {
				for (int i = 0; i < children.length; i++) {

					//deletes the extension.
					childrenName = children[i];
					childrenName = childrenName.replace(".shp", "");

					// Add the file name to ArrayList
					names.add(childrenName);
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return names;
	}

	static FilenameFilter filter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(".shp");
		}
	};
}
