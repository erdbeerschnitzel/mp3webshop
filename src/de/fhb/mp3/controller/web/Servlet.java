package de.fhb.mp3.controller.web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.servlet.ServletConfig;
import org.apache.log4j.Logger;
import de.fhb.mp3.controller.web.actions.ActionBuyItems;
import de.fhb.mp3.controller.web.actions.ActionChangeProfil;
import de.fhb.mp3.controller.web.actions.ActionLogin;
import de.fhb.mp3.controller.web.actions.ActionLogout;
import de.fhb.mp3.controller.web.actions.ActionMainPage;
import de.fhb.mp3.controller.web.actions.ActionRSS;
import de.fhb.mp3.controller.web.actions.ActionRegister;
import de.fhb.mp3.controller.web.actions.ActionShow;
import de.fhb.mp3.controller.web.actions.ActionShowMyArticles;
import de.fhb.mp3.controller.web.actions.ActionShowProfil;
import de.fhb.mp3.controller.web.actions.ActionShowWarenkorb;
import de.fhb.mp3.controller.web.actions.HttpRequestActionBase;
import de.fhb.mp3.da.*;
import de.fhb.mp3.vo.*;

/**
 * Controller
 * 
 * @author diesel
 * 
 */

public class Servlet extends HttpServletControllerBase implements javax.servlet.Servlet
{

	private static final long serialVersionUID = -2568616753374531369L;

	private static org.apache.log4j.Logger log = Logger.getLogger(Servlet.class);

	/**
	 * Default-Konstruktor
	 */
	public Servlet()
	{
		super();
	}

	/**
	 * Diese Methode wird bei einer GET-Operation des Client-Browser ausgeführt
	 * 
	 * @param request der HTTP-Request
	 * @param response die HTTP-Response
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

			HttpSession session = request.getSession(false);
			
			// Session-Objekt erzeugen falls noch keine Session vorhanden war
			if(session == null)
			{
				session = request.getSession();
			}
			
			/** synchronisierter session-zugriff */
			synchronized(session)
			{
				// wenn User nicht eingeloggt ist
				if(session.getAttribute("username") == null)
				{
					// Cookies holen
					Cookie[] cookies = request.getCookies();
					
					// falls Cookies existieren
					if (request.getCookies() != null)
					{
						for (int i = 0; i < cookies.length; i++)
						{
							// Usernamen-Cookie suchen
							if (cookies[i].getName().equals("username"))
							{
								LinkedList<UserVO> userlist = UserDAO.getInstance().findAll();
								
								for (int a = 0; a < userlist.size(); a++)
								{
									// Usernamen in DB-Liste suchen
									if (hash(userlist.get(a).getAccountname()).equals(cookies[i].getValue()))
									{
										// Usernamen in Session ablegen
										session.setAttribute("username", userlist.get(a).getAccountname());
										
										log.trace("cookie found for user: " + userlist.get(a).getAccountname());
										break;
									}
								}
							}
						}
					}
				}
			}

			HttpRequestActionBase action = (HttpRequestActionBase) actions.get(getOperation(request));

			// falls aktion = null
			if (action == null)
			{	
				// Standard-Aktion ausführen
				action = (HttpRequestActionBase) actions.get("main");
				
				
				log.warn(request.getRemoteHost() + " tried to access " + request.getRequestURL() + "?=" + request.getQueryString());
		
			}
	
			// Aktion ausführen
			action.perform(request, response);
			

	}

	/**
	 * Diese Methode wird bei einer POST-Operation des Client-Browser ausgeführt
	 * 
	 * @param request der HTTP-Request
	 * @param response die HTTP-Response
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	

	/**
	 * Initialisierungsmethode des Servlets
	 * 
	 * @param conf die Servlet-Konfiguration
	 */
	public void init(ServletConfig conf) throws ServletException
	{
		HttpRequestActionBase action = null;
		actions = new HashMap<String, HttpRequestActionBase>();

		// Aktionen erzeugen und zur Hashmap hinzufügen
		action = new ActionShowWarenkorb();
		actions.put("korb", action);
		action = new ActionBuyItems();
		actions.put("buy", action);
		action = new ActionShow();
		actions.put("show", action);
		action = new ActionRSS();
		actions.put("rss", action);
		action = new ActionMainPage();
		actions.put("main", action);
		action = new ActionShowProfil();
		actions.put("profil", action);
		action = new ActionLogout();
		actions.put("logout", action);
		action = new ActionLogin();
		actions.put("login", action);
		action = new ActionRegister();
		actions.put("register", action);
		action = new ActionChangeProfil();
		actions.put("profilchanges", action);
		action = new ActionShowMyArticles();
		actions.put("showmyarticles",action);
	}

	/**
	 * Diese Methode holt den Parameter "op" aus dem HTTP-Request
	 * @param req der HTTP-Request
	 * @return den Parameterwert
	 */
	protected String getOperation(HttpServletRequest req)
	{
		// do ist in diesem Fall der Steuerbefahl z.B. ?do=selectCD&cdId=1
		return req.getParameter("do");
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