package ict.db;

import java.io.IOException;
import ict.bean.AdminList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ict.bean.UserInfo;
import java.util.ArrayList;

public class UserDB {

    private String dburl = "";
    private String dbusername = "";
    private String dbpassword = "";

    public UserDB(String dburl, String dbusername, String dbpassword) {
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

    public boolean isValidUser(String user, String pwd) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid = false;

        try {
            cnnct = getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, user);
            pStmnt.setString(2, pwd);;
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                isValid = true;
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
        return isValid;
    }

    public String userRole(String user) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String role = "";

        try {
            cnnct = getConnection();
            String sql = "SELECT role FROM user WHERE username = ?";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, user);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
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
        return role;
    }

    public int getMemberId(String user) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int id = 0;

        try {
            cnnct = getConnection();
            String sql = "SELECT id FROM user WHERE username = ?";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, user);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
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

    public boolean isNewUser(String user) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isNew = true;

        try {
            cnnct = getConnection();
            String sql = "SELECT * FROM user WHERE username = ?";
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

    public void createUserTable() {
        Statement stmnt = null;
        Connection cnnct = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql1 = "CREATE TABLE IF NOT EXISTS user (" + "Id varchar(5) NOT NULL," + "username varchar(25) NOT NULL," + "password varchar(25) NOT NULL," + "PRIMARY KEY (Id)" + ")";
            stmnt.execute(sql1);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean addUserInfo(String username, String password, String phone) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isAddSuccess = false;

        try {
            cnnct = getConnection();
            String sql = "INSERT INTO user(username, password, role, phone_no) VALUES (?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            pStmnt.setString(3, "m");
            pStmnt.setString(4, phone);
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

    public boolean addAdminUser(String username, String password, String phone, String role) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isAddSuccess = false;

        try {
            cnnct = getConnection();
            String sql = "INSERT INTO user(username, password, role, phone_no) VALUES (?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            pStmnt.setString(3, role);
            pStmnt.setString(4, phone);
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

    public AdminList queryCustByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        AdminList cb = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                cb = new AdminList();
                cb.setId(rs.getInt("id"));
                cb.setUsername(rs.getString("username"));
                cb.setPhone_no(rs.getInt("phone_no"));
                cb.setRole(rs.getString("role"));
                cb.setPassword(rs.getString("password"));
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

    public ArrayList<AdminList> queryCust() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<AdminList> user = new ArrayList<AdminList>();

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                AdminList c = new AdminList();
                c.setId(rs.getInt("id"));
                c.setUsername(rs.getString("username"));
                c.setPhone_no(rs.getInt("phone_no"));
                c.setRole(rs.getString("role"));
                c.setPassword(rs.getString("password"));
                user.add(c);
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
        return user;
    }

    public ArrayList<AdminList> queryCustByName(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<AdminList> user = new ArrayList<AdminList>();

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE USERNAME LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%" + username + "%");
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                AdminList c = new AdminList();
                c.setId(rs.getInt("id"));
                c.setUsername(rs.getString("username"));
                c.setPhone_no(rs.getInt("phone_no"));
                c.setRole(rs.getString("role"));
                c.setPassword(rs.getString("password"));
                user.add(c);
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
        return user;
    }

    public boolean delRecord(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Boolean isSuccess = false;

        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM user WHERE ID=?";
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

    public boolean editRecord(AdminList cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Boolean isSuccess = false;

        try {
            cnnct = getConnection();

            int id = cb.getId();
            String name = cb.getUsername();
            int phone_no = cb.getPhone_no();
            String role = cb.getRole();
            String password = cb.getPassword();

            String preQueryStatement = "UPDATE user SET USERNAME=?, PASSWORD=?, ROLE=?, PHONE_NO=? WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, password);
            pStmnt.setString(3, role);
            pStmnt.setInt(4, phone_no);
            pStmnt.setInt(5, id);
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
