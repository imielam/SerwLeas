<%-- 
    Document   : register
    Created on : 2013-05-11, 13:13:10
    Author     : Ponury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page language="java" import="captchas.CaptchasDotNet" import="user.Validation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>SerwLeas</title>

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

                <%
                    // Construct the captchas object (Default Values)
                    CaptchasDotNet captchas = new captchas.CaptchasDotNet(
                            request.getSession(true), // Ensure session
                            "demo", // client
                            "secret" // secret
                            );
                    // Construct the captchas object (Extended example)
                    // CaptchasDotNet captchas = new captchas.CaptchasDotNet(
                    //  request.getSession(true),     // Ensure session
                    //  "demo",                       // client
                    //  "secret",                     // secret
                    //  "01",                         // alphabet
                    //  16,                           // letters
                    //  500,                          // width
                    //  80                            // height
                    //  );
                %>

                <%-- 
                 % encodeUrl produces jsessionid=xyz in case of disabled cookies
                 % Please test your implementation also with disabled cookies
                --%>
                <form name="frm" method="post" onsubmit="return validateRegisterData()" action="<%=response.encodeUrl("check.jsp")%>">
                    <div id="loginBox">
                        <h4>Dane użytkownika</h4>

                        <table class="regTable" cellspacing="6px">
                            <tr>
                                <td><strong>Login: </strong></td>
                                <td><input type="text" size="20" name="j_username"></td>
                            </tr>
                            <tr>
                                <td><strong>Hasło: </strong></td>
                                <td><input type="password" size="20" name="j_password"></td>
                            </tr>
                            <tr>
                                <td><strong>Powtórz hasło: </strong></td>
                                <td><input type="password" size="20" name="j_passwordRepeat"></td>
                            </tr>
                            <tr>
                                <td><strong>E-mail: </strong></td>
                                <td><input type="text" size="20" name="j_email"></td>
                            </tr>
                            <tr>
                                <td><strong>PESEL: </strong></td>
                                <td><input type="text" size="20" name="j_pesel"></td>
                            </tr>
                        </table>
                        <h4>Dane firmy</h4>
                        <table class ="regTable" cellspacing="6px">
                            <tr>
                                <td><strong>Nazwa firmy: </strong></td>
                                <td><input type="text" size="20" name="j_companyname"></td>
                            </tr>
                            <tr>
                                <td><strong>NIP: </strong></td>
                                <td><input type="text" size="20" name="j_companynip"></td>
                            </tr>
                        </table>
                        <h4>Adres firmy</h4>
                        <table class ="regTable" cellspacing="6px">
                            <tr>
                                <td><strong>Miasto: </strong></td>
                                <td><input type="text" size="20" name="j_companytown"></td>
                            </tr>
                            <tr>
                                <td><strong>Ulica: </strong></td>
                                <td><input type="text" size="20" name="j_companystreet"></td>
                            </tr>
                            <tr>
                                <td><strong>Numer ulicy: </strong></td>
                                <td><input type="text" size="20" name="j_companyhn"></td>
                            </tr>
                            <tr>
                                <td><strong>Numer mieszkania: </strong></td>
                                <td><input type="text" size="20" name="j_companyan"></td>
                            </tr>
                            <tr>
                                <td><strong>Kod pocztowy: </strong></td>
                                <td><input type="text" size="20" name="j_companypostalcode"></td>
                            </tr>
                        </table>



                        <p><%= captchas.image()%></p>
                        <p>Przepisz kod z obrazka <br />
                            <input name="captchapassword" size="16" /></p>
                        <p><input type="submit" value="Zarejestruj" /></p>
                </form>

            </div>
        </div>
    </div>
</body>
</html>
