package com.standconnect.Models;

import java.util.Date;

/**
 * Created by Marc on 24/11/15.
 */
public class Event implements Entity{

    String name;
    Date beginDate;
    Date endDate;
    String schedule;
    String location;
    String image;

    public Event(String name, Date beginDate, Date endDate, String schedule, String location, String image) {
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.schedule = schedule;
        this.location = location;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (beginDate != null ? !beginDate.equals(event.beginDate) : event.beginDate != null)
            return false;
        return !(endDate != null ? !endDate.equals(event.endDate) : event.endDate != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", schedule='" + schedule + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
