/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

/**
 *
 * @author jason
 */
public class Student {
    private String stuID;
    private String fullname;
    private String email;
    private String phonenum;
    private String sex;
    private String address;

    public Student() {
    }

    public Student(String stuID, String fullname, String email, String phonenum, String sex, String address) {
        this.stuID = stuID;
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
        this.sex = sex;
        this.address = address;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
