package com.standconnect.Models;

import java.util.List;

/**
 * Created by Marc on 16/1/16.
 */
public class ScannerData {

    Beacon beacon;
    Stand stand;
    List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    @Override
    public String toString() {
        return "ScannerData{" +
                "beacon=" + beacon +
                ", stand=" + stand +
                ", tags=" + tags +
                '}';
    }
}
