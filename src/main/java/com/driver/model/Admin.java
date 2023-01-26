package com.driver.model;

import javax.persistence.*;

@Entity
@Table
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Admin(){

    }
    public Admin(int id,String userName,String password) {
        setPassword(password);
        setUserName(userName);
        setId(id);
    }
}
