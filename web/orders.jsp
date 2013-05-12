<%-- 
    Document   : index
    Created on : 2013-05-09, 20:21:46
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="orders.Orders, orders.Order, orders.OrderedItem" %>
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
        <jsp:useBean id="inventory"
                     class="inventory.Inventory" scope="session"></jsp:useBean>
        <jsp:useBean id="orders"
                     class="orders.Orders" scope="session"></jsp:useBean>
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
                    String content = "Sprawdzanie uprawnień...";
                    String tmp = "";
                    if (user.getUsertype() == 0) {
                        response.setHeader("Refresh", "0;url=login.jsp");
                    } else if (user.getUsertype() == 1) {
                        content = "";
                        for (Order o : orders.orders) {


                            if (o.getUserid() == user.getUserid()) {
                                content += "<table class=\"leasitem\">"
                                        + "<tr><td>Id zamówienia:</td><td>"+o.getOrderid()+"</td></tr>"
                                        + "<tr><td>Początek zamówienia:</td><td>"+o.getStartdate()+"</td></tr>"
                                        + "<tr><td>Koniec zamówienia:</td><td>"+o.getEnddate()+"</td></tr>"
                                        + "<tr><td colspan=\"2\">Zamówione przedmioty</td></tr>";
                                for (OrderedItem oi : o.getOrdereditems()) {
                                    content += "<tr><td>"+inventory.inventory.get(oi.itemid).getName()+"</td>"
                                            + "<td>"+oi.quantity+"</td></tr>";
                                }
                                content += "</table><br />";
                            }
                        }

                    } else if (user.getUsertype() == 2) {
                        content = "<p>Wybierz klienta: </p>";
                        content += "<table class=\"leasitem\">";
                        
                        content += "</table>";
                    } else if (user.getUsertype() == 3) {
                        content = "";
                    }
                %>
                <%=content%>
            </div>
        </div>
    </body>
</html>