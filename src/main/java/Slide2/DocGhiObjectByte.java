/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slide2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teo
 */
public class DocGhiObjectByte {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Teo", 5, "CNTT"));
        list.add(new Student("Bi", 7, "TKWeb"));
        list.add(new Student("Gai", 8, "TkDoHoa"));
        list.add(new Student("Ti", 6, "Android"));
        // ghi object luong byte
        try{           
            FileOutputStream fos = new FileOutputStream("D:\\SVByte.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);           
            oos.writeObject(list);
            oos.close();
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //doc object luong byte
        try{
            FileInputStream fis = new FileInputStream("D:\\SVByte.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);           
            List<Student> lt = (List<Student>) ois.readObject();
            System.out.println("Doc tu file: ");
            for(Student s : lt){
                System.out.println(s);
            }
            ois.close();
            fis.close();
        }catch(Exception e){
            System.out.println("Co loi: " +e);
        }
    }
}
