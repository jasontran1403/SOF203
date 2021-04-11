/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teo
 */
public class DocGhiObjectCharacter {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Teo", 5, "CNTT"));
        list.add(new Student("Bi", 7, "TKWeb"));
        list.add(new Student("Gai", 8, "TkDoHoa"));
        list.add(new Student("Ti", 6, "Android"));
        //Ghi object luong character
        try{
            FileWriter fr = new FileWriter("D:\\SVCharacter.dat");
            BufferedWriter bw = new BufferedWriter(fr);
            for(int i = 0;i< list.size();i++){
                String temp = list.get(i).toString();
                bw.write(temp+"\n");

            }
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        //Doc object luong character, dua vao listSV
        List<Student> listSV = new ArrayList<>();
        try{
            FileReader frr = new FileReader("D:\\SVCharacter.dat");
            BufferedReader br = new BufferedReader(frr);
            String text;           
            while ((text = br.readLine()) != null) {
                //System.out.println(text); 
                String []detailSV = text.split("-");
                String name = detailSV[0];
                double diem = Double.parseDouble(detailSV[1]);
                String major = detailSV[2];
                Student sv = new Student(name, diem, major);
                listSV.add(sv);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        //Xuat ds sinh vien listSV da input tu file
        for(Student sv : listSV){
            System.out.println(sv);
        }
        System.out.println("");
    }
}
