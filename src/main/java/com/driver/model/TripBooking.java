package com.driver.model;

import javax.persistence.*;

@Entity
@Table
public class TripBooking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripBookingId;
    private String fromLocation;
    private String toLocation;
    private int distanceInKm;
    @Enumerated(value = EnumType.STRING)
    private TripStatus status;
    private int bill;

    public int getTripBookingId() {
        return tripBookingId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public TripStatus getStatus() {
        return status;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public String getToLocation() {
        return toLocation;
    }
    public TripBooking(){}
    public TripBooking(int tripBookingId,int distanceInKm,int bill,String toLocation,String fromLocation,TripStatus status){
        setStatus(status);
        setBill(bill);
        setTripBookingId(tripBookingId);
        setDistanceInKm(distanceInKm);
        setToLocation(toLocation);
        setFromLocation(fromLocation);
    }
    @ManyToOne
    @JoinColumn
    private Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }
    @ManyToOne
    @JoinColumn
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}