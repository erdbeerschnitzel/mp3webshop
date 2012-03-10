package de.fhb.mp3.controler.web.actions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import de.fhb.mp3.da.*;
import de.fhb.mp3.vo.*;


/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn sich ein User Änderungen an seinen Profildaten vornehmen will
 * @author diesel
 *
 */
public class ActionChangeProfil extends HttpRequestActionBase
{
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionChangeProfil.class);
	
	
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
			
			
			// eventuelle Änderungen speichern
			if (req.getParameter("save") != null)
			{				
				if (req.getParameter("save").equals("true"))
				{					
					if (session.getAttribute("username") != null)
					{						
						UserVO tempuser = UserDAO.getInstance().findAccountByAccountName(session.getAttribute("username").toString());
						
						
						if(tempuser.getLastname() !=  new String(req.getParameter("nachname").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setLastname(new String(req.getParameter("nachname").getBytes("ISO-8859-1"),"UTF-8"));
						}
						
						if(tempuser.getName() !=  new String(req.getParameter("vorname").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setName(new String(req.getParameter("vorname").getBytes("ISO-8859-1"),"UTF-8"));
						}
						
						if(tempuser.getStrasse() !=  new String(req.getParameter("strasse").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setStrasse(new String(req.getParameter("strasse").getBytes("ISO-8859-1"),"UTF-8"));
						}						
		
						if(tempuser.getPlz() !=  new String(req.getParameter("plz").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setPlz(new String(req.getParameter("plz").getBytes("ISO-8859-1"),"UTF-8"));
						}	
						
						if(tempuser.getEmail() !=  new String(req.getParameter("email").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setEmail(new String(req.getParameter("email").getBytes("ISO-8859-1"),"UTF-8"));
						}	
						
						if(tempuser.getStadt() !=  new String(req.getParameter("stadt").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setStadt(new String(req.getParameter("stadt").getBytes("ISO-8859-1"),"UTF-8"));
						}						
						
						
						if(tempuser.getHausnummer() !=  new String(req.getParameter("hausnummer").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setHausnummer(new String(req.getParameter("hausnummer").getBytes("ISO-8859-1"),"UTF-8"));
						}							
						
						if(tempuser.getLand() !=  new String(req.getParameter("Land").getBytes("ISO-8859-1"),"UTF-8"))
						{							
							tempuser.setLand(new String(req.getParameter("Land").getBytes("ISO-8859-1"),"UTF-8"));
						}							
						
						if (req.getParameter("passwort").equals(req.getParameter("passwortagain")) && req.getParameter("passwort") != "" & req.getParameter("passwort") != " ")
						{							
							if(tempuser.getPassword() !=  hash(new String(req.getParameter("passwort").getBytes("ISO-8859-1"),"UTF-8")))
							{								
								tempuser.setPassword(hash(new String(req.getParameter("passwort").getBytes("ISO-8859-1"),"UTF-8")));
							}	
							
						}
						
						UserDAO.getInstance().update(tempuser);
					}
					
				}
			}
			
			if (req.getParameter("savebank") != null)
			{
				if (req.getParameter("savebank").equals("true"))
				{					
					if (session.getAttribute("username") != null)
					{						
						UserVO tempuser = UserDAO.getInstance().findAccountByAccountName(session.getAttribute("username").toString());
						
						if(req.getParameter("Zahlmethode").equals(tempuser.getZahlungsart()) == false)
						{							
							tempuser.setZahlungsart(req.getParameter("Zahlmethode"));
						}
					
						if(req.getParameter("Zahlmethode").equals("Bankeinzug"))
						{							
							String data = "";
							
							if (req.getParameter("inhaber") != null)
							{								
								data = req.getParameter("inhaber") + ";";
							
							} else
							{								
								data = ";";
							}
							
							if (req.getParameter("kontonr") != null)
							{								
								data = data + req.getParameter("kontonr") + ";";
							
							} else
							{								
								data = data + ";";
							}
							
							if (req.getParameter("blz") != null)
							{								
								data = data + req.getParameter("blz") + ";";
							
							} else
							{								
								data = data + ";";
							}
							
							if (req.getParameter("bank") != null)
							{								
								data = data + req.getParameter("bank") + ";";
							
							} else
							{								
								data = data + ";";
							}
							
							tempuser.setZahlungsdaten(data);
						}
						
						UserDAO.getInstance().update(tempuser);
					}
				}
			}


			// weiterleiten zur Hauptseite
			synchronized(session)
			{				
			
				session.setAttribute("mainID", 16);
				
				
				if (session.getAttribute("username") != null)
				{					
					UserVO tempuser = UserDAO.getInstance().findAccountByAccountName(session.getAttribute("username").toString());
					
					if (tempuser.getLand().equals("Deutschland") == false && tempuser.getLand().equals("Schweiz") == false && tempuser.getLand().equals("Österreich") == false)
					{						
						tempuser.setLand("Deutschland");
					}
					
					String[] bankdata = tempuser.getSplittedBankData();
					
					req.setAttribute("bankdaten", bankdata);
					
					req.setAttribute("userdaten", tempuser);
					session.setAttribute("timedout", "no");
					
					
					
					if (tempuser.getZahlungsart().equals("") == false)
					{						
						session.setAttribute("zahlung", tempuser.getZahlungsart());		
					
					} else
					{						
						session.setAttribute("zahlung", "Rechnung");
					}
					
					
				} else
				{					
					session.setAttribute("timedout", "yes");
				}
	
				if (req.getParameter("save") != null)
				{
					if (req.getParameter("save").equals("true"))
					{						
						session.setAttribute("saved", "yes");
					}
					
				} else
				{					
					session.setAttribute("saved", "no");
				}
	
				if (req.getParameter("savebank") != null)
				{					
					if (req.getParameter("savebank").equals("true"))
					{
						session.setAttribute("savedbank", "yes");
					}
					
				} else
				{					
					session.setAttribute("savedbank", "no");
				}
	
				
				//Request weiterleiten
				forward(req, resp, "main.jsp");
			
			}
		
		} catch (Exception e)
		{
			log.error("Fehler in ActionChangeProfil: \n\n" + e.getMessage());
		}
	}
	
	/**
	 * Diese Methode erzeugt die MD5-Summe des übergebenen Strings
	 * 
	 * @param hidethis String der "gehasht" werden soll
	 * @return der "gehashte" String
	 */
	public String hash(String hidethis)
	{

		String result = new String();

		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(hidethis.getBytes());
			byte[] digest = m.digest();

			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < digest.length; i++)
			{
				hexString.append(Integer.toHexString(0xFF & digest[i]));

			}
			result = hexString.toString();

		} catch (NoSuchAlgorithmException e)
		{
			log.error("Error in hash-method: \n\n" + e.getMessage());
		}

		return result;
	}
	
}
