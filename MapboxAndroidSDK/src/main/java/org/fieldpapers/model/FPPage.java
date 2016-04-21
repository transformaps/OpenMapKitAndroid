package org.fieldpapers.model;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FPPage {

    private JSONObject feature;
    private Geometry geom;
    private Envelope env;

    private String pageNumber;
    private String url;

    private GeometryFactory geometryFactory = new GeometryFactory();

    public FPPage(JSONObject feature) {
        this.feature = feature;
        buildGeometry();
        buildEnvelope();
    }

    private void buildGeometry() {
        try {
            JSONArray coords = feature.getJSONObject("geometry").getJSONArray("coordinates").getJSONArray(0);
            int len = coords.length();
            Coordinate[] coordinates = new Coordinate[len];
            for (int i = 0; i < len; ++i) {
                JSONArray coord = coords.getJSONArray(i);
                double lng = coord.getDouble(0);
                double lat = coord.getDouble(1);
                coordinates[i] = new Coordinate(lng, lat);
            }
            geom = geometryFactory.createPolygon(coordinates);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void buildEnvelope() {
        env = geom.getEnvelopeInternal();
    }

    public String pageNumber() {

        return null;
    }

    public Geometry geometry() {
        return geom;
    }

    public Envelope envelope() {
        return env;
    }
}