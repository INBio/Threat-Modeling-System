/* AIT - Analysis of taxonomic indicators
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

package org.inbio.modeling.web.common.controller.utils;

import java.util.List;

/**
 *
 * @author esmata
 */
public class Node {

    public Node(){};

    private Long value1;
    private Long value2;
    private List<String> value3;

    /**
     * @return the value1
     */
    public Long getValue1() {
        return value1;
    }

    /**
     * @param value1 the value1 to set
     */
    public void setValue1(Long value1) {
        this.value1 = value1;
    }

    /**
     * @return the value2
     */
    public Long getValue2() {
        return value2;
    }

    /**
     * @param value2 the value2 to set
     */
    public void setValue2(Long value2) {
        this.value2 = value2;
    }

    /**
     * @return the value3
     */
    public List<String> getValue3() {
        return value3;
    }

    /**
     * @param value3 the value3 to set
     */
    public void setValue3(List<String> value3) {
        this.value3 = value3;
    }

}
