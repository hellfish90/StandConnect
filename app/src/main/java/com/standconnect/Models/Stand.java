package com.standconnect.Models;

/**
 * Created by Marc on 24/11/15.
 */
public class Stand implements Entity{

    String name;
    int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
        result = 31 * result + number;
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
