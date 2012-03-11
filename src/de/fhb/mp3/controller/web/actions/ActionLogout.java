package de.fhb.mp3.controller.web.actions;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn sich ein User ausloggt
 * @author diesel
 *
 */
public class ActionLogout extends HttpRequestActionBase
{
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionLogout.class);
	
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

			//hier alles aus der Session löschen
			synchronized(session)
			{
				resp.addCookie(new Cookie("username" , ""));
				
				if (session.getAttribute("username") != null)
				{
					log.trace("removed cookie for user: " + session.getAttribute("username").toString());

				}
				
				
				session.removeAttribute("username");
				session.removeAttribute("cdliste");
				
				//weiterleiten zur Hauptseite
				session.setAttribute("mainID", 1);
				
			}
			
			forward(req, resp, "main.jsp");

		} catch (Exception e)
		{
			log.error("Fehler in ActionLogout: \n\n" + e.getMessage());

		}
	}
}
