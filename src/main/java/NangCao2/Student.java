/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NangCao2;

/**
 *
 * @author Admin
 */
public class Student {
    private String fullname;
    private String fpid;
    private double score;

    public Student() {
    }

    public Student(String fullname, String fpid, double score) {
        this.fullname = fullname;
        this.fpid = fpid;
        this.score = score;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFpid() {
        return fpid;
    }

    public void setFpid(String fpid) {
        this.fpid = fpid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
}
