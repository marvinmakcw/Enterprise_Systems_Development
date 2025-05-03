<%-- 
    Document   : adminEditUser
    Created on : 2023年4月26日, 上午12:44:52
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EPL_booking</title>
    </head>

    <body>
        <h1>Add-User</h1>
        <form method="post" action="handleUser">
            <table border="0">
                <tr><td>
                        <p align="left"><b>Username: </b></td>
                    <td><input type="text" name="username" maxlength="10" size="15" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Password: </b></td>
                    <td><input type="password" name="password" maxlength="10" size="15" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Confirm Password: </b></td>
                    <td><input type="password" name="cpassword" maxlength="10" size="15" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Phone: </b></td>
                    <td><input type="phone" name="phone" maxlength="8" size="15" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Role: </b></td>
                    <td><select name="role" id="role" length="15" required>
                            <option value="a">Admin</option>
                            <option value="s">Staff</option>
                            <option value="m">Member</option>
                        </select></td>
                </tr>
                <tr><td>
                        <p align="center"><input type="submit" value="Add User"/></p></td>
                    <td>
                        <button><a href="adminPage.jsp" style="text-decoration: none; color: black">Return</a></button>
                    </td>
                </tr>
            </table>
        </form>  
    </body>
</html>
