/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ponury
 */
public class Validation {

    public String validateRegisterData(String username, String password, String passwordRepeat, String email,
            String PESEL, String companyname, String companynip, String companytown, String companystreet,
            String companystreethn, String companystreetan, String companypostalcode) {
        //username
        Pattern lettersOnly = Pattern.compile("[a-zA-Z]+");
        Pattern numbersOnly = Pattern.compile("[0-9]+");
        Pattern postalCode = Pattern.compile("[0-9]{2}-[0-9]{3}");
        Pattern extended = Pattern.compile("[a-zA-Z0-9\\. -]+");
        Pattern mail = Pattern.compile("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        
        Matcher m = lettersOnly.matcher(username);
        if (!m.matches() || username.length()<4 || username.length() > 16) {
            return "Błąd w nazwie użytkownika! Nazwa użytkownika powinna zawierać wyłącznie małe i duże litery bez polskich znaków i mieć długość pomiędzy 4 a 16.";
        }
        m = extended.matcher(password);
        if(!m.matches() || password.length()<4 || password.length() >16){
            return "Hasło musi miec od 4 do 16 znakow i skladac sie z cyfr oraz liter (bez polskich znakow)";
        }
        if(!passwordRepeat.equals(password)){
            return "Hasla nie sa zgodne!";
        }
        m = mail.matcher(email);
        if(!m.matches()){
            return "Nieprawidlowy adres email!";
        }
        m = numbersOnly.matcher(PESEL);
        if(!m.matches() || PESEL.length() != 11){
            return "Nieprawidlowy numer PESEL. Numer PESEL powinien sie składać z 11 cyfr.";
        }
        m = extended.matcher(companyname);
        if(!m.matches() || companyname.length()<3 || companyname.length() >25){
            return "Blad w nazwie firmy";
        }
        m = numbersOnly.matcher(companynip);
        if(!m.matches() || companynip.length() != 10){
            return "Nieprawidlowy NIP. Wprowadz NIP bez myślników. NIP musi mieć 10 znaków.";
        }
        m = lettersOnly.matcher(companytown);
        if(!m.matches() || companytown.length() < 3 || companytown.length() > 16){
            return "Błędna nazwa miasta.";
        }
        m = extended.matcher(companystreet);
        if(!m.matches() || companystreet.length() <3 || companystreet.length() > 16){
            return "Błędna nazwa ulicy.";
        }
        m = numbersOnly.matcher(companystreethn);
        if(!m.matches()){
            return "Zły numer ulicy." + companystreethn;
        }
        m = numbersOnly.matcher(companystreetan);
        if(companystreetan.length()!=0 && !m.matches()){
            return "Nieprawidlowy numer mieszkania.";
        }
        m = postalCode.matcher(companypostalcode);
        if(!m.matches()){
            return "Nieprawidlowy kod pocztowy.";
        }

        return "ok";
    }
}
