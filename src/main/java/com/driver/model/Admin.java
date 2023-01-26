package com.driver.model;
import javax.persistence.*;

@Entity
@Table
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String username;
    private String password;
    public Admin(int adminId,String userName,String password){
        this.adminId=adminId;
        this.username=userName;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public Admin(){}

}