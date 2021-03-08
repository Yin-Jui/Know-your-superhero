package com.example.knowyoursuperhero;

public class latlng {
    private double latitude;
    private double longitude;

    public latlng(){
        this(0.0, 0.0);
    }
    public latlng(double la, double lo){
        this.latitude = la;
        this.longitude = lo;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

