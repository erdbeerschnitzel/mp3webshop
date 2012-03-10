package de.fhb.mp3.controler.web.actions;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import de.fhb.mp3.bo.*;
import de.fhb.mp3.da.*;
import de.fhb.mp3.vo.*;


/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn sich ein User Änderungen an seinen Profildaten vornehmen will
 * @author diesel
 *
 */

public class ActionBuyItems extends HttpRequestActionBase
{
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionBuyItems.class);

	/**
	 * Perform-Methode der Klasse -
	 * wird ausgeführt wenn diese Aktion gestartet werden soll
	 * @param req der HTTP-Request
	 * @param resp die HTTP-Response
	 */	
	@SuppressWarnings("unchecked")
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException
	{

		boolean bankeinzug = false;

		try
		{
			// Session-Objekt erzeugen falls noch keine Session vorhanden war
			HttpSession session = req.getSession(false);

			if (session == null)
			{
				session = req.getSession();
			}

			
			LinkedList<Long> warenkorb = new LinkedList<Long>();


			if (session.getAttribute("username") != null)
			{
				
				log.trace("user " + session.getAttribute("username").toString() + " tries to buy items...");
				UserVO tempuser = UserDAO.getInstance().findAccountByAccountName(session.getAttribute("username").toString());

				if (req.getParameter("save") != null)
				{					
					String bankzeug = "";
					
					if (req.getParameter("Zahlmethode") != null)
					{						
						tempuser.setZahlungsart(req.getParameter("Zahlmethode"));

					}
					
					if (req.getParameter("inhaber") != null)
					{
						bankzeug = req.getParameter("inhaber") + ";";
						session.setAttribute("savebank", "yes");
					}

					if (req.getParameter("kontonr") != null)
					{
						bankzeug = bankzeug + req.getParameter("kontonr") + ";";
						session.setAttribute("savebank", "yes");
					}

					if (req.getParameter("blz") != null)
					{
						bankzeug = bankzeug + req.getParameter("blz") + ";";
						session.setAttribute("savebank", "yes");
					}

					if (req.getParameter("bank") != null)
					{
						bankzeug = bankzeug + req.getParameter("bank") + ";";
						session.setAttribute("savebank", "yes");
					}
					
					
					// Zahlungsdaten aktualisieren
					tempuser.setZahlungsdaten(bankzeug);
					// DB updaten
					UserDAO.getInstance().update(tempuser);
					log.trace("updated bankdata for user: " + tempuser.getAccountname() + "(" + tempuser.getName() + " " + tempuser.getLastname() +")");
					
					session.setAttribute("zahlung", tempuser.getZahlungsart());
					session.setAttribute("bankdaten", tempuser.getSplittedBankData());
					session.setAttribute("mainID", 18);	
					
					forward(req, resp, "main.jsp");
					
					
					
				} else
				{				
				
					if (tempuser.getZahlungsart().equals("Bankeinzug"))
					{
						
						log.trace("ActionBuyItems -> User: " + tempuser.getAccountname() + "(" + tempuser.getName() + " " + tempuser.getLastname() + ") -> Zahlungsart: Bankeinzug");

						if (tempuser.getZahlungsdaten().equals(";;;;")) {
	
							bankeinzug = true;
						}
	
						if (tempuser.getSplittedBankData()[0].length() < 5) {
	
							bankeinzug = true;
						}
	
						if (tempuser.getSplittedBankData()[1].length() < 5) {
	
							bankeinzug = true;
						}
	
						if (tempuser.getSplittedBankData()[2].length() < 8) {
	
							bankeinzug = true;
						}
	
						if (tempuser.getSplittedBankData()[3].length() < 5) {
	
							bankeinzug = true;
						}
	
						session.setAttribute("savebank", "no");
						
						if (bankeinzug)
						{
							log.warn("user: " + tempuser.getAccountname() + " -> Bankeinzug -> Daten unvollständig");
							session.setAttribute("mainID", 18);
							session.setAttribute("bankdaten", tempuser.getSplittedBankData());
							session.setAttribute("zahlung", tempuser.getZahlungsart());
							forward(req, resp, "main.jsp");
							
						} else
						{
						
							log.trace("user: " + tempuser.getAccountname() + " -> Bankeinzug -> Daten vollständig");
							
							ShoppingCart korb = new ShoppingCart();
							LinkedList<Long> cds = tempuser.getCds();
							LinkedList<Long> songs = tempuser.getSongs();
			
							if (session.getAttribute("Warenkorb") != null)
							{			
								if (session.getAttribute("SWarenkorb") != null)
								{									
									korb = new ShoppingCart((LinkedList<Long>) session.getAttribute("Warenkorb"),(LinkedList<Long>) session.getAttribute("SWarenkorb"));
					
								} else
								{									
									korb = new ShoppingCart((LinkedList<Long>) session.getAttribute("Warenkorb"),new LinkedList<Long>());
									
								}
								
								cds.addAll(korb.getCdslong());
								songs.addAll(korb.getSongslong());
								session.setAttribute("gekauftesongs", korb.getSongs());
								session.setAttribute("gekauftecds", korb.getCds());
			
							} else
							{	
								if (session.getAttribute("SWarenkorb") != null)
								{			
									korb = new ShoppingCart(new LinkedList<Long>(),(LinkedList<Long>) session.getAttribute("SWarenkorb"));
									songs.addAll(korb.getSongslong());
								}
								
							}

							// gekaufte CDs und Songs updaten
							tempuser.setCds(cds);
							tempuser.setSongs(songs);
							// DB updaten
							UserDAO.getInstance().update(tempuser);
							
							String items = "";
							
							for (int a = 0; a < korb.getCdslong().size(); a++)
							{
							  items = items + korb.getCdslong().get(a) + ";";
							}
							
							log.info("user: " + tempuser.getAccountname() + "(" + tempuser.getName() + " " + tempuser.getLastname() + ") kauft CDs: " + items 
									+ " -> Preis: " + korb.getPricecds() + "  Zahlungsart: "  + tempuser.getZahlungsart());
							
							items = "";
							
							for (int a = 0; a < korb.getSongslong().size(); a++)
							{
							  items = items + korb.getSongslong().get(a) + ";";
							}							
							
							log.info("user: " + tempuser.getAccountname() + "(" + tempuser.getName() + " " + tempuser.getLastname() + ") kauft Songs: " + items 
									+ " -> Preis " + korb.getPricesongs() + "  Zahlungsart: "  + tempuser.getZahlungsart());
	

							warenkorb.clear();
							session.setAttribute("zahlung", tempuser.getZahlungsart());
							session.setAttribute("Warenkorb", new LinkedList<Long>());
							session.setAttribute("SWarenkorb", new LinkedList<Long>());

							session.setAttribute("mainID", 19);
							forward(req, resp, "main.jsp");

					}
					
				} else
				{
					
					// Rechnung
					
					log.warn("user: " + tempuser.getAccountname() + " -> Zahlungsart: Rechnung");
					
					ShoppingCart korb;
					LinkedList<Long> cds = tempuser.getCds();
					LinkedList<Long> songs = tempuser.getSongs();
					

					if (session.getAttribute("Warenkorb") != null)
					{
	
						if (session.getAttribute("SWarenkorb") != null)
						{							
							korb = new ShoppingCart((LinkedList<Long>) session.getAttribute("Warenkorb"),(LinkedList<Long>) session.getAttribute("SWarenkorb"));

						} else
						{							
							korb = new ShoppingCart((LinkedList<Long>) session.getAttribute("Warenkorb"),new LinkedList<Long>());

						}

						
						cds.addAll(korb.getCdslong());
						songs.addAll(korb.getSongslong());
						session.setAttribute("gekauftesongs", korb.getSongs());
						session.setAttribute("gekauftecds", korb.getCds());
	
					} else
					{

						if (session.getAttribute("SWarenkorb") != null)
						{	
							korb = new ShoppingCart(new LinkedList<Long>(),(LinkedList<Long>) session.getAttribute("SWarenkorb"));
							songs.addAll(korb.getSongslong());
						}
						
					}

					tempuser.setCds(cds);
					tempuser.setSongs(songs);

					UserDAO.getInstance().update(tempuser);

					String items = "";
					
					for (int a = 0; a < cds.size(); a++)
					{
					  items = items + cds.get(a) + ";";
					}
					
					log.info("user: " + tempuser.getAccountname() + "(" + tempuser.getName() + " " + tempuser.getLastname() + ") bought CDs: " + items);
					
					items = "";
					
					for (int a = 0; a < songs.size(); a++)
					{
					  items = items + songs.get(a) + ";";
					}							
					
					log.info("user: " + tempuser.getAccountname() + "(" + tempuser.getName() + " " + tempuser.getLastname() + ") bought Songs: " + items);

					
					warenkorb.clear();
					session.setAttribute("zahlung", tempuser.getZahlungsart());
					session.setAttribute("Warenkorb", new LinkedList<Long>());
					session.setAttribute("SWarenkorb", new LinkedList<Long>());

					session.setAttribute("mainID", 19);
					forward(req, resp, "main.jsp");

				}
			}
				
			} else
			{

				// nicht eingeloggt
				forward(req, resp, "main.jsp");
			}

			

		} catch (Exception e)
		{
			log.error("Fehler in ActionBuyItems: \n\n" + e.getMessage());
		}
	}
}
