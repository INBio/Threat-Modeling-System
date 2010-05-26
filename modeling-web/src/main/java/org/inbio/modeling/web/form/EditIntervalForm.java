/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.modeling.web.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.inbio.modeling.web.form.util.Layer;

/**
 *
 * @author asanabria
 */
public class EditIntervalForm {

	List<Layer> layers =
		LazyList.decorate( new ArrayList(),
						   FactoryUtils.instantiateFactory(Layer.class));

	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}
}
