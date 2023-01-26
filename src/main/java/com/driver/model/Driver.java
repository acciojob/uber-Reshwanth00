package com.driver.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;
    private String mobile;
    private String password;

    public String getPassword() {
        return password;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Driver(){}
    public Driver(int driverId,String mobile,String password){
        setPassword(password);
        setMobile(mobile);
        setDriverId(driverId);
    }
    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    private Cab cab;

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public Cab getCab() {
        return cab;
    }
    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private List<TripBooking> tripBookingList = new ArrayList<>();

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }
}