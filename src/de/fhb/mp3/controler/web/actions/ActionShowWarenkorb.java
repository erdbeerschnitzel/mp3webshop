package de.fhb.mp3.controler.web.actions;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import de.fhb.mp3.bo.*;


/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn ein User den Warenkorb "betritt"
 * @author diesel
 *
 */
public class ActionShowWarenkorb extends HttpRequestActionBase 
{
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionShowWarenkorb.class);
	
	/**
	 * Perform-Methode der Klasse -
	 * wird ausgeführt wenn diese Aktion gestartet werden soll
	 * @param req der HTTP-Request
	 * @param resp die HTTP-Response
	 */
	@SuppressWarnings("unchecked")
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

				// Parameter zum Löschen von CDs und Songs aus dem Request holen
				String param = req.getParameter("removeid");
				String params = req.getParameter("removesid");
				
				ShoppingCart warenkorb = new ShoppingCart();
				
				// thread-safe
				synchronized(session)
				{
		
					if (param != null)
					{

						if (session.getAttribute("Warenkorb") != null)
						{
							
							
								// CD aus Warenkorb löschen
								warenkorb = new ShoppingCart((LinkedList<Long>)session.getAttribute("Warenkorb"), new LinkedList<Long>());
								
								warenkorb.removeCD(Long.parseLong(param));

						}
							
						session.setAttribute("Warenkorb", warenkorb.getCdslong());
					}
					
					// wenn Song-Parameter vorhanden
					if (params != null)
					{

						warenkorb = new ShoppingCart();
						
						// wenn Song-Warenkorb vorhanden
						if (session.getAttribute("SWarenkorb") != null)
						{							
								warenkorb = new ShoppingCart(new LinkedList<Long>(), (LinkedList<Long>)session.getAttribute("SWarenkorb"));
								warenkorb.removeSong(Long.parseLong(params));
								
						} 
						
						// aktualisierten Song-Warenkorb in Session speichern
						session.setAttribute("SWarenkorb", warenkorb.getSongslong());
					}
					
	
					
					// wenn CD-Warenkorb in Session
					if (session.getAttribute("Warenkorb") != null)
					{					
						ShoppingCart korb;
						
						if (session.getAttribute("SWarenkorb") != null)
						{					
							korb =  new ShoppingCart((LinkedList<Long>) session.getAttribute("Warenkorb"),(LinkedList<Long>) session.getAttribute("SWarenkorb"));
						
						} else
						{
							korb =  new ShoppingCart((LinkedList<Long>) session.getAttribute("Warenkorb"),new LinkedList<Long>());
	
						}
						
						session.setAttribute("PRICE" , korb.getPrice());
						session.setAttribute("wkcontent", korb.getCds());
						session.setAttribute("wkscontent", korb.getSongs());
					
					} else
					{ 					
						if (session.getAttribute("SWarenkorb") != null)
						{						
							ShoppingCart korb;
							korb =  new ShoppingCart(new LinkedList<Long>(),(LinkedList<Long>) session.getAttribute("SWarenkorb"));
							
							
							session.setAttribute("PRICE" , korb.getPrice());
							session.setAttribute("wkcontent", korb.getCds());
							session.setAttribute("wkscontent", korb.getSongs());
						}
					}
					
					
					
					if (session.getAttribute("username") != null)
					{					
						session.setAttribute("notloggedin", "no");
						
					} else 
					{					
						session.setAttribute("notloggedin", "yes");
					}							
					
					
				session.setAttribute("mainID", 11);
				
			}
				forward(req, resp, "main.jsp");
	
				
			
			} catch (Exception e) 
			{
				log.error("Fehler in ActionShowWarenkorb: \n\n" + e.getMessage());
			}
	}
	

}
