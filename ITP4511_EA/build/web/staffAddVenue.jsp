<%-- 
    Document   : staffAddVenue
    Created on : 2023年4月28日, 上午07:45:23
    Author     : osacr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Venue</title>
    </head>
    <body>
        <h1>Add-Venue</h1>
        <form method="post" action="handleVenue">
            <table border="0">
                <tr><td>
                        <p align="left"><b>Name: </b></td>
                    <td><input type="text" name="name" maxlength="50" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Type: </b></td>
                    <td><input type="text" name="type" maxlength="20" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Capacity: </b></td>
                    <td><input type="text" name="capacity" maxlength="10" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Location: </b></td>
                    <td><input type="text" name="location" maxlength="20" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Description: </b></td>
                    <td><input type="text" name="description" maxlength="50" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Person_in_charge: </b></td>
                    <td><input type="text" name="person_ic" maxlength="20" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Booking_fee: </b></td>
                    <td><input type="text" name="booking_fee" maxlength="10" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="left"><b>Status: </b></td>
                    <td><input type="text" name="status" maxlength="8" size="30" required></td>
                </tr>
                <tr><td>
                        <p align="center"><input type="submit" value="Add Venue"/></p></td>
                    <td>
                        <button><a href="staffMain.jsp" style="text-decoration: none; color: black">Return</a></button>
                    </td>
                </tr>
            </table>
        </form> 
    </body>
</html>
