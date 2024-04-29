package com.snhu.sslserver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
	
	public static String calculateHash(String name) throws Exception { 
		   MessageDigest md = MessageDigest.getInstance("SHA-256");
		   
		   byte[] hash = md.digest(name.getBytes(StandardCharsets.UTF_8));
		   StringBuilder hexToString = new StringBuilder(2 * hash.length);
		   for (int i = 0; i < hash.length; i++) {
			   String hex = Integer.toHexString(0xff & hash[i]);
			   if(hex.length() == 1) {
				   hexToString.append('0');
			   }
			   hexToString.append(hex);
	    }
		   return hexToString.toString();

}
	private Connector redirectConnectory() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}
	
	@RequestMapping("/hash")
    public String myHash(){
    	String data = "Hello World Check Sum!";
       
        return "<p>data:"+data;
    }
}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";