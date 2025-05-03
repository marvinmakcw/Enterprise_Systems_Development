package ict.bean;

import java.io.Serializable;

public class VenueBean implements Serializable {

    private int id;
    private String name;
    private String type;
    private int capacity;
    private String location;
    private String description;
    private int person_ic;
    private int booking_fee;
    private String status;

    public VenueBean() {
    }

    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPerson_ic() {
        return person_ic;
    }

    public void setPerson_ic(int person_ic) {
        this.person_ic = person_ic;
    }

    public int getBooking_fee() {
        return booking_fee;
    }

    public void setBooking_fee(int booking_fee) {
        this.booking_fee = booking_fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
