package com.driver.model;
import javax.persistence.*;

@Entity
@Table
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public Admin(){}
    public Admin(int adminId,String userName,String password){
        setPassword(password);
        setAdminId(adminId);
        setUserName(userName);
    }
}