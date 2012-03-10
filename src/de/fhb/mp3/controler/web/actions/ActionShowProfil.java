package de.fhb.mp3.controler.web.actions;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

import de.fhb.mp3.da.*;
import de.fhb.mp3.vo.*;

/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn ein User sein Profil betrachtet
 * @author diesel
 *
 */
public class ActionShowProfil extends HttpRequestActionBase 
{
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionShowProfil.class);
	
	/**
	 * Perform-Methode der Klasse -
	 * wird ausgeführt wenn diese Aktion gestartet werden soll
	 * @param req der HTTP-Request
	 * @param resp die HTTP-Response
	 */
	public void perform(HttpServletRequest req, HttpServletResponse resp)throws ServletException
	{

			try
			{
				
				// Session-Objekt erzeugen falls noch keine Session vorhanden war
				HttpSession session = req.getSession(false);
				
				if (session == null)
				{
					session = req.getSession();
				}
				
				synchronized(session)
				{
					
					// wenn Username in Session
				    if (session.getAttribute("username") != null)
				    {
				    	// Userdaten in Request packen
				    	UserVO user = UserDAO.getInstance().findAccountByAccountName(session.getAttribute("username").toString());
						
				    	req.setAttribute("userdaten", user);
				    	
				    	// Anzahl gekaufter Artikel in Session packen
						session.setAttribute("timedout", "no");
						session.setAttribute("complete", user.getCds().size() + user.getSongs().size());
						session.setAttribute("cdsbought", user.getCds().size());
						session.setAttribute("songsbought", user.getSongs().size());
						
					
				    } else 
				    {	
				    	// Timeout-Attribut setzen
				    	session.setAttribute("timedout", "yes");
				    }
				
					session.setAttribute("mainID", 12);
				}
				
				// weiterleiten
				forward(req, resp, "main.jsp");

				
			} catch (Exception e)
			{

				log.error(("Fehler in ActionShowProfil: \n\n" + e.getMessage()));
			}
	}
	

}
