<%-- 
    Document   : index
    Created on : 2013-05-09, 20:21:46
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Leasing</title>
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
                <table class="leasitem">
                    <tr>
                        <td><img src="img/karton.jpg" alt="placeholder" style="vertical-align: middle"></td><td>NAZWA</td>
                    </tr>
                    <tr>
                        <td colspan ="2">testeeteeetest giuehgibeaygbeageoague
                            anigybeaugbyeagyueagbieagojeauaeiybgeangieyabgaeng
                            obeaiygbeaiugbeaiygbeaigboeaiayebgueaiygbea
                            9ygbeaiygbi</td>
                    </tr>
                    <tr>
                        <td>Liczba dostępnych sztuk: </td><td>12</td>
                    </tr>
                    <tr>
                        <td>Cena za miesiąc: </td><td>3,50zł</td>
                    </tr>
                    <tr>
                        <td>Dodaj do koszyka:</td>
                        <td><input type="text" size="2" name="idxitemorderquant" value="0" style="text-align:right"></td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
