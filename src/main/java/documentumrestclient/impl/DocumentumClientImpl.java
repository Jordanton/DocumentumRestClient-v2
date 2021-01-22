package documentumrestclient.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import documentumrestclient.intf.DocumentumClientIntf;
import documentumrestclient.model.Application;

/**
 * @author 403522
 * JT - 04/21/2020
 *
 */
@Component
public class DocumentumClientImpl implements DocumentumClientIntf {

	private static Logger logger = LoggerFactory.getLogger( DocumentumClientImpl.class );

	/*
	 * JT - 04/24/2020
	 * Method to connect to the API to get the JSON object
	 *
	 */
	public JsonObject getJsonResponse( String objectId ) throws ConnectException, RestClientResponseException {

		// URL for QA environment
//		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";
		
		// URL for Production environment
		String apiURL = "https://api.lacity.org/documentum/repositories/cwdocbase1";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( "/documents/" )
		   .append( objectId );

		// Verify full URL
		logger.info( "Full URL: " + uri.toString() );

		HttpHeaders headers = getHeaders();

		headers.set( "Accept", MediaType.APPLICATION_JSON_VALUE );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, String.class );

		// Verify status code
		logger.info( "The Status Code:" + response.getStatusCode().toString() );

		if ( response.getStatusCode() == HttpStatus.OK ) {

			JsonObject jsonObject = new JsonParser().parse( response.getBody() ).getAsJsonObject();
			return jsonObject;
		}

		logger.info( "The JSON Response is null!" );

		return null;
	}

	/*
	 * JT - 04/24/2020
	 * Method to get the PDF, Jpeg, and Png files from the API
	 *
	 */
	public byte[] getByteArray( List<Application> applications, Integer id ) throws IOException {

		// URL for QA environment
//		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";
		
		// URL for Production environment
		String apiURL = "https://api.lacity.org/documentum/repositories/cwdocbase1";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( "/objects/" )
		   .append( applications.get(id).getObjectId() )
		   .append( "/content-media" );

		// Verify full URL
		logger.info( "Full URL: " + uri.toString() );

		HttpHeaders headers = getHeaders();

//		headers.setAccept( Arrays.asList( MediaType.APPLICATION_PDF, MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.APPLICATION_OCTET_STREAM ) );

		headers.setAccept( Arrays.asList( MediaType.APPLICATION_OCTET_STREAM ) );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<byte[]> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, byte[].class );

		byte[] content = response.getBody();

		// Verify status code
		logger.info( "The Status Code:" + response.getStatusCode().toString() );

		// Verify the size of the PDF file
		logger.info( "The Size of the Uploaded Document:" + content.length );

		if ( response.getStatusCode() == HttpStatus.OK ) {

			return content;
		}

		logger.info( "The byte array is null!" );

		return null;		
	}
	
/*
	@Override
	public byte[] getView( List<Application> applications, Integer id ) throws IOException {

		String apiURL = "https://api-test.lacity.org/documentum-qa/repositories/cwdocbase1";

		StringBuilder uri = new StringBuilder();

		uri.append( apiURL )
		   .append( "/objects/" )
		   .append( applications.get(id).getObjectId() )
		   .append( "/content-media" );

		// Verify full URL
		logger.info( "Full URL: " + uri.toString() );

		HttpHeaders headers = getHeaders();

//		headers.setAccept( Arrays.asList( MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM ) );

		headers.setAccept( Arrays.asList( MediaType.APPLICATION_OCTET_STREAM ) );

		HttpEntity<String> entity = new HttpEntity<>( headers );

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<byte[]> response = restTemplate.exchange( uri.toString(), HttpMethod.GET, entity, byte[].class );

		byte[] content = response.getBody();

		if ( response.getStatusCode() == HttpStatus.OK ) {

			return content;
		}

		logger.info( "The byte array is null!" );

		return null;
	}
*/
	
	/*
	 * JT - 4/24/2020
	 * Method to parse the JSON object to extract the name of the document
	 *
	 */
	@Override
	public String getObjectName( JsonObject jsonObject ) {

		String objectName = getParseJsonString( jsonObject, "object_name" );

		return objectName;
	}

	/*
	 * JT - 4/24/2020
	 * Method to parse the JSON object to extract a list of String using the property name
	 *
	 */
	@Override
	public String getParseJsonString( JsonObject jsonObject, String propertyName ) {

		String property = jsonObject.get( "properties" ).toString();

		JsonObject jsonProperties = new JsonParser().parse( property ).getAsJsonObject();

		String parseJsonString = jsonProperties.get( propertyName ).getAsString();

		return parseJsonString;
	}

	/*
	 * JT - 04/17/2020
	 * Method to return the HTTP Headers with API Key and Authentication
	 *
	 */
	public HttpHeaders getHeaders() {

		try ( InputStream input = DocumentumClientImpl.class.getResourceAsStream( "/documentum.properties" ) ) {

			Properties prop = new Properties();

			// Load a properties file
            prop.load( input );

            HttpHeaders headers = new HttpHeaders();

            headers.add( "x-api-key", prop.getProperty( "app.apikey" ) );

    	    headers.add( "Authorization", "Basic " + prop.getProperty( "app.authentication.viewer" ) );

            return headers;

		} catch ( FileNotFoundException e ) {

			logger.warn( "FileNotFoundException" );
			e.printStackTrace();

		} catch ( IOException e ) {

			logger.warn( "IOException" );
			e.printStackTrace();
		}

		return null;
	}

}
