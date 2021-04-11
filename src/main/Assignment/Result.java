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
public class Result {
    private String stuid;
    private String fullname;
    private double java;
    private double javascript;
    private double htmlcss;
    private double average;

    public Result() {
    }

    public Result(String stuid, String fullname, double java, double javascript, double htmlcss, double average) {
        this.stuid = stuid;
        this.fullname = fullname;
        this.java = java;
        this.javascript = javascript;
        this.htmlcss = htmlcss;
        this.average = average;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public double getJava() {
        return java;
    }

    public void setJava(double java) {
        this.java = java;
    }

    public double getJavascript() {
        return javascript;
    }

    public void setJavascript(double javascript) {
        this.javascript = javascript;
    }

    public double getHtmlcss() {
        return htmlcss;
    }

    public void setHtmlcss(double htmlcss) {
        this.htmlcss = htmlcss;
    }

    public double getAverage() {
        return (this.java + this.javascript + this.htmlcss)/3;
    }

    public void setAverage(double average) {
        this.average = average;
    }
    
    
}
