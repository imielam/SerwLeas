<%-- 
    Document   : finalizeOrder
    Created on : 2013-05-19, 23:10:22
    Author     : Ponury
--%>


<%@page import="extras.UserType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="inventory.Inventory, inventory.Item, java.util.Calendar, java.sql.Date, java.text.Format, 
         java.text.SimpleDateFormat, orders_new.OrderedItem, orders_new.ModelOrders, orders_new.Order, extras.UserType" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Koszyk</title>
    </head>
    <body>
        <jsp:useBean id="user" class="user.User"
                     scope="session"></jsp:useBean>
        <jsp:useBean id="neworder"
                     class="orders_new.OrderBean" scope="session"></jsp:useBean>
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
                    String time = request.getParameter("timeInMonths");
                    Calendar cal = Calendar.getInstance();
                    java.sql.Date startDate = new java.sql.Date(cal.getTimeInMillis());
                    cal.add(Calendar.MONTH, Integer.parseInt(time));
                    java.sql.Date endDate = new java.sql.Date(cal.getTimeInMillis());

                    Format f = new SimpleDateFormat("dd-MMMM-yyyy");
                    int itemcount = 0;
                    for(OrderedItem o : neworder.getOrdereditems()){
                        itemcount++;
                    }
                    ModelOrders mo = new ModelOrders();
                    try {
                        mo.addNewOrder(UserType.ADMIN, new Order(0,user.getUserid(),startDate,endDate,neworder.getOrdereditems()));
                        content = "Dokonałeś zamówienia na " + itemcount + " przedmiotów. Czas trwania: " + f.format(startDate.getTime()) + " do " + f.format(endDate.getTime());
                    } catch (Exception e){
                        content = "Wystąpił błąd przy dodawaniu zamówienia";
                    }

                %>

                <%=content%>

            </div>
        </div>
    </body>
</html>

