/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import java.io.IOException;
import ict.bean.VenueBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author osacr
 */
public class VenueDB {

    private String dburl = "";
    private String dbusername = "";
    private String dbpassword = "";

    public VenueDB(String dburl, String dbusername, String dbpassword) {
        this.dburl = dburl;
        this.dbusername = dbusername;
        this.dbpassword = dbpassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(dburl, dbusername, dbpassword);
    }

    public boolean isNewVenue(String user) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isNew = true;

        try {
            cnnct = getConnection();
            String sql = "SELECT * FROM venue WHERE name = ?";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, user);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                isNew = false;
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isNew;
    }

    public boolean addVenue(String name, String type, String capacity, String location, String description, String person_ic, String booking_fee, String status) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isAddSuccess = false;

        try {
            cnnct = getConnection();
            String sql = "INSERT INTO venue(name, type, capacity, location, description, person_ic, booking_fee, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, name);
            pStmnt.setString(2, type);
            pStmnt.setString(3, capacity);
            pStmnt.setString(4, location);
            pStmnt.setString(5, description);
            pStmnt.setString(6, person_ic);
            pStmnt.setString(7, booking_fee);
            pStmnt.setString(8, status);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isAddSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex = ex.getNextException();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isAddSuccess;
    }

    public ArrayList<VenueBean> queryCust() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<VenueBean> venue = new ArrayList<VenueBean>();

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                VenueBean c = new VenueBean();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setType(rs.getString("type"));
                c.setCapacity(rs.getInt("capacity"));
                c.setLocation(rs.getString("location"));
                c.setDescription(rs.getString("description"));
                c.setPerson_ic(rs.getInt("person_ic"));
                c.setBooking_fee(rs.getInt("booking_fee"));
                c.setStatus(rs.getString("status"));
                venue.add(c);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return venue;
    }

    public VenueBean queryCustByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        VenueBean cb = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                cb = new VenueBean();
                cb.setId(rs.getInt("id"));
                cb.setName(rs.getString("name"));
                cb.setType(rs.getString("type"));
                cb.setCapacity(rs.getInt("capacity"));
                cb.setLocation(rs.getString("location"));
                cb.setDescription(rs.getString("description"));
                cb.setPerson_ic(rs.getInt("person_ic"));
                cb.setBooking_fee(rs.getInt("booking_fee"));
                cb.setStatus(rs.getString("status"));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cb;
    }

    public boolean delRecord(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM venue WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean editRecord(VenueBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Boolean isSuccess = false;

        try {
            cnnct = getConnection();

            int id = cb.getId();
            String name = cb.getName();
            String type = cb.getType();
            int capacity = cb.getCapacity();
            String location = cb.getLocation();
            String description = cb.getDescription();
            int person_ic = cb.getPerson_ic();
            int booking_fee = cb.getBooking_fee();
            String status = cb.getStatus();

            String preQueryStatement = "UPDATE venue SET NAME=?, TYPE=?, CAPACITY=?, LOCATION=?"
                    + ", DESCRIPTION=?, PERSON_IC=?, BOOKING_FEE=?, STATUS=? WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, type);
            pStmnt.setInt(3, capacity);
            pStmnt.setString(4, location);
            pStmnt.setString(5, description);
            pStmnt.setInt(6, person_ic);
            pStmnt.setInt(7, booking_fee);
            pStmnt.setString(8, status);
            pStmnt.setInt(9, id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

}
