/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;
import java.io.Serializable;
/**
 *
 * @author osacr
 */
public class AdminList {

    private int id;
    private String username;
    private int phone_no;
    private String role;
    private String password;

    public AdminList(String username, int phone_no, String role, String password) {
        this.username = username;
        this.phone_no = phone_no;
        this.role = role;
        this.password = password;
    }

    public AdminList(int id, String username, int phone_no, String role, String password) {
        this.id = id;
        this.username = username;
        this.phone_no = phone_no;
        this.role = role;
        this.password = password;
    }

    public AdminList() {}


    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getPhone_no() {
        return phone_no;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }


}
