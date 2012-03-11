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
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn sich ein User einloggt
 * @author diesel
 *
 */
public class ActionLogin extends HttpRequestActionBase {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionLogin.class);
	
	
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

			HttpSession session = req.getSession(false);

			// Session-Objekt erzeugen falls noch keine Session vorhanden war 
			if (session == null)
			{
				session = req.getSession();
			}

			// wenn Username im Request übergeben wurde 
			if (req.getParameter("username") != null)
			{

				// entsprechende Userdaten aus der DB holen 
				UserVO user = UserDAO.getInstance().findAccountByAccountName(req.getParameter("username"));
				
				// falls User in der DB gefunden wurde 
				if (user != null)
				{
					

					
					//falls Passwort stimmt 
					if (hash(req.getParameter("passwort")).equals(user.getPassword()))
					{	
						
						//MD5-Summe vom Usernamen erzeugen 
						String cookieValue = hash(req.getParameter("username"));
						
						//Cookie mit "gehashtem" Usernamen erzeugen und zum Response-Objekt hinzufügen
						Cookie cook = new Cookie("username", cookieValue);
						cook.setMaxAge(300);
						resp.addCookie(cook);
						
						log.debug("user " + user.getAccountname() + " logged in");
						log.debug("set cookie for user: " + user.getAccountname());
						synchronized(session)
						{					
							session.setAttribute("username", user.getAccountname());
						}
					
					} else
					{						
						log.warn("user " + user.getAccountname() + " tried to log in with wrong passwort");
						
					}
				
				} else
				{
					log.warn("non-existent user " + req.getParameter("username") + " tried to log in");
				}
				

			}

			//weiterleiten zur News-Seite falls vorher keine andere Seite in der Session festgelegt wurde 
			synchronized(session)
			{
				if (session.getAttribute("mainID") == null)	session.setAttribute("mainID", 1);
			
				if (session.getAttribute("username") == null || session.getAttribute("username").toString().equals("anonym") ||
						session.getAttribute("username").toString().equals("") || session.getAttribute("username").toString().equals(" "))
						{
							//weiter zur Login-Fehler-Seite
							session.setAttribute("mainID", 3);
						}
			
			}
			
			
			
			forward(req, resp, "main.jsp");

		} catch (Exception e)
		{
			log.error("Fehler in ActionLogin: \n\n" + e.getMessage());
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
