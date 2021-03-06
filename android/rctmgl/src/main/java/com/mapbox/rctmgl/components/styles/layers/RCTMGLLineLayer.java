package com.mapbox.rctmgl.components.styles.layers;

import android.content.Context;

import com.facebook.react.uimanager.UIManagerModule;
import com.mapbox.mapboxsdk.style.layers.Filter;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.rctmgl.components.mapview.RCTMGLMapView;
import com.mapbox.rctmgl.components.styles.RCTMGLStyle;
import com.mapbox.rctmgl.components.styles.RCTMGLStyleFactory;

/**
 * Created by nickitaliano on 9/18/17.
 */

public class RCTMGLLineLayer extends RCTLayer<LineLayer> {
    private String mSourceLayerID;

    public RCTMGLLineLayer(Context context) {
        super(context);
    }

    @Override
    protected void updateFilter(Filter.Statement statement) {
        mLayer.setFilter(statement);
    }

    @Override
    public void addToMap(RCTMGLMapView mapView) {
        super.addToMap(mapView);

        Filter.Statement statement = buildFilter();
        if (statement != null) {
            updateFilter(statement);
        }
    }

    @Override
    public LineLayer makeLayer() {
        LineLayer layer = new LineLayer(mID, mSourceID);

        if (mSourceLayerID != null) {
            layer.setSourceLayer(mSourceLayerID);
        }

        return layer;
    }

    @Override
    public void addStyles() {
        RCTMGLStyleFactory.setLineLayerStyle(mLayer, new RCTMGLStyle(getContext(), mReactStyle, mMap));
    }

    public void setSourceLayerID(String sourceLayerID) {
        mSourceLayerID = sourceLayerID;

        if (mLayer != null) {
            mLayer.setSourceLayer(mSourceLayerID);
        }
    }
}
