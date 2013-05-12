<%--
 % Example for using the free http://captchas.net Webservice
 % Documentation see http://captchas.net/sample/jsp/
 --%>

<%@ page language="java" import="captchas.CaptchasDotNet, user.Validation" %>

<html>
  <head>
    <title>Sample JSP CAPTCHA Query</title>
  </head>
  <h1>Sample JSP CAPTCHA Query</h1>
<%
// Construct the captchas object
// Use same settings as in query.jsp
CaptchasDotNet captchas = new captchas.CaptchasDotNet(
  request.getSession(true),     // Ensure session
  "demo",                       // client
  "secret"                      // secret
  );

// Read the form values
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
// Check captcha
String body;
switch (captchas.check(captchapassword)) {
  case 's':
    body = "Session seems to be timed out or broken. ";
    body += "Please try again or report error to administrator.";
    break;
  case 'm':
    body = "Every CAPTCHA can only be used once. ";
    body += "The current CAPTCHA has already been used. ";
    body += "Please use back button and reload";
    break;
  case 'w':
    body = "You entered the wrong password. ";
    body += "Please use back button and try again. ";
    break;
  default:
    String validatorResponse = validator.validateRegisterData(username,password,passwordRepeat,email,pesel,
            companyname,companynip,companytown,companystreet,companyhn,
            companyan,companypostalcode);
    if(validatorResponse == "ok"){
        body ="walidacja i kapcza poprawna i tworzymy konto";
    }else {
        body = "Kolego  " + username + " kapcze masz ok ale walidacje danych byla niepoprawna wiec teraz utknales na tej stronie lol."
                + "\nA blad byl taki"+validatorResponse;
    }
    
    break;
}
%>
<%=body%>
</html>
