<%--
    Document   : logout
    Created on : 2013-05-11, 20:55:06
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Wylogowywanie</title>
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

            <div class="leftmenu">
                <ul class="menu">
                    <li><a href="login.jsp">Zaloguj</a></li>
                    <li><a href="link2.html">link2</a></li>
                    <li><a href="link3.html">link3</a></li>
                    <li><a href="link4.html">link4</a></li>
                    <li><a href="link5.html">link5</a></li>
                    <li><a href="link6.html">link6</a></li>
                </ul>
            </div>
            <div class="content">
                <div id="loginBox">
                    <p>Zostaniesz teraz wylogowany.</p>
                    <% session.invalidate();%>
                    <% response.setHeader("Refresh", "3;url=index.jsp");%>
                </div>
            </div>
        </div>

    </body>
</html>
