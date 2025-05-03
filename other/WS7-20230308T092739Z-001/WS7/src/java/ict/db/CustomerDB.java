/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.CustomerBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public CustomerDB(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
        return DriverManager.getConnection(url, username, password);
    }
    
    public void createCustTable(){
        Statement stmnt = null;
        Connection cnnct = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS customer (" + "custId varchar(5) NOT NULL," + 
                    "name varchar(25) NOT NULL," + "tel varchar(10) NOT NULL," + 
                    "age int(11) NOT NULL," + "PRIMARY KEY (custId)" + ")";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean addRecord(String CustId, String Name, String Tel, int Age){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO CUSTOMER VALUES (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, CustId);
            pStmnt.setString(2, Name);
            pStmnt.setString(3, Tel);
            pStmnt.setInt(4, Age);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public CustomerBean queryCustByID(String id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        CustomerBean cb = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE CUSTID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()){
                cb = new CustomerBean();
                cb.setCustId(rs.getString("custId"));
                cb.setName(rs.getString("name"));
                cb.setTel(rs.getString("tel"));
                cb.setAge(rs.getInt("age"));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return cb;
    }
    
    public ArrayList<CustomerBean> queryCust(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()){
                CustomerBean c = new CustomerBean();
                c.setCustId(rs.getString("custId"));
                c.setName(rs.getString("name"));
                c.setTel(rs.getString("tel"));
                c.setAge(rs.getInt("age"));
                customers.add(c);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return customers;
    }
    
    public ArrayList<CustomerBean> queryCustByName(String name){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE NAME LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%" + name + "%");
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()){
                CustomerBean c = new CustomerBean();
                c.setCustId(rs.getString("custId"));
                c.setName(rs.getString("name"));
                c.setTel(rs.getString("tel"));
                c.setAge(rs.getInt("age"));
                customers.add(c);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return customers;
    }
    
    public ArrayList<CustomerBean> queryCustByTel(String tel){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<CustomerBean> customers = new ArrayList<CustomerBean>();
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE TEL=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, tel);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()){
                CustomerBean c = new CustomerBean();
                c.setCustId(rs.getString("custId"));
                c.setName(rs.getString("name"));
                c.setTel(rs.getString("tel"));
                c.setAge(rs.getInt("age"));
                customers.add(c);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return customers;
    }
    
    public boolean delRecord(String custId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Boolean isSuccess = false;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM CUSTOMER WHERE CUSTID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, custId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean editRecord(CustomerBean cb){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Boolean isSuccess = false;
        
        try{
            cnnct = getConnection();
            
            String id = cb.getCustId();
            String name = cb.getName();
            String tel = cb.getTel();
            int age = cb.getAge();
            
            String preQueryStatement = "UPDATE CUSTOMER SET NAME=?, TEL=?, AGE=? WHERE CUSTID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, tel);
            pStmnt.setInt(3, age);
            pStmnt.setString(4, id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public void dropCustTable(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Boolean isSuccess = false;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "DROP TABLE CUSTOMER";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.execute();
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
