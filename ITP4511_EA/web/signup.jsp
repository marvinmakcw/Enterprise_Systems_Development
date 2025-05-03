<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Sign up</h1>
        <form method="post" action="signup">
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
                        <p align="center"><input type="submit" value="Sign up"/></p>
                    </td>
                    <td>
                        <p align="center"><button onclick="location.href = 'index.jsp'" type="button">Back</button></p>
                    </td>
                </tr>
            </table>
        </form>  
    </body>
</html>
