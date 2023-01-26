package com.driver.model;
import javax.persistence.*;
@Entity
@Table
public class Cab{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int perKmRate;
    private boolean available;
    public Cab(){}
    public Cab(int perKmRate){
        setPerKmRate(perKmRate);
        setAvailable(true);
    }

    public int getId() {
        return id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }
    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public void setId(int id) {
        this.id = id;
    }
    @OneToOne
    @JoinColumn
    private Driver driver;
}
