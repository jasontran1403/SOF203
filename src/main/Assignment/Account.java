/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

/**
 *
 * @author Admin
 */
public class Account {
    private String username;
    private String password;    
    private String fullname;
    private String address;
    private String phonenum;
    private String email;
    private String role;
    private String imagepath;

    public Account() {
    }

    public Account(String username, String password, String fullname, String address, String phonenum, String email, String role, String imagepath) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.phonenum = phonenum;
        this.email = email;
        this.role = role;
        this.imagepath = imagepath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

   
}
