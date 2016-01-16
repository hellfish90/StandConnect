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

    @Override
    public String toString() {
        return "Stand{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
