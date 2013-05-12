<%-- 
    Document   : index
    Created on : 2013-05-09, 20:21:46
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>SerwLeas</title>
    </head>
    <body>
        <div id="top">
            <div id ="header"><img src ="img/servleaslogo.jpg" alt ="logo"></div>
            <div id ="headermenu">
                <div class="headermenu_pos">
                    <a href="index.jsp">Aktualności</a></div>
                <div class="headermenu_pos">
                    <a href="ofirmie.jsp">O firmie</a></div>
                <div class="headermenu_pos">
                    <a href="inventory.jsp">Leasing</a></div>
                <div class="headermenu_pos">
                    <a href="cart.jsp">Koszyk</a></div>
                <div class="headermenu_pos">
                    <a href="controlPanel.jsp">Profil</a></div>
                <div class="headermenu_posend">
                    <a href="kontakt.jsp">Kontakt</a></div>
            </div>
        </div>
        <div id="main">
            <jsp:include page="leftpanel.jsp"/>
            <div class="content">
                <form method="post" action ="validate.jsp">
                <div id="loginBox">
                    <p><strong>Login: </strong>
                        <input type="text" size="20" name="name"></p>

                    <p><strong>Hasło: </strong>
                        <input type="password" size="20" name="password"></p>
                    <p><input type="submit" value="Zaloguj" /></p>
                </form>
                    <p>Nie posiadasz jeszcze konta? <a href="register.jsp">Zarejestruj się.</a></p>
                </div>
            </div>
        </div>
    </body>
</html>
