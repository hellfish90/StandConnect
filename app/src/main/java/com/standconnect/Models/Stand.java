package com.standconnect.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 24/11/15.
 */
public class Stand implements Entity{

    private Integer id;
    private String name;
    private Integer number;
    private List<Business> businesses = new ArrayList<Business>();
    private List<Beacon> beacons = new ArrayList<Beacon>();
    private Event event;

    public Stand(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stand stand = (Stand) o;

        if (number != stand.number) return false;
        return !(name != null ? !name.equals(stand.name) : stand.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Stand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", businesses=" + businesses +
                ", beacons=" + beacons +
                ", event=" + event +
                '}';
    }
}
