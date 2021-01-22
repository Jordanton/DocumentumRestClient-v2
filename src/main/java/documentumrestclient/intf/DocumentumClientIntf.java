package documentumrestclient.intf;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;

import com.google.gson.JsonObject;

import documentumrestclient.model.Application;

/**
 * @author 403522
 * JT - 04/21/2020
 *
 */
public interface DocumentumClientIntf {

	public abstract JsonObject getJsonResponse( String objectId ) throws ConnectException, RestClientResponseException;
	
	public abstract byte[] getByteArray( List<Application> applications, Integer id ) throws IOException;
	
	public abstract String getObjectName( JsonObject jsonObject );
	
	public abstract String getParseJsonString( JsonObject jsonObject, String propertyName );
			
	public abstract HttpHeaders getHeaders();
	
}
