/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.BookingBean;
import ict.bean.VenueBean;
import ict.bean.GuestBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DB {

    private String dburl = "";
    private String id = "";
    private String dbusername = "";
    private String dbpassword = "";

    public DB(String dburl, String dbusername, String dbpassword) {
        this.dburl = dburl;
        this.dbusername = dbusername;
        this.dbpassword = dbpassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        }
        return DriverManager.getConnection(dburl, dbusername, dbpassword);
    }

    public ArrayList getAllVenue() {
        ArrayList<VenueBean> venues = new ArrayList<VenueBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Venue";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                VenueBean vb = new VenueBean();
                vb.setId(rs.getInt(1));
                vb.setName(rs.getString(2));
                vb.setType(rs.getString(3));
                vb.setCapacity(rs.getInt(4));
                vb.setLocation(rs.getString(5));
                vb.setDescription(rs.getString(6));
                vb.setPerson_ic(rs.getInt(7));
                vb.setBooking_fee(rs.getInt(8));
                vb.setStatus(rs.getString(9));
                venues.add(vb);
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
        return venues;
    }

    public ArrayList getGuestListByMemberId(String memberId) {
        ArrayList<GuestBean> guestLists = new ArrayList<GuestBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT DISTINCT listId FROM Guest WHERE memberId =?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, memberId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                GuestBean gb = new GuestBean();
                gb.setListId(rs.getInt(1));
                guestLists.add(gb);
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
        return guestLists;
    }

    public ArrayList getGuestByGuestListId(String listId) {
        ArrayList<GuestBean> guestLists = new ArrayList<GuestBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM guest WHERE listId =?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, listId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                GuestBean gb = new GuestBean();
                gb.setGuestId(rs.getInt(1));
                gb.setListId(rs.getInt(2));
                gb.setName(rs.getString(3));
                gb.setEmail(rs.getString(4));
                gb.setMemberId(rs.getInt(5));
                guestLists.add(gb);
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
        return guestLists;

    }

    public int getNewId(String table, String column) {
        int id = 0;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT " + column + " FROM " + table + " ORDER BY " + column + " DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1) + 1;
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
        return id;
    }

    public boolean editGuest(String guestId, String name, String email) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE guest SET name = ?, email = ? WHERE guestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, email);
            pStmnt.setString(3, guestId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
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

    public boolean addGuest(String guestId, String listId, String name, String email, String memberId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO guest(guestId, listId, name, email, memberId) VALUES (?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, guestId);
            pStmnt.setString(2, listId);
            pStmnt.setString(3, name);
            pStmnt.setString(4, email);
            pStmnt.setString(5, memberId);
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

    public boolean delGuest(String guestId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM guest WHERE guestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, guestId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
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

    public ArrayList getBookRequestByMemberId(String memberId) {
        ArrayList<BookingBean> bookings = new ArrayList<BookingBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking WHERE memberId =?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, memberId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            while (rs.next()) {
                BookingBean booking = new BookingBean();
                booking.setId(Integer.parseInt(rs.getString(1)));
                booking.setDate(rs.getString(2));
                booking.setVenueId(Integer.parseInt(rs.getString(3)));
                booking.setStartTime(rs.getString(5));
                booking.setEndTime(rs.getString(6));
                booking.setStatus(rs.getString(7));
                booking.setListId(Integer.parseInt(rs.getString(8)));
                booking.setRequestTime(rs.getString(9));
                bookings.add(booking);
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
        return bookings;

    }

    public boolean createBookRequest(String veune, String date, String memberId, String startTime, String endTime, String guestList) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Connection cnnct1 = null;
        PreparedStatement pStmnt1 = null;
        boolean isSuccess = false;
        try {
            cnnct1 = getConnection();
            String preQueryStatement1 = "Select * From booking WHERE venueId = ? AND date = ? AND startTime <= ? AND endTime > ? Limit 1";
            pStmnt1 = cnnct1.prepareStatement(preQueryStatement1);
            pStmnt1.setString(1, veune);
            pStmnt1.setString(2, date);
            pStmnt1.setString(3, endTime);
            pStmnt1.setString(4, endTime);
            ResultSet rs = null;
            rs = pStmnt1.executeQuery();
            if (rs.next() == false) {
                //insert
                cnnct = getConnection();
                String preQueryStatement = "INSERT INTO booking VALUES(?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, String.valueOf(getNewId("booking", "id")));
                pStmnt.setString(2, date);
                pStmnt.setString(3, veune);
                pStmnt.setString(4, memberId);
                pStmnt.setString(5, startTime);
                pStmnt.setString(6, endTime);
                pStmnt.setString(7, "pending");
                pStmnt.setString(8, guestList);

                int rowCount = pStmnt.executeUpdate();
                isSuccess = true;
            }

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

    public boolean editBookRequest(String bookingRequestId, String veune, String date, String startTime, String endTime, String guestList) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET venueId = ?, date = ?, startTime = ?, endTime = ?, listId = ? WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, veune);
            pStmnt.setString(2, date);
            pStmnt.setString(3, startTime);
            pStmnt.setString(4, endTime);
            pStmnt.setString(5, guestList);
            pStmnt.setString(6, bookingRequestId);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
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

    public boolean cancelBooking(String bookingRequestId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET status = 'reject' WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, bookingRequestId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
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
    /*public boolean createGuestList(String memberId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO guest VALUES(?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, getLatestId("guestList", "guestListId"));
            pStmnt.setString(2, memberId);
            int rowCount = pStmnt.executeUpdate();
            isSuccess = true;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }*/

 /*public ArrayList getAllActiveVenue(){
        ArrayList<VenueBean> venues = new ArrayList<VenueBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Venue WHERE venueStatus = '1'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while(rs.next()) {
            VenueBean vb = new VenueBean();
            vb.setVenueId(rs.getString(1));
            vb.setImageListId(rs.getString(2));
            vb.setVenueName(rs.getString(3));
            vb.setVenueType(rs.getString(4));
            vb.setVenueCapacity(rs.getString(5));
            vb.setVenueLocation(rs.getString(6));
            vb.setVenueDesc(rs.getString(7));
            vb.setVenuePIC(rs.getString(8));
            vb.setVenueHourlyFee(rs.getInt(9));
            vb.setVenueStatus(rs.getString(10));
            venues.add(vb);
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return venues;
        
        
    }
    
    
    
    public ArrayList getGuestByGuestListId(String guestListId){
        ArrayList<GuestBean> guestLists = new ArrayList<GuestBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Guest WHERE guestListId =?";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, guestListId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while(rs.next()) {
            GuestBean gl = new GuestBean();
            gl.setGuestId(rs.getString(1));
            gl.setGuestName(rs.getString(2));
            gl.setGuestEmail(rs.getString(3));
            gl.setGuestListId(rs.getString(4));
            guestLists.add(gl);
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return guestLists;
        
        
    }
    
    public ArrayList getBookRequestByMemberId(String memberId){
        ArrayList<BookingRequestBean> brbs = new ArrayList<BookingRequestBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM BookingRequest WHERE bookMemberId =?";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, memberId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
            BookingRequestBean brb = new BookingRequestBean();
            brb.setBookingRequestId(rs.getString(1));
            brb.setBookDate(rs.getString(2));
            brb.setBookVenueId(rs.getString(3));
            brb.setBookStartTime(rs.getString(5));
            brb.setBookEndTime(rs.getString(6));
            brb.setBookStatus(rs.getString(7));
            brb.setBookRequestTime(rs.getString(8));
            brb.setBookPaymentReceipt(rs.getString(9));
            brb.setBookGuestListId(rs.getString(10));
            brbs.add(brb);
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return brbs;
        
        
    }
    
    public ArrayList getAllDate(){
        ArrayList dates = new ArrayList<>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        LocalDate date = LocalDate.now();
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM datetable WHERE aDate >'" + date + "'";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, memberId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
  
            dates.add(rs.getString(1));
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return dates;
    }
    
    public ArrayList getAllTimeSlot(){
        ArrayList timeslots = new ArrayList<>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM TimeSlot";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, memberId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
            timeslots.add(rs.getString(1));
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return timeslots;
    }
    
    public boolean addBookRequest(String veune, String date, String memberId, String startTime, String endTime, String guestList){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Connection cnnct1 = null;
        PreparedStatement pStmnt1 = null;
        boolean isSuccess = false;
        try{
            //select record from table
            cnnct1 = getConnection();
            String preQueryStatement1 = "Select * From bookingRequest WHERE bookVenueId = ? AND bookDate = ? AND bookStartTime <= ? AND bookEndTime > ? Limit 1";
            pStmnt1 = cnnct1.prepareStatement(preQueryStatement1);
            pStmnt1.setString(1, veune);
            pStmnt1.setString(2, date);
            pStmnt1.setString(3, endTime);
            pStmnt1.setString(4, endTime);
            ResultSet rs = null;
            rs = pStmnt1.executeQuery();
            if(rs.next() == false){
                //insert
                cnnct = getConnection();
                String preQueryStatement = "INSERT INTO bookingRequest VALUES(?,?,?,?,?,?,0,CURRENT_TIMESTAMP,NULL,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, getLatestId("bookingRequest", "bookingRequestId"));
                pStmnt.setString(2, date);
                pStmnt.setString(3, veune);
                pStmnt.setString(4, memberId);
                pStmnt.setString(5, startTime);
                pStmnt.setString(6, endTime);
                pStmnt.setString(7, guestList);

                int rowCount = pStmnt.executeUpdate();
                    isSuccess = true;          
            }          
            
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean editBookRequest(String bookingRequestId, String veune, String date, String startTime, String endTime, String guestList){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE bookingRequest SET bookVenueId = ?, bookDate = ?, bookStartTime = ?, bookEndTime = ?, bookGuestListId = ? WHERE bookingRequestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, veune);
            pStmnt.setString(2, date);
            pStmnt.setString(3, startTime);
            pStmnt.setString(4, endTime);
            pStmnt.setString(5, guestList);
            pStmnt.setString(6, bookingRequestId);
            
            int rowCount = pStmnt.executeUpdate();
            if (rowCount>=1){    
            isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public String getLatestId(String table, String column){
        String zeros = "00000000000000000000";
        String id  = "f";
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT "+column+" FROM "+ table +" ORDER BY "+ column +" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        if (rs.next()) {
            id = rs.getString(1);
            //int idLength = id.length() - 7;
            String idPre;
            int idNum;
            int countForZero = 0;
            int countForOne = 0;
            idPre = id.substring(0,2);
            if(Integer.parseInt(id.substring(2)) == 9){
            for (int i = 0; i < id.length()-2; i++) {
              if (id.charAt(i+2) =='0') {
                countForZero++;
              }else{
                  break;
              }
            }
             countForZero--; 
            }else{
                for (int i = 0; i < id.length()-2; i++) {
              if ( id.charAt(i+2) =='0') {
                countForZero++;
              }else{
                  break;
              }
            } 
                
            }
            idNum = Integer.parseInt(id.substring(2))+1;
            
            //idPre = id.split("(?<=\\G.{"+ idLength +"})")[0];
            //idNum = Integer.parseInt(id.split("(?<=\\G.{"+ idLength +"})")[1]) + 1;
            id = idPre+zeros.substring(0, countForZero)+idNum;
        }
            pStmnt.close();
            cnnct.close();

        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return id;
    }
    public String getCurrentId(String table, String column){
        String id  = "f";
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT "+column+" FROM "+ table +" ORDER BY "+ column +" DESC";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        if (rs.next()) {
            id = rs.getString(1);
        }
            pStmnt.close();
            cnnct.close();

        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return id;
    }
    public boolean addGuestList(String memberId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO guestList VALUES(?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, getLatestId("guestList", "guestListId"));
            pStmnt.setString(2, memberId);
            int rowCount = pStmnt.executeUpdate();
                isSuccess = true;          
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean addGuest(String guestId, String guestName, String guestEmail, String guestListId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO guest VALUES(?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, guestId);
            pStmnt.setString(2, guestName);
            pStmnt.setString(3, guestEmail);
            pStmnt.setString(4, guestListId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean delGuest(String guestId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM guest WHERE guestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, guestId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean editGuest(String guestId, String guestName, String guestEmail){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE guest SET guestName = ?, guestEmail = ? WHERE guestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, guestName);
            pStmnt.setString(2, guestEmail);
            pStmnt.setString(3, guestId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean cancelBooking(String bookingRequestId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE bookingRequest SET bookStatus = '9'WHERE bookingRequestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, bookingRequestId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean addVenue(String imageListId, String venueName, String venueType, String venueCapacity, String venueLocation, String venueDesc, String venuePIC, int venueHourlyFee){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO Venue VALUES(?,?,?,?,?,?,?,?,?,'1')";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, getLatestId("Venue", "venueId"));
            pStmnt.setString(2, imageListId);
            pStmnt.setString(3, venueName);
            pStmnt.setString(4, venueType);
            pStmnt.setString(5, venueCapacity);
            pStmnt.setString(6, venueLocation);
            pStmnt.setString(7, venueDesc);
            pStmnt.setString(8, venuePIC);
            pStmnt.setInt(9, venueHourlyFee);
 
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public ArrayList getAllStaff(){
        ArrayList staffs = new ArrayList<>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT staffId FROM staff WHERE roleId = 'R01'";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, memberId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
            staffs.add(rs.getString(1));
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return staffs;
    }
    public ArrayList getAllImageList(){
        ArrayList<imageListBean> ImageLists = new ArrayList<>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM imageList";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, memberId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
            imageListBean ilb = new imageListBean();
            ilb.setImageListId(rs.getString(1));
            ilb.setImageListName(rs.getString(2));
            ImageLists.add(ilb);
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return ImageLists;
    }
    public boolean editTimeslot(String editTimeslot, String orignalTimeslot){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE timeslot SET atimeslot = ? WHERE atimeslot = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, editTimeslot);
            pStmnt.setString(2, orignalTimeslot);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean delTimeslot(String delTimeslot){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM timeslot WHERE atimeslot = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, delTimeslot);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean addTimeslot(String addTimeslot){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO timeslot VALUES (?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, addTimeslot);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public ArrayList getBookRequestByStaffId(String staffId){
        ArrayList<String> venueIds = getVenueByStaffId(staffId);
        ArrayList<BookingRequestBean> brbs = new ArrayList<BookingRequestBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        for(int i = 0; i < venueIds.size(); i++){
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM BookingRequest WHERE bookVenueId = ? AND (bookStatus = '0' OR bookStatus = '1' OR bookStatus = '8') ";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, venueIds.get(i));
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
            BookingRequestBean brb = new BookingRequestBean();
            brb.setBookingRequestId(rs.getString(1));
            brb.setBookDate(rs.getString(2));
            brb.setBookVenueId(rs.getString(3));
            brb.setBookMemberId(rs.getString(4));
            brb.setBookStartTime(rs.getString(5));
            brb.setBookEndTime(rs.getString(6));
            brb.setBookStatus(rs.getString(7));
            brb.setBookRequestTime(rs.getString(8));
            brb.setBookPaymentReceipt(rs.getString(9));
            brb.setBookGuestListId(rs.getString(10));
            brbs.add(brb);
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        }
        return brbs;
       
    }
    public ArrayList getVenueByStaffId(String staffId){
        ArrayList<String> venueIds = new ArrayList<String>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT venueId FROM venue WHERE venuePIC =?";
            
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, staffId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
            venueIds.add(rs.getString(1));
        }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return venueIds;
        
        
    }
    public boolean approveBookRequest(String bookingRequestId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE bookingRequest SET bookStatus = '1' WHERE bookingRequestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, bookingRequestId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean rejectBookRequest(String bookingRequestId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE bookingRequest SET bookStatus = '8' WHERE bookingRequestId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, bookingRequestId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean addCheckInOutRecord(String bookingRequestId, String memberId, String checkInOutType, String checkInOutTime, String checkInOutRemark){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO CheckInOutRecord VALUES (?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, getLatestId("CheckInOutRecord","checkInOutRecordId"));
            pStmnt.setString(2, bookingRequestId);
            pStmnt.setString(3, memberId);
            pStmnt.setString(4, checkInOutType);
            pStmnt.setString(5, checkInOutTime);
            pStmnt.setString(6, checkInOutRemark);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public ArrayList getCheckRecordById(String bookingRequestId){
        ArrayList<checkRecord> crs = new ArrayList<checkRecord>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM checkInOutRecord WHERE bookingRequestId = ?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, bookingRequestId);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

        while (rs.next()) {
            checkRecord cr = new checkRecord();
            cr.setCheckInOutRecord(rs.getString(1));
            cr.setBookingRequestId(rs.getString(2));
            cr.setMemberId(rs.getString(3));
            cr.setCheckInOutType(rs.getString(4));
            cr.setCheckInOutTime(rs.getString(5));
            cr.setCheckInOutRemark(rs.getString(6));
            crs.add(cr);
        }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return crs;
    }
    public boolean editCheckInOutRecord(String checkInOutRecordId, String checkInOutType,String checkInOutTime,String checkInOutRemark){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE checkInOutRecord SET checkInOutType = ?, checkInOutTime= ?, checkInOutRemark = ? WHERE checkInOutRecordId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, checkInOutType);
            pStmnt.setString(2, checkInOutTime);
            pStmnt.setString(3, checkInOutRemark);            
            pStmnt.setString(4, checkInOutRecordId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean delCheckInOutRecord(String checkInOutRecordId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM checkInOutRecord WHERE checkInOutRecordId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, checkInOutRecordId);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >=1){    
                isSuccess = true;
            }
        }catch(SQLException ex){
            while (ex != null){
            ex.printStackTrace();
            ex = ex.getNextException();
        }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }*/
}
