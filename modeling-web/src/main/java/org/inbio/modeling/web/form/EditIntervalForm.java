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
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author asanabria
 */
public class EditIntervalForm implements Validator {

	private List<Layer> layers =
		LazyList.decorate( new ArrayList(),
						   FactoryUtils.instantiateFactory(Layer.class));


	public boolean supports(Class clazz) {
		return EditIntervalForm.class.equals(clazz);
	}


	public void validate(Object target, Errors errors) {

		EditIntervalForm  form = (EditIntervalForm) target;
		Object[] args = null;

		for(Layer aLayer : form.getLayers()){
			if(aLayer.getCategories().size() <= 0){
				args = new Object[1];
				args[0] = aLayer.getName();
				errors.reject("errors.noCategories", args, "No categories");
			}
		}
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}
}
