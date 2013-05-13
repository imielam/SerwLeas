<%--
 % Example for using the free http://captchas.net Webservice
 % Documentation see http://captchas.net/sample/jsp/
--%>

<%@ page language="java" import="captchas.CaptchasDotNet, user.Validation, user.ModelUser, user.TUserData, address.TAddressData, company.TCompanyData, extras.UserType" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <title>Rejestracja - walidacja danych</title>
    </head>
    <body>
        <%
            CaptchasDotNet captchas = new captchas.CaptchasDotNet(
                    request.getSession(true), // Ensure session
                    "demo", // client
                    "secret" // secret
            );
            String captchapassword = request.getParameter("captchapassword");
            String username = request.getParameter("j_username");
            String password = request.getParameter("j_password");
            String passwordRepeat = request.getParameter("j_passwordRepeat");
            String email = request.getParameter("j_email");
            String pesel = request.getParameter("j_pesel");
            String companyname = request.getParameter("j_companyname");
            String companynip = request.getParameter("j_companynip");
            String companytown = request.getParameter("j_companytown");
            String companystreet = request.getParameter("j_companystreet");
            String companyhn = request.getParameter("j_companyhn");
            String companyan = request.getParameter("j_companyan");
            String companypostalcode = request.getParameter("j_companypostalcode");


            Validation validator = new Validation();
            String body;
            switch (captchas.check(captchapassword)) {
                case 's':
                    body = "Sesja wygasła bądź wystąpił błąd. ";
                    body += "Spróbuj ponownie bądź skontaktuj się z administratorem strony.";
                    break;
                case 'm':
                    body = "Każda CAPTCHA może zostać użyta wyłącznie jednokrotnie. ";
                    body += "Obecna CAPTCHA została już użyta. ";
                    body += "Użyj przycisku \"Cofnij\" oraz \"Odśwież\"";
                    break;
                case 'w':
                    body = "Wprowadziłeś zły tekst z CAPTCHY. ";
                    body += "Użyj przycisku \"Cofnij\" oraz \"Odśwież\", a następnie spróbuj ponownie. ";
                    break;
                default:
                    String validatorResponse = validator.validateRegisterData(username, password, passwordRepeat, email, pesel,
                            companyname, companynip, companytown, companystreet, companyhn,
                            companyan, companypostalcode);
                    if (validatorResponse == "ok") {
                        body = "Walidacja i kapcza poprawna. Niestety, tworzenie kont jest w danej chwili zablokowane.";
                        TCompanyData tcd = new TCompanyData(companyname,companynip);
                        TAddressData tad = new TAddressData(companypostalcode,companystreet,companyhn,Integer.parseInt(companyan),companytown);
                        TUserData tud = new TUserData(username,email,1,1,pesel); //bez sensu.
                        ModelUser mu = new ModelUser();
                        mu.addNewUser(UserType.CLIENT, tud, tcd, tad);
                        
                        
                    } else {
                        body = "CAPTCHA została wprowadzona poprawnie, ale dane nie przeszły walidacji. Pamiętaj, że na obecną chwilę, wśród danych nie może być polskich znaków, "
                                + "ponieważ zaprojektowanie tego aspektu zoutsource'owaliśmy do niewielkiej etiopijskiej wioski która nie podołała zadaniu."
                                + "<br />Błąd zwrócony przez walidator: " + validatorResponse;
                    }

                    break;
            }
        %>
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
                <%=body%>
            </div>
        
    </body>
</html>
