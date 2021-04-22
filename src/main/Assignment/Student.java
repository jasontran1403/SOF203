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
public class Student {
    private String studentID;
    private String fullname;
    private String email;
    private String phonenum;
    private String sex;
    private String add;
    private String imagepath;

    public Student() {
    }

    public Student(String studentID, String fullname, String email, String phonenum, String sex, String add, String imagepath) {
        this.studentID = studentID;
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
        this.sex = sex;
        this.add = add;
        this.imagepath = imagepath;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
    
    
}
