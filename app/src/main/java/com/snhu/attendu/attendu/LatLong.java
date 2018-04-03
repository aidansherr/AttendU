package com.snhu.attendu.attendu;
import java.io.Serializable;

/**
 * Created by Travis Guthrie on 3/28/2018.
 */

public class LatLong implements Serializable
{
    double lat;
    double lng;

    LatLong()
    {

    }
    LatLong(double nlat, double nlng)
    {
        lat=nlat;
        lng=nlng;
    }

   double getLat()
    {
        return lat;
    }
    double getLng()
    {
        return lng;
    }
    void setLat(double n)
    {
        lat=n;
    }
    void setLng(double n)
    {
        lng=n;
    }

}
