/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Slide2;

/**
 *
 * @author Nga
 */
public class person {
private String name;
private int age;
private boolean gioitinh;
private String ttgt;
    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public person(String name, int age, boolean gt, String tt) {
        this.name = name;
        this.age = age;
        this.gioitinh = gt;
        this.ttgt = tt;
    }

    public String getTtgt() {
        return ttgt;
    }

    public void setTtgt(String ttgt) {
        this.ttgt = ttgt;
    }

    public person() {
    }


}
