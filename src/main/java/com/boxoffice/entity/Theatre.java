package com.boxoffice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Theatre {

    String theatreId;
    String name;
    String cityId;
    String address;
    String ownerId;

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "theatreId='" + theatreId + '\'' +
                ", name='" + name + '\'' +
                ", city='" + cityId + '\'' +
                ", address='" + address + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
