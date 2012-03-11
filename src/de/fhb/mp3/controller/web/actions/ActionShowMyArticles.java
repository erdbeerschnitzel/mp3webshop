package de.fhb.mp3.controller.web.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.apache.log4j.Logger;

import de.fhb.mp3.da.*;
import de.fhb.mp3.vo.*;

/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn ein User seine erworbenen Artikel betrachtet
 * @author diesel
 *
 */
public class ActionShowMyArticles extends HttpRequestActionBase
{
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionShowMyArticles.class);
	
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
		
			// thread-safe
			synchronized(session)
			{
				
				// falls Attribut in Session dann löschen
				if (session.getAttribute("kauferfolgreich") != null) session.removeAttribute("kauferfolgreich");
			

				// wenn User eingeloggt ist
				if (session.getAttribute("username") != null)
				{
					
					// User-Daten aus DB holen
					UserVO tempuser = UserDAO.getInstance().findAccountByAccountName(session.getAttribute("username").toString());
					
					// CDs und Songs in Listen packen
					LinkedList<CD_VO> cds = new LinkedList<CD_VO>();
					LinkedList<SongVO> songs = new LinkedList<SongVO>();
					
					for (int x = 0; x < tempuser.getCds().size(); x++)
					{						
						cds.add((CD_VO)CDDAO.getInstance().findByEAN(tempuser.getCds().get(x)));
					}
					
					for (int x = 0; x < tempuser.getSongs().size(); x++)
					{						
						songs.add((SongVO)SongDAO.getInstance().findById(tempuser.getSongs().get(x)));
					}
					
					// CD und Song-Listen in Session speichern
					session.setAttribute("usercds", cds);
					session.setAttribute("usersongs", songs);
	
				}
	
				session.setAttribute("mainID", 17);
				forward(req, resp, "main.jsp");

			}
		} catch (Exception e) 
		{
			log.error("Fehler in ActionShowMyArticles: \n\n" + e.getMessage());
		}
	}
}
