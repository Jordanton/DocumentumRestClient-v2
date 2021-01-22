package documentumrestclient.model;

import java.io.Serializable;

/**
 * @author 403522
 * JT - 04/21/2020
 *
 */
public class Application implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String appId;

	private String objectId;
	
	private String docName;
	
	private String createdBy;
		
	public Application() {
		
	}

	public Application( String appId, String objectId, String docName, String createdBy ) {
		
		this.appId = appId;
		this.objectId = objectId;
		this.docName = docName;
		this.createdBy = createdBy;
	}

	public String getAppId() {
		
		return appId;
	}

	public void setAppId( String appId ) {
		
		this.appId = appId;
	}

	public String getObjectId() {
		
		return objectId;
	}

	public void setObjectId( String objectId ) {
		
		this.objectId = objectId;
	}

	public String getDocName() {
		
		return docName;
	}

	public void setDocName( String docName ) {
		
		this.docName = docName;
	}

	public String getCreatedBy() {
		
		return createdBy;
	}

	public void setCreatedBy( String createdBy ) {
		
		this.createdBy = createdBy;
	}

}
