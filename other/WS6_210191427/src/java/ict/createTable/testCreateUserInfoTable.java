/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.createTable;

import ict.db.UserDB;

/**
 *
 * @author zengjuncai
 */
public class testCreateUserInfoTable {
        public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/itp4511_db";
        String username = "root";
        String password = "";
        UserDB userDb = new UserDB(url, username, password);
        userDb.createUserTable();
        userDb.addUserInfo("1", "abc", "123");
        userDb.addUserInfo("2", "xyz", "123");
        userDb.addUserInfo("3", "Andy", "Andy123");
    }
}
