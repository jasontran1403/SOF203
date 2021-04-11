/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

/**
 *
 * @author Admin
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EmailValidator{
private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern;
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
 
    public boolean validate(final String hex) {
 
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
 
    }
 
    // main và test
    public static void main(String[] args) {
        String email = "nguyendangkhiemit@gmailcom";
        EmailValidator validator = new EmailValidator();
        if (!validator.validate(email)) {
            System.out.println("");
        }
    }
} 
