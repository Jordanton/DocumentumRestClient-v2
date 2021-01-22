package documentumrestclient.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import documentumrestclient.model.Application;
import documentumrestclient.model.dao.intf.ApplicationDao;

/**
 * @author 403522
 * JT - 05/01/2020
 *
 */
@Repository
public class ApplicationDaoImpl implements ApplicationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource( DataSource dataSource ) {
		
		jdbcTemplate = new JdbcTemplate( dataSource );
	}

	@Override
	public List<Application> getApplications( String appId ) {
		
		// SQL query for test databases 
//		String sql = "SELECT doc_id, doc_name, created_by FROM web_app_document_ids WHERE app_id = :appId";
		
		// SQL query for LTXPROD
		String sql = "SELECT doc_id, doc_name, created_by FROM latax.web_app_document_ids WHERE app_id = :appId";
		
		List<Application> applications = jdbcTemplate.query( sql, new Object[] { appId }, new RowMapper<Application>() {

			@Override
			public Application mapRow( ResultSet rs, int rowNum ) throws SQLException {
				
				Application application = new Application();
				
				application.setAppId( appId );
				application.setObjectId( rs.getString( "doc_id" ) );
				application.setDocName( rs.getString( "doc_name" ) );
				application.setCreatedBy( rs.getString( "created_by" ) );
				
				return application;
			}
			
		});
		
		return applications;
	}

}
