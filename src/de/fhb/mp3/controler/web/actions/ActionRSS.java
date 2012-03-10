package de.fhb.mp3.controler.web.actions;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

import de.fhb.mp3.da.*;
import de.fhb.mp3.vo.*;

/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn ein User den RSS aufruft
 * @author diesel
 *
 */
public class ActionRSS extends HttpRequestActionBase
{

	private static org.apache.log4j.Logger log = Logger.getLogger(ActionRSS.class);
	
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
				
				String parameter = req.getParameter("id");
				
				// wenn Parameter vorhanden
				if(parameter != null)
				{
					// wenn Parameter der 1 entspricht
					if(Integer.parseInt(parameter) == 1)
					{
						// CD-Liste aus DB holen
						LinkedList<CD_VO> list = CDDAO.getInstance().findAll();

						// neue CDs in Liste speichern
						for (int x = 0; x < list.size(); x++)
						{							
							if (list.get(x).getVeroeffentlichung() != 2008)
							{								
								list.remove(x);
								x = x -1;
							}
						}

						synchronized(session)
						{
							// CD-Liste in Session ablegen
							session.setAttribute("list", list);
						}
						
						// weiterleiten an 1. RSS-Seite
						forward(req, resp, "rss1.jsp");
					
					} else
					{
						// weiterleiten an 2. RSS-Seite
						forward(req, resp, "rss2.jsp");
					}
				} else
				{
					// weiterleiten zur RSS-Auswahl
					forward(req, resp, "rss.jsp");
		
				}

				
			} catch (Exception e)
			{
				log.error("Fehler in ActionRSS: \n\n" + e.getMessage());
			}
	}
}
