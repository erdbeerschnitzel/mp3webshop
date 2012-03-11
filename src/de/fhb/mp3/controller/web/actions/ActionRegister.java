package de.fhb.mp3.controller.web.actions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

import de.fhb.mp3.da.UserDAO;
import de.fhb.mp3.vo.UserVO;

/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn ein User die Registrierungseite aufruft
 * @author diesel
 *
 */
public class ActionRegister extends HttpRequestActionBase
{
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionRegister.class);
	
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

			boolean all = true;
			
			// Session-Objekt erzeugen falls noch keine Session vorhanden war
			HttpSession session = req.getSession(false);

			if (session == null)
			{
				session = req.getSession();
			}
			
			synchronized(session)
			{
				
				session.setAttribute("userexists", 0);
				session.setAttribute("passwordequal", "");
				session.setAttribute("desiredusername", "");
				session.setAttribute("stadt", "");
				session.setAttribute("plz", "");
				session.setAttribute("vorname", "");
				session.setAttribute("nachname", "");
				session.setAttribute("strasse", "");
				session.setAttribute("hausnummer", "");
				session.setAttribute("Zahlmethode", "");
				session.setAttribute("email", "");
				session.setAttribute("all", "");
	
				if (req.getParameter("doreg") != null)
				{
	
					if (req.getParameter("desiredusername") == "")
					{
						all = false;
						
					} else
					{	
						session.setAttribute("desiredusername", req.getParameter("desiredusername"));
					}
	
					if (req.getParameter("passwort") == "" || req.getParameter("passwort") == null)
					{	
						all = false;
	
					} else
					{	
						if (req.getParameter("passwortagain").equals(req.getParameter("passwort")) == false)
						{	
							all = false;
							session.setAttribute("passwordequal", "no");
							// ~>
						}
	
					}
	
					if (req.getParameter("nachname") == "")
					{
						all = false;
						
					} else
					{
						session.setAttribute("nachname", req.getParameter("nachname"));
					}
					
					if (req.getParameter("vorname") == "")
					{
						all = false;
						
					} else
					{
						session.setAttribute("vorname", req.getParameter("vorname"));
					}
					
					if (req.getParameter("plz") == "")
					{
						all = false;
						
					} else
					{
						session.setAttribute("plz", req.getParameter("plz"));
					}
					
					if (req.getParameter("stadt") == "")
					{
						all = false;
						
					} else
					{
						session.setAttribute("stadt", req.getParameter("stadt"));
					}
					
					if (req.getParameter("strasse") == "")
					{
						all = false;
						
					} else
					{
						session.setAttribute("strasse", req.getParameter("strasse"));
					}
					
					if (req.getParameter("hausnummer") == "")
					{
						all = false;
						
					} else
					{
						session.setAttribute("hausnummer", req.getParameter("hausnummer"));
					}
					
					if (req.getParameter("email") == "")
					{
						all = false;
						session.setAttribute("emailinvalid", "yes");
						
					} else
					{	
						session.setAttribute("email", req.getParameter("email"));
						
						if(req.getParameter("email").contains("@") == false)
						{							
							session.setAttribute("emailinvalid", "yes");
							all = false;
							
						} else
						{
							session.setAttribute("emailinvalid", "no");
						}
					}
					
					if (req.getParameter("Land") == "")
					{
						all = false;
						
					} else
					{
						session.setAttribute("Land", req.getParameter("Land"));
					}
					
					if (req.getParameter("agbaccept") != null)
					{						
						if (req.getParameter("agbaccept").equals("on") == false)
						{
							all = false;
						}
						
					} else {
						all = false;
					}
	
					if (req.getParameter("Zahlmethode") != null)
					{
						session.setAttribute("zahlung", req.getParameter("Zahlmethode"));
					}
					
					session.setAttribute("all", all);
	
					if (all)
					{

						if (UserDAO.getInstance().findAccountByAccountName(req.getParameter("desiredusername")) == null)
						{
							UserVO newUser = new UserVO();
							newUser.setAccountname(req.getParameter("desiredusername"));
							newUser.setName(new String(req.getParameter("vorname").getBytes("ISO-8859-1"), "UTF-8"));
							newUser.setLastname(req.getParameter("nachname"));
							newUser.setPassword(hash(req.getParameter("passwort")));
							newUser.setLand(new String(req.getParameter("Land").getBytes("ISO-8859-1"),"UTF-8"));
							newUser.setStadt(new String(req.getParameter("stadt").getBytes("ISO-8859-1"),"UTF-8"));
							newUser.setPlz(new String(req.getParameter("plz").getBytes("ISO-8859-1"),"UTF-8"));
							newUser.setStrasse(new String(req.getParameter("strasse").getBytes("ISO-8859-1"),"UTF-8"));
							newUser.setHausnummer(new String(req.getParameter("hausnummer").getBytes("ISO-8859-1"),"UTF-8"));
							newUser.setZahlungsart(req.getParameter("Zahlmethode"));
							newUser.setZahlungsdaten("");
							newUser.setEmail(req.getParameter("email"));
	
							UserDAO.getInstance().create(newUser);
	
							log.info("user registered: " + newUser.getAccountname() + "(" + req.getRemoteHost() + ")");
							session.setAttribute("user", newUser);
							session.setAttribute("userexists", "0");
							
							synchronized(session)
							{
								
								session.setAttribute("username", newUser.getAccountname());
							}
	
						} else
						{
							
							log.trace("Register error -> user exists: " + req.getParameter("desiredusername"));
	
							session.setAttribute("userexists", req.getParameter("desiredusername"));
							all = false;
						}
	
					}
	
				} else
				{					
					all = false;
					session.setAttribute("all", "true");
				}
	
				// weiterleiten zur Registrierseite
				session.setAttribute("mainID", 8);
				
				// bzw. zur Registriertseite
				if (all) session.setAttribute("mainID", 10);
				

				if (req.getParameter("Land") != null)
				{	
					req.setAttribute("LandF", new String(req.getParameter("Land").getBytes("ISO-8859-1"),"UTF-8"));

				} else
				{					
					req.setAttribute("LandF", "default");
				}
				
			}
			
			forward(req, resp, "main.jsp");
			
		} catch (Exception e)
		{
			log.error("Fehler in ActionRegister: \n\n" + e.getMessage());
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
