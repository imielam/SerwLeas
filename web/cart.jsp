<%-- 
    Document   : index
    Created on : 2013-05-09, 20:21:46
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="inventory.Inventory, inventory.Item, orders_new.OrderBean, orders_new.OrderedItem" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Koszyk</title>
            <script>
            function validate(evt) {
                var theEvent = evt || window.event;
                var key = theEvent.keyCode || theEvent.which;
                key = String.fromCharCode(key);
                var regex = /[0-9]/;
                if (!regex.test(key)) {
                    theEvent.returnValue = false;
                    if (theEvent.preventDefault)
                        theEvent.preventDefault();
                }
            }
        </script>
    </head>
    <body>
        <jsp:useBean id="inventory"
                     class="inventory.Inventory" scope="session"></jsp:useBean>
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
                    String tmp = "";
                    int orderedItemsNum = 0;
                    int [] itemQuantNum = new int[inventory.inventory.size()];
                    String [] itemQuant = new String[inventory.inventory.size()];
                    for(int i =0; i<itemQuant.length;i++){
                        itemQuant[i] = new String("");
                    }
                    for (int i=1; i<itemQuantNum.length+1 ;i++) {
                        try{
                            itemQuantNum[i-1] = Integer.parseInt(request.getParameter("item"+i+"orderquant"));
                            session.setAttribute("item"+i+"orderquant", request.getParameter("item"+i+"orderquant"));
                            if(itemQuantNum[i-1]!=0){
                                orderedItemsNum++;
                            }                                
                        }catch(Exception e){
                        }
                    }
                    if(orderedItemsNum!=0){
                        OrderedItem o;
                        content+= " <form name=\"frm\" method=\"post\" action=\"finalizeOrder.jsp\"\">";
                        content+="Zamawiane przedmioty: <br /><table class = leasitem>";
                        for (int i=0; i<itemQuantNum.length ;i++){
                            if(itemQuantNum[i]!=0){    
                                content+="<tr><td>"+inventory.inventory.get(i).getName()+"</td>"
                                        + "<td>"+session.getAttribute("item"+(i+1)+"orderquant")+"</td>"
                                        + "<td> Cena za mc: "+inventory.inventory.get(i).getBase_price()*itemQuantNum[i]+"</td></tr>";
                                        //+ "<td>"+itemQuantNum[i]+"</td></tr>";
                                neworder.addItem(new OrderedItem(i+1, itemQuantNum[i]));
                            }
                        }
                        content+="</table>"
                                + "Czas umowy w miesiącach (3-12): <input type=\"text\" size=\"2\" onkeypress=\"validate(event)\" name=\"timeInMonths\" value=\"3\" style=\"text-align:right\">"
                        + "<p><input type=\"submit\" value=\"Złóż zamówienie\" /></p></form>";
                        
                    }else {
                        content="<p>Twój koszyk jest pusty.</p>";
                    }
                    
                %>

                <%=content%>

            </div>
        </div>
    </body>
</html>
