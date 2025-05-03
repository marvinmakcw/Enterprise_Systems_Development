<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="main">
            <input type="hidden" name="action" value="authenticate">
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
                        <p align="center"><input type="submit" value="Login"></p></td>
                    <td><a href="signup.jsp">
                            <input type="button" value="Sign up" >
                        </a>
                    </td>
                </tr>
            </table> 
        </form>
    </body>
</html>
