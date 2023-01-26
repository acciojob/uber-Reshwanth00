package com.driver.model;
import net.minidev.json.annotate.JsonIgnore;
import javax.persistence.*;
@Entity
@Table
public class TripBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripBookingId;
    private String fromLocation;
    private String toLocation;
    private int distanceInKm;
    @Enumerated(value = EnumType.STRING)
    private TripStatus status;
    private int bill;
    public TripBooking(){}

    public int getTripBookingId() {
        return tripBookingId;
    }

    public int getBill() {
        return bill;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public TripStatus getStatus() {
        return status;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }
    public TripBooking(int tripBookingId, String fromLocation, String toLocation, int distanceInKm, TripStatus status, int bill){
        setBill(bill);
        setStatus(status);
        setTripBookingId(tripBookingId);
        setToLocation(toLocation);
        setFromLocation(fromLocation);
        setDistanceInKm(distanceInKm);
    }
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Driver driver;

    public Customer getCustomer() {
        return customer;
    }

    public Driver getDrive() {
        return driver;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer;
}
