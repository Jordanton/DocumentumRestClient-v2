package documentumrestclient.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import documentumrestclient.intf.DocumentumClientIntf;
import documentumrestclient.model.Application;
import documentumrestclient.model.dao.intf.ApplicationDao;

/**
 * @author 403522
 * JT - 04/27/2020
 * 
 */
@Controller
public class DocumentumController {

	private static Logger logger = LoggerFactory.getLogger( DocumentumController.class );
	
	@Autowired
	private DocumentumClientIntf documentumClient;
	
	@Autowired
	private ApplicationDao applicationDao;
		
	/**
	 * JT - 05/12/2020
	 * This method handles the Get Request for Scers, Non-Profit, and Vendor with parameter app id
	 * JT - Add conditions to display the application type
	 */
	@RequestMapping(value = "/{appId}.html", method = RequestMethod.GET)
	public String documentumSearch( @PathVariable String appId, ModelMap models, HttpSession session ) {
				
		List<Application> applications = new ArrayList<>();
		
		applications = applicationDao.getApplications( appId );
		
		if ( applications.isEmpty() ) {
			
			logger.info( "The list of applications is empty: " + applications.isEmpty() );
			
			return "error2";
		}
		
		logger.info( "The number of uploaded documents: " + applications.size() );
		
		models.put( "applications", applications );
		
		// Conditions to check for the application type
		if ( applications.get( 0 ).getCreatedBy().equalsIgnoreCase( "NP" ) ) {
			
			models.put( "type", "Non-Profit" );
			
		} else if ( applications.get( 0 ).getCreatedBy().equalsIgnoreCase( "LL" ) ) {
			
			models.put( "type", "Lifeline" );
			
		} else if ( applications.get( 0 ).getCreatedBy().equalsIgnoreCase( "V" ) ) {
			
			models.put( "type", "Vendor" );
		}
		
		// Put the list of objects in session scope
		session.setAttribute( "applications", applications );
		
		return "documentum_search_result";
	}
	
	/*	
	@RequestMapping(value = {"/", "/index.html", "/documentum_search.html"}, method = RequestMethod.GET)
	public String documentumSearch( ModelMap models ) {
		
		Application application = new Application();
		
		models.put( "application", application );
		
		return "documentum_search";
	}
	*/
	
	/*	
	@RequestMapping(value = {"/", "/index.html", "/documentum_search.html"}, method = RequestMethod.POST)
	public String documentumSearch( @ModelAttribute Application application, ModelMap models, HttpSession session ) {
		
		List<Application> applications = new ArrayList<>();
		
		applications = applicationDao.getApplications( application.getAppId() );
		
		if ( applications.isEmpty() ) {
			
			logger.info( "The list of applications is empty: " + applications.isEmpty() );
			
			return "error2";
		}
		
		logger.info( "The number of uploaded documents: " + applications.size() );
				
		models.put( "applications", applications );
		
		// Put the list of objects in session scope
		session.setAttribute( "applications", applications );
		
		return "documentum_search_result";
	}
	*/
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/documentum_search_result", method = RequestMethod.GET)
	public String documentumSearchResult( ModelMap models, HttpSession session ) {
		
		// Get the list of objects from session scope
		List<Application> applications = (List<Application>) session.getAttribute( "applications" );
				
		models.put( "applications", applications );
		
		return "documentum_search_result";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/documentum_view/{id}.html", method = RequestMethod.GET)
	public String documentumView( @PathVariable Integer id, HttpSession session, HttpServletResponse response ) {
		
		// Get the list of objects from session scope
		List<Application> applications = (List<Application>) session.getAttribute( "applications" );
		
		try {
						
			if ( applications.get(id).getDocName().contains("pdf") ) {
				
				response.setContentType( "application/pdf" );
				
			} else if ( applications.get(id).getDocName().contains("jpg") ) {
				
				response.setContentType( "image/jpg" );
				
			} else if ( applications.get(id).getDocName().contains("png") ) {
				
				response.setContentType( "image/png" );
				
			}	
			
			response.setHeader( "Content-Disposition", "inline; filename=" + applications.get(id).getDocName() );
			
			byte[] content = documentumClient.getByteArray( applications, id );
			
			OutputStream outStream = response.getOutputStream();
			
			outStream.write( content );
			
		} catch ( IOException e ) {
			
			logger.error( "IOException" );
			e.printStackTrace();
			return "error";
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/documentum_download/{id}.html", method = RequestMethod.GET)
	public String documentumDownload( @PathVariable Integer id, HttpSession session, HttpServletResponse response ) {
		
		// Get the list of objects from session scope
		List<Application> applications = (List<Application>) session.getAttribute( "applications" );
		
		try {
			
			if ( applications.get(id).getDocName().contains("pdf") ) {
				
				response.setContentType( "application/pdf" );
				
			} else if ( applications.get(id).getDocName().contains("jpg") ) {
				
				response.setContentType( "image/jpg" );
				
			} else if ( applications.get(id).getDocName().contains("png") ) {
				
				response.setContentType( "image/png" );
				
			}		
			
			response.setHeader( "Content-Disposition", "attachment; filename=" + applications.get(id).getDocName() );
			
			byte[] content = documentumClient.getByteArray( applications, id );
			
			OutputStream outStream = response.getOutputStream();
			
			outStream.write( content );
			
		} catch ( IOException e ) {
			
			logger.error( "IOException" );
			e.printStackTrace();
			return "error";
		}
		
		return null;
	}
	
}
