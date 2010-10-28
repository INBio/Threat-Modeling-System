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
package org.inbio.modeling.core.manager.impl;

import org.inbio.modeling.core.dao.ConfigDAO;
import org.inbio.modeling.core.manager.ConfigManager;

/**
 *
 * @author asanabria
 */
public class ConfigManagerImpl implements ConfigManager{

    private ConfigDAO configDAO;

    @Override
    public String retrieveResolution() {
        return this.configDAO.findParameter("Resolution");
    }

    @Override
    public void saveResolution(String res) {
        this.configDAO.updateParameter("Resolution", res);
    }

    public ConfigDAO getConfigDAO() {
        return configDAO;
    }

    public void setConfigDAO(ConfigDAO configDAO) {
        this.configDAO = configDAO;
    }
}