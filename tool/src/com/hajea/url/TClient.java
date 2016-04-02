package com.hajea.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
//http://donald3003a.iteye.com/blog/1688010
public class TClient {

	 public static void main(String[] args) throws Exception {  
	        System.setProperty("javax.net.ssl.trustStore", "C:/Users/Administrator/.keystore" );  
	        new TClient().test();  
	    }  
	  
	    private void test() {  
	        String https_url = "https://localhost:8443/";  
	        URL url;  
	        try {  
	            url = new URL(https_url);  
	            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
	            connection.setDoOutput(true);  
	            connection.setRequestMethod("POST");  
	            connection.getOutputStream().flush();  
	            connection.getOutputStream().close();  
	            System.out.println( connection.getPeerPrincipal().toString() );  
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
}
