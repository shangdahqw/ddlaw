package com.dangde.domain.model;

import java.util.List;

import com.dangde.domain.Layer;

public class Layers {

	
	private List<Layer> layers;

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public Layers(List<Layer> layers) {
        super();
        this.layers = layers;
    }

    public Layers() {
        super();
    }

}
