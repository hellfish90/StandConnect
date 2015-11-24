package com.standconnect.Models;

/**
 * Created by Marc on 24/11/15.
 */
public class Beacon implements Entity{

    String name;
    String mac;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beacon beacon = (Beacon) o;

        return !(mac != null ? !mac.equals(beacon.mac) : beacon.mac != null);

    }

    @Override
    public int hashCode() {
        return mac != null ? mac.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Beacon{" +
                "name='" + name + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
