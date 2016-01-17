package com.standconnect.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 16/1/16.
 */
public class DataForScanner {
    private List<Stand> stands = new ArrayList<Stand>();
    private List<Tag> tags = new ArrayList<Tag>();
    private List<Beacon> beacons = new ArrayList<Beacon>();


    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public List<Stand> getStands() {
        return stands;
    }

    public void setStands(List<Stand> stands) {
        this.stands = stands;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "DataForScanner{" +
                "beacons=" + beacons +
                ", stands=" + stands +
                ", tags=" + tags +
                '}';
    }
}
