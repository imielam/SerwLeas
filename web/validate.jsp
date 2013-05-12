<%-- 
    Document   : validate
    Created on : 2013-05-11, 19:04:42
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Walidacja</title>
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
                <jsp:useBean id="user" class="user.User"
                             scope="session"></jsp:useBean>
                <jsp:setProperty property="*" name="user" />
                
                <jsp:useBean id="dataSource"
                             class="database.DataSource" scope="session"></jsp:useBean>

                <% String result = "";

                    if (dataSource.userExists(user)) {
                        user.setUsertype(1);
                        session.setMaxInactiveInterval(600); // 10 minutowa sesja
                        result = "Witaj " + user.getName() + "! Przekierowanie w toku...";
                        response.setHeader("Refresh", "3;url=controlPanel.jsp");
                    } else {
                        result = "Dane niepoprawne. Przekierowanie na stronę logowania w toku...";
                        response.setHeader("Refresh", "3;url=login.jsp");
                    }
                %>
                <div id="loginBox">
                    <%= result%>
                </div>
            </div>
        </div>

    </body>
</html>
