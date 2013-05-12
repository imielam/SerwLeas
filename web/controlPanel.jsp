<%-- 
    Document   : controlpanel
    Created on : 2013-05-11, 20:43:07
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Panel kontrolny</title>
    </head>
    <body>
        <jsp:useBean id="user" class="user.User"
                     scope="session"></jsp:useBean>

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
                    
                <%
                    String content ="Sprawdzanie uprawnień...";
                    if (user.getUsertype()==0) {

                        response.setHeader("Refresh", "0;url=login.jsp");
                    } else if (user.getUsertype() == 1) {
                        content = "Witaj "+user.getName()+". Masz uprawnienia Klienta.<p> <a href=\"logout.jsp\">Wyloguj</a></p>";
                    }
                %>
                <%=content%>
                </div>
        
    </div>
</div>
</body>
</html>
