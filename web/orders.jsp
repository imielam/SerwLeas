<%-- 
    Document   : index
    Created on : 2013-05-09, 20:21:46
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="orders_new.TOrderForUser, inventory.Item, orders_new.OrderedItem, orders_new.Order, orders_new.ModelOrders, orders_new.TOrderAmount,
         extras.UserType, java.util.List, java.util.ArrayList" %>
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
        <jsp:useBean id="mo"
                     class="orders_new.ModelOrders" scope="session"></jsp:useBean>
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
                    inventory.getItems();
                    if (user.getUsertype() == 0) {
                        response.setHeader("Refresh", "0;url=login.jsp");
                    } else if (user.getUsertype() == 1) {
                        content = "";
                        List<TOrderForUser> ordersForUser = mo.getOrdersForUser(UserType.CLIENT, user.getName());
                        List<Order> orders = new ArrayList<Order>();
                        boolean makeANewOrder;
                        for (TOrderForUser tofu : ordersForUser) {
                            makeANewOrder = true;
                            for (Order o : orders) {
                                if (tofu.getOrderId() == o.getOrderid()) {
                                    makeANewOrder = false;
                                    o.addOrderedItem(new OrderedItem(tofu.getProductId(), tofu.getQuality()));
                                }
                            }
                            if (makeANewOrder) {
                                List<OrderedItem> ordereditems = new ArrayList<OrderedItem>();
                                ordereditems.add(new OrderedItem(tofu.getProductId(), tofu.getQuality()));
                                orders.add(new Order(tofu.getOrderId(), tofu.getUserId(), tofu.getStartDate(), tofu.getEndDate(), ordereditems));
                            }
                        }

                        for (Order o : orders) {

                            if (o.getUserid() == user.getUserid()) {
                                try {
                                    content += "<table class=\"leasitem\">"
                                            + "<tr><td>Id zamówienia:</td><td>" + o.getOrderid() + "</td></tr>"
                                            + "<tr><td>Początek zamówienia:</td><td>" + o.getStartdate() + "</td></tr>"
                                            + "<tr><td>Koniec zamówienia:</td><td>" + o.getEnddate() + "</td></tr>"
                                            + "<tr><td colspan=\"2\">Zamówione przedmioty</td></tr>";
                                    for (OrderedItem oi : o.getOrdereditems()) {
                                        content += "<tr><td>" + inventory.inventory.get(oi.itemid-1).getName() + "</td>"
                                                + "<td>" + oi.quantity + "</td></tr>";

                                    }
                                    content += "</table><br />";
                                } catch (Exception e) {
                                    content += "Nastąpił problem przy wyświetlaniu zamówień";
                                    break;
                                }

                            }
                        }

                    } else if (user.getUsertype() == 2) {
                        content = "";
                        content += "<table class=\"leasitem\">";
                        List<TOrderAmount> uwo = mo.getAllUsersWithOrders(UserType.SALESMAN);
                        content += "<tr><td>Klient</td><td>Ilość zamówień</td></tr>";
                        for (TOrderAmount toa : uwo) {
                            content += "<tr><td><a id\"=" + toa.getUserName() + "\">" + toa.getUserName() + "</a></td><td>" + toa.getAmount() + "</td></tr>";
                        }
                        content += "</table><br />";

                        //----------------MAGICZNA ŚCIANA SKOPIOWANEGO KODU------------------------
                        //dla kazdego usera
                        for (TOrderAmount toa : uwo) {
                            content += "<p>"+toa.getUserName()+"</p>";
                            List<TOrderForUser> ordersForUser = mo.getOrdersForUser(UserType.CLIENT, toa.getUserName());
                            List<Order> orders = new ArrayList<Order>();
                            boolean makeANewOrder;
                            for (TOrderForUser tofu : ordersForUser) {
                                makeANewOrder = true;
                                for (Order o : orders) {
                                    if (tofu.getOrderId() == o.getOrderid()) {
                                        makeANewOrder = false;
                                        o.addOrderedItem(new OrderedItem(tofu.getProductId(), tofu.getQuality()));
                                    }
                                }
                                if (makeANewOrder) {
                                    List<OrderedItem> ordereditems = new ArrayList<OrderedItem>();
                                    ordereditems.add(new OrderedItem(tofu.getProductId(), tofu.getQuality()));
                                    orders.add(new Order(tofu.getOrderId(), tofu.getUserId(), tofu.getStartDate(), tofu.getEndDate(), ordereditems));
                                }
                            }

                            for (Order o : orders) {
                                if (o.getUserid()== toa.getUserId()) {
                                    try {
                                        content += "<table class=\"leasitem\">"
                                                + "<tr><td>Id zamówienia:</td><td>" + o.getOrderid() + "</td></tr>"
                                                + "<tr><td>Początek zamówienia:</td><td>" + o.getStartdate() + "</td></tr>"
                                                + "<tr><td>Koniec zamówienia:</td><td>" + o.getEnddate() + "</td></tr>"
                                                + "<tr><td colspan=\"2\">Zamówione przedmioty</td></tr>";
                                        for (OrderedItem oi : o.getOrdereditems()) {
                                            content += "<tr><td>" + inventory.inventory.get(oi.itemid-1).getName() + "</td>"
                                                    + "<td>" + oi.quantity + "</td></tr>";

                                        }
                                        content += "</table><br />";
                                    } catch (Exception e) {
                                        content += "Nastąpił problem przy wyświetlaniu zamówień";
                                        break;
                                    }

                                }
                            }
                        }

                        //---------------KONIEC MAGICZNEJ ŚCIANY SKOPIOWANEGO KODU (Sorki)---------
                    } else if (user.getUsertype() == 3) {
                        content = "";
                    }
                %>
                <%=content%>
            </div>

        </div>
    </body>
</html>