package documentumrestclient.model.dao.intf;

import java.util.List;

import documentumrestclient.model.Application;

/**
 * @author 403522
 * JT - 04/28/2020
 *
 */
public interface ApplicationDao {

	public abstract List<Application> getApplications( String appId );
	
}
