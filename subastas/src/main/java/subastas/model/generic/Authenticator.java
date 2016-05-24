package subastas.model.generic;

import javax.mail.PasswordAuthentication;

public class Authenticator extends javax.mail.Authenticator{
	 private PasswordAuthentication authentication;
     public Authenticator() {
    	 String username = "subastas";
 		 String password = "Yachay2016";
         authentication = new PasswordAuthentication(username, password);
     }
     protected PasswordAuthentication getPasswordAuthentication() {
         return authentication;
     }
}
