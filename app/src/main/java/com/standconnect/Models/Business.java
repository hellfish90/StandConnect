package com.standconnect.Models;

/**
 * Created by Marc on 24/11/15.
 */
public class Business implements Entity {

    String name;
    String description;
    int standNumber;
    String image;
    String contact;
    String phone;
    String address;

    public Business(String name, String description, int standNumber, String image, String contact, String phone, String address) {
        this.name = name;
        this.description = description;
        this.standNumber = standNumber;
        this.image = image;
        this.contact = contact;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(int standNumber) {
        this.standNumber = standNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Business bussines = (Business) o;

        if (name != null ? !name.equals(bussines.name) : bussines.name != null) return false;
        return !(phone != null ? !phone.equals(bussines.phone) : bussines.phone != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Business{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", standNumber=" + standNumber +
                ", image='" + image + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
