/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.Serializable;

/**
 * 
 * @author Tran Trung Nghia
 */
public class ThongTinDangNhap implements Serializable {
        private String id;
        private String password;
        private String idAndPassword;

        public ThongTinDangNhap() {
        }

        public ThongTinDangNhap(String id, String password, String idAndPassword) {
                this.id = id;
                this.password = password;
                this.idAndPassword = idAndPassword;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getIdAndPassword() {
                return idAndPassword;
        }

        public void setIdAndPassword(String idAndPassword) {
                this.idAndPassword = idAndPassword;
        }
        
        
}
