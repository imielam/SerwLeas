<%-- 
    Document   : leftpanel
    Created on : 2013-05-12, 14:16:23
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:useBean id="user" class="user.User"
                     scope="session"></jsp:useBean>
        
        <% String menucontents ="";
            if(user.getUsertype()==0){
                menucontents = "Witaj na stronie SerwLeas!"
                        + "<ul class=\"menu\">"
                        + "<li><a href=\"login.jsp\">Zaloguj</a></li>"
                        + "</ul>";
            }else if(user.getUsertype()==1){
                menucontents = "Witaj "+user.getName()+"!"
                        + "<ul class=\"menu\">"
                        + "<li><a href=\"controlPanel.jsp\">Panel Sterowania</a></li>"
                        + "<li><a href=\"logout.jsp\">Wyloguj</a></li>"
                        + "</ul>";
            }
        
        %>
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
    </body>
</html>
