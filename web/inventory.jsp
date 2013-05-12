<%-- 
    Document   : index
    Created on : 2013-05-09, 20:21:46
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="inventory.Inventory, inventory.Item" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Leasing</title>
    </head>
    <body>
        <jsp:useBean id="inventory"
                     class="inventory.Inventory" scope="session"></jsp:useBean>
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
                    String content = "";
                    String tmp = "";
                    for (Item i : inventory.inventory) {
                        tmp = "<table class=\"leasitem\">"
                                + "<tr>"
                                + "<td><img src=\"img/karton.jpg\" alt=\"placeholder\" style=\"vertical-align: middle\"></td><td>"+i.getName()+"</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td colspan =\"2\">"+i.getDescription()+"</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td>Liczba dostępnych sztuk: </td><td>"+i.getAviable()+"</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td>Cena za miesiąc: </td><td>"+i.getBase_price()+"</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<td>Dodaj do koszyka:</td>"
                                + "<td><input type=\"text\" size=\"2\" name=\"item"+i.getId()+"orderquant\" value=\"0\" style=\"text-align:right\"></td>"
                                + "</tr>"
                                + "</table>"
                                + "<br />";
                        content = content + tmp;
                    }
                %>

                <%=content%>

            </div>
        </div>
    </body>
</html>
