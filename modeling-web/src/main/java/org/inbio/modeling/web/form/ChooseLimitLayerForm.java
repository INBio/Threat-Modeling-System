/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.modeling.web.form;

import java.util.List;
import org.inbio.modeling.web.form.util.Layer;

/**
 *
 * @author asanabria
 */
public class ChooseLimitLayerForm {

    private List<Layer> layers;
    private String selectedLayerName;

    public String getSelectedLayerName() {
        return selectedLayerName;
    }

    public void setSelectedLayerName(String selectedLayerName) {
        this.selectedLayerName = selectedLayerName;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}
