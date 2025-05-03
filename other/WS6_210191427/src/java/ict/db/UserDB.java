/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ict.bean.UserInfo;

/**
 *
 * @author zengjuncai
 */
public class UserDB {
    private String dburl = "";
    private String dbusername = "";
    private String dbpassword = "";

    public UserDB(String dburl, String dbusername, String dbpassword){
        this.dburl = dburl;
        this.dbusername = dbusername;
        this.dbpassword = dbpassword;
    }

    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return DriverManager.getConnection(dburl, dbusername, dbpassword);
    }

    public boolean isValidUser(String user, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;

        try{
                cnnct = getConnection();
                String sql = "SELECT * FROM UserInfo WHERE username = ? AND password = ?";
                pStmnt = cnnct.prepareStatement(sql);
                pStmnt.setString(1, user);
                pStmnt.setString(2, pwd);;
                ResultSet rs = null;
                rs = pStmnt.executeQuery();

                if(rs.next()){
                    isValid = true;
                }

                pStmnt.close();
                cnnct.close();
        }catch(SQLException ex){
                while(ex != null){
                    ex.printStackTrace();
                    ex = ex.getNextException();
                }
        }catch(IOException ex){
                ex.printStackTrace();
        }
        return isValid;
    }

    public void createUserTable(){
        Statement stmnt = null;
        Connection cnnct = null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql1 = "CREATE TABLE IF NOT EXISTS userInfo (" + "Id varchar(5) NOT NULL," + "username varchar(25) NOT NULL," + "password varchar(25) NOT NULL," + "PRIMARY KEY (Id)" + ")";
            stmnt.execute(sql1);
            stmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public boolean addUserInfo(String id, String user, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isAddSuccess = false;

        try{
            cnnct = getConnection();
            //String sql = "INSERT INTO APP.userInfo (Id,username,password) VALUES ('1','abc','123')";
            String sql = "INSERT INTO userInfo VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, id);
            pStmnt.setString(2, user);
            pStmnt.setString(3, pwd);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isAddSuccess = true;
                System.out.print("Add record successfully.<br />");
            }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            ex= ex.getNextException();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isAddSuccess;
    }

}
