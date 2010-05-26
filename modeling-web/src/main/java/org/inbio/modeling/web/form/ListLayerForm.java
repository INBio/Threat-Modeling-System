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
public class ListLayerForm {

	private Double resolution;
	private String projection;
	private List<Layer> layerList =
		LazyList.decorate( new ArrayList(),
						   FactoryUtils.instantiateFactory(Layer.class));

	public List<Layer> getLayerList() {
		return layerList;
	}

	public void setLayerList(List<Layer> layerList) {
		this.layerList = layerList;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public Double getResolution() {
		return resolution;
	}

	public void setResolution(Double resolution) {
		this.resolution = resolution;
	}
}
