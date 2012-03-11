package de.fhb.mp3.controller.web.actions;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn ein User auf der Seite surft
 * @author diesel
 *
 */
public class ActionMainPage extends HttpRequestActionBase {

	private static org.apache.log4j.Logger log = Logger.getLogger(ActionMainPage.class);
	
	/**
	 * Perform-Methode der Klasse -
	 * wird ausgeführt wenn diese Aktion gestartet werden soll
	 * @param req der HTTP-Request
	 * @param resp die HTTP-Response
	 */	
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException
	{
		try 
		{
			// Session-Objekt erzeugen falls noch keine Session vorhanden war
			HttpSession session = req.getSession(false);
			
			if (session == null)
			{
				session = req.getSession();
			}

			
			if (req.getParameter("id") != null)
			{				
				session.setAttribute("mainID", req.getParameter("id"));
			
			} else
			{				
				session.setAttribute("mainID", 1);
			}
			
			forward(req, resp, "main.jsp");

		} catch (Exception e)
		{
			log.error("Fehler in ActionMainPage: \n\n" + e.getMessage());
		}
	}

}