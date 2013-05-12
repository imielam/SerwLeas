<%-- 
    Document   : index
    Created on : 2013-05-09, 20:21:46
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="Orders.Orders, Orders.Order, Orders.OrderedItem" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Zamówienia</title>
    </head>
    <body>
        <jsp:useBean id="user" class="user.User"
                     scope="session"></jsp:useBean>
        <jsp:useBean id="orders"
                     class="Orders" scope="session"></jsp:useBean>
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
                        response.setHeader("Refresh","0;url=login.jsp");
                    } else if (user.getUsertype() == 1) {
                        for (Order o : orders.orders){
                            
                        }
                        content = "";
                    } else if (user.getUsertype() == 2){
                        content = "";
                    } else if (user.getUsertype() == 3){
                        content = "";
                    }
                    
                    
                %>
                <%=content%>
            </div>
        </div>
    </body>
</html>
