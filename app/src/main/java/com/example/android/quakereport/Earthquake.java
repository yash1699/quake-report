package com.example.android.quakereport;

/**
 * An {@link Earthquake} object contains information related to a single earthquake*/

public class Earthquake {

//    Magnitude of the earthquake
    private double mMagnitude;

//    Location of the earthquake
    private String mLocation;

//    Date of the earthquake
    private Long mTimeInMilliSeconds;

//    Website url of the earthquake
    private String mUrl;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude(size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMilliSeconds is the date the earthquake happened
     * @param url is the website url to find more details about the earthquake
     */
    public Earthquake(double magnitude, String location, Long timeInMilliSeconds, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliSeconds = timeInMilliSeconds;
        mUrl = url;
    }

    /**
     * Returns the magnitude of the earthquake
     */
    public double getMagnitude(){
        return mMagnitude;
    }

    /**
     * Returns the location of the earthquake
     */
    public String getLocation(){
        return mLocation;
    }

    /**
     * Returns the date of the earthquake
     */
    public Long getTimeInMilliSeconds(){
        return mTimeInMilliSeconds;
    }

    /**
     * Returns the website url to find more information about the earthquake
     */
    public String getUrl(){
        return mUrl;
    }

}