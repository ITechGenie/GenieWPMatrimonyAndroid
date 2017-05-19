package com.itechgenie.auth;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

public class GoogleOAuth {
	
	private static String webHost = "114.1.1.9" ;
	private static String webProtocol = "http" ;
	private static String webBaseUrl =  null ;
	private static String webContext = "/wp473" ;
	private static String oAuth1AuthorizeUri = "/oauth1/authorize" ;
	private static String oAuth1RequestUri = "/oauth1/request" ;
	private static String oAuth1AccessUri = "/oauth1/access" ;
	
	 /** Directory to store user credentials. */
	  private static final java.io.File DATA_STORE_DIR =
	      new java.io.File(System.getProperty("user.home"), ".store/dailymotion_sample");

	  /**
	   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
	   * globally shared instance across your application.
	   */
	  private static FileDataStoreFactory DATA_STORE_FACTORY;

	  /** OAuth 2 scope. */
	  private static final String SCOPE = "read";

	  /** Global instance of the HTTP transport. */
	  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	  /** Global instance of the JSON factory. */
	  static final JsonFactory JSON_FACTORY = new JacksonFactory();

	  private static final String TOKEN_SERVER_URL = webProtocol + webBaseUrl + webContext + oAuth1RequestUri ;
	  private static final String AUTHORIZATION_SERVER_URL = webProtocol + webBaseUrl + webContext + oAuth1AuthorizeUri ;
	  
		private static String key = "iY5jtwTrQ586";
		private static String secret = "zw0g73xhkcpWxQ3CsTcVJVKMlu50x1uqR0hGNSU29AEz1co8";

	  
	  public static void main(String[] args) throws Exception {
		  
		  OAuthHmacSigner signer = new OAuthHmacSigner();
		    signer.clientSharedSecret = secret ;
		    signer.tokenSharedSecret = "UJLcoXLNL4Ek3PAWsoZYpSC05TfBGSWQN72amH3tfTGtntIg" ;
		  
		  String nounce = String.valueOf( (int) (Math.random() * 100000000) ) ;
		  String timeStamp = String.valueOf(System.currentTimeMillis() / 1000) ;
		
		  OAuthParameters parameters = new OAuthParameters();
		  
		    parameters.consumerKey = key ;
		    parameters.nonce = "40077762" ;
		    parameters.signatureMethod = "HMAC-SHA1";
		    parameters.timestamp = "1492942742" ;
		    parameters.signer = signer ;
		    
		    GenericUrl genericUrl = new GenericUrl() ;
		    genericUrl.setScheme(webProtocol);
		    genericUrl.setHost(webHost);
		    genericUrl.setFragment(webContext);
		    genericUrl.setRawPath(oAuth1AccessUri);
		    
		  parameters.computeSignature("POST", genericUrl ); 
		    
		 String headers =   parameters.getAuthorizationHeader() ;
		 
		 System.out.println("Headers: " + headers );
		 
		  
		  
	}

}
