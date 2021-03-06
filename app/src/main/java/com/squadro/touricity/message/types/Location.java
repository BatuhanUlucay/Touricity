package com.squadro.touricity.message.types;

import com.google.android.gms.maps.model.LatLng;
import com.squadro.touricity.message.types.interfaces.ILocation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location implements ILocation {

    private String location_id;
    private double latitude;
    private double longitude;

    public Location(String location_id, double latitude, double longitude) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {
        location_id = null;
        latitude = 0.0;
        longitude = 0.0;
    }

    public Location(double lat, double lon){
        location_id = null;
        this.latitude = lat;
        this.longitude = lon;
    }

    public LatLng getLatLng(){
        return new LatLng(this.latitude, this.longitude);
    }
}
