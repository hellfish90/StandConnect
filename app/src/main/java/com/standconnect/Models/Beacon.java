package com.standconnect.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Marc on 24/11/15.
 */
public class Beacon implements Parcelable, Entity {

    private final String uuid;
    private final String name;
    private final int major;
    private final int minor;
    private final int rssi;
    private final String mac;

    //Beacon 1: 20:CD:39:A1:52:28
    //Beacon 2: 78:A5:04:4A:2F:65
    //Beacon 3: 78:A5:04:4A:2B:4A
    //Beacon Google: C5:D9:2F:73:00:E1
    //Mando: D0:5F:B8:30:D9:93


    public Beacon(String uuid, String name, int major, int minor, int rssi, String mac) {
        this.uuid = uuid;
        this.name = name;
        this.major = major;
        this.minor = minor;
        this.rssi = rssi;
        this.mac = mac;
    }

    protected Beacon(Parcel in) {
        uuid = in.readString();
        name = in.readString();
        major = in.readInt();
        minor = in.readInt();
        rssi = in.readInt();
        mac = null;
    }

    public static final Creator<Beacon> CREATOR = new Creator<Beacon>() {
        @Override
        public Beacon createFromParcel(Parcel in) {
            return new Beacon(in);
        }

        @Override
        public Beacon[] newArray(int size) {
            return new Beacon[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getUUID() {
        return uuid;
    }


    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getRssi() {
        return rssi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beacon beacon = (Beacon) o;

        return mac.equals(beacon.mac);

    }

    @Override
    public int hashCode() {
        return mac.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(this.uuid);
        parcel.writeString(this.name);
        parcel.writeInt(this.major);
        parcel.writeInt(this.minor);
        parcel.writeInt(this.rssi);
    }
}