package de.fhb.mp3.controller.web.actions;

import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import de.fhb.mp3.da.*;
import de.fhb.mp3.vo.*;

/**
 * Abgeleitet von HttpRequestActionBase - 
 * Die Perform-Methode dieser Klasse wird vom Servlet ausgeführt wenn ein User im MP3-Shop surft
 * @author diesel
 *
 */
public class ActionShow extends HttpRequestActionBase
{
	private static org.apache.log4j.Logger log = Logger.getLogger(ActionShow.class);
	
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


			synchronized(session)
			{
			
//###########################################
//################# Kauf ####################
//###########################################				
				
				
				if (session.getAttribute("cdliste") != null)
				{
					session.removeAttribute("cdliste");
				}
				
				if (session.getAttribute("songliste") != null)
				{
					session.removeAttribute("songliste");
				}					
				
				LinkedList<Long> warenkorb = new LinkedList<Long>();
				LinkedList<Long> warenkorbsongs = new LinkedList<Long>();
				
				if (req.getParameter("buysid") != null)
				{

					if (session.getAttribute("SWarenkorb") != null)
					{						
						warenkorbsongs = (LinkedList<Long>)session.getAttribute("SWarenkorb");
						
						if (session.getAttribute("Warenkorb") != null)
						{
							
								warenkorb = (LinkedList<Long>)session.getAttribute("Warenkorb");
								boolean found = false;
								
								for (int z = 0; z < warenkorb.size(); z++)
								{
									
									if (CDDAO.getInstance().findByEAN(warenkorb.get(z)).getEan() == SongDAO.getInstance().findById(Long.parseLong(req.getParameter("buysid"))).getCdean())
									{
										
										found = true;
										break;
									}
								}
								
								if(!found)
								{
									
									warenkorbsongs.add(Long.parseLong(req.getParameter("buysid")));
								}
							
							
							
						} else
						{
							
								warenkorbsongs.add(Long.parseLong(req.getParameter("buysid")));
	
						}
	
					} else
					{
	
						warenkorbsongs.add(Long.parseLong(req.getParameter("buysid")));
						
					}
					
					session.setAttribute("CDID", req.getParameter("cdid"));
					session.setAttribute("SWarenkorb", warenkorbsongs);
					CD_VO tempcd = CDDAO.getInstance().findByEAN(Long.parseLong(req.getParameter("cdid")));
					session.setAttribute("CD", tempcd);
					session.setAttribute("linkartist", tempcd.getInterpret().replaceAll(" ", "%20"));
					session.setAttribute("DETAIL", 1);
					session.setAttribute("shownew", 0);
					session.setAttribute("showall", 0);
					session.setAttribute("showgenre", 0);
					session.setAttribute("SEARCH", 0);
					session.setAttribute("SEARCH2", 0);					
					
				} else
				{
				
					if (req.getParameter("buyid") != null)
					{
						if (session.getAttribute("Warenkorb") != null)
						{
							warenkorb = (LinkedList<Long>)session.getAttribute("Warenkorb");
							warenkorb.add(Long.parseLong(req.getParameter("buyid")));
							
							if (session.getAttribute("SWarenkorb") != null)
							{								
								warenkorbsongs = (LinkedList<Long>)session.getAttribute("SWarenkorb");
	
								for (int y = 0; y < warenkorbsongs.size(); y++)
								{
									
									if (SongDAO.getInstance().findById(warenkorbsongs.get(y)).getCdean() == Long.parseLong(req.getParameter("buyid"))){
										
										warenkorbsongs.remove(y);
										y = y -1;
									}
								}
								
								
								session.setAttribute("SWarenkorb", warenkorbsongs);
							}
							
						} else 
						{
							
							warenkorb.add(Long.parseLong(req.getParameter("buyid")));
							
							if (session.getAttribute("SWarenkorb") != null)
							{								
								warenkorbsongs = (LinkedList<Long>)session.getAttribute("SWarenkorb");
								
								
								for (int y = 0; y < warenkorbsongs.size(); y++)
								{
									
									if (SongDAO.getInstance().findById(warenkorbsongs.get(y)).getCdean() == Long.parseLong(req.getParameter("buyid")))
									{										
										warenkorbsongs.remove(y);
										y = y -1;
									}
								}
			
								session.setAttribute("SWarenkorb", warenkorbsongs);
							}
							
						}

						log.trace("user added CD to shoppingcart: " + req.getParameter("buyid"));
						
						session.setAttribute("Warenkorb", warenkorb);
						session.setAttribute("CDID", req.getParameter("buyid"));
						CD_VO tempcd = CDDAO.getInstance().findByEAN(Long.parseLong(req.getParameter("buyid")));
						session.setAttribute("CD", tempcd);
						session.setAttribute("linkartist", tempcd.getInterpret().replaceAll(" ", "%20"));
						session.setAttribute("DETAIL", 1);
						session.setAttribute("shownew", 0);
						session.setAttribute("showall", 0);

						session.setAttribute("showgenre", 0);
						session.setAttribute("SEARCH", 0);
						session.setAttribute("SEARCH2", 0);
					} 	
					
				}
				
//###########################################
//################# Suche ####################
//###########################################		
	
				LinkedList<CD_VO> liste = new LinkedList<CD_VO>();
				LinkedList<SongVO> songliste = new LinkedList<SongVO>();
				
				String search = null;
				String option = null;
		
				session.setAttribute("nothing", "no");
				
				if (req.getParameter("suchbegriff") != null)
				{
	
					session.setAttribute("SEARCH2", 1);
					session.setAttribute("SEARCH", 0);
					session.setAttribute("searchedthat", new String(req.getParameter("suchbegriff").getBytes("ISO-8859-1"),"UTF-8"));
					search = req.getParameter("suchbegriff");
					option = req.getParameter("suchoption");
					
					
					log.trace("user searched for: "  + req.getParameter("suchbegriff") + "  --> suchoption: " + req.getParameter("suchoption"));
					
				
				} else
				{
					
					session.setAttribute("SEARCH2", 0);
					
					if (req.getParameter("search") != null)
					{
						log.trace("user searched artist: " + req.getParameter("search"));
						
						session.setAttribute("SEARCH", 1);
						session.setAttribute("searched", new String(req.getParameter("search").getBytes("ISO-8859-1"),"UTF-8"));
						search = req.getParameter("search");
						option = "";
						
					} else
					{					
						session.setAttribute("SEARCH", 0);
						search = null;
					}
		
				}
				
		
				
				if (search != null)
				{
					
					//convert search string to UTF-8
					search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
					
					liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist(search);

					if (option.equals("0"))
					{
	
						liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist("%" + search + "%");
						
		
						for (int x = 0; x < CDDAO.getInstance().findByTitle(search).size(); x++)
						{
		
							liste.add(CDDAO.getInstance().findByTitle(search).get(x)); 
						}
		
					
						for(int x = 0; x < liste.size(); x++)
						{
							for(int y = 0; y < liste.size(); y++)
							{							
								if (x != y)
								{								
									if (liste.get(x).getEan() == liste.get(y).getEan())
									{
										
										liste.remove(y);
									}
								}
							}
						}
						
						songliste = (LinkedList<SongVO>)SongDAO.getInstance().findByTitle(search);
					}
		
					if (option.equals("1"))
					{
						
						liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist(search);
						
					}
					
					if (option.equals("2"))
					{
						liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByTitle(search);
		
					}  
					
					if (option.equals("3"))
					{
						songliste = (LinkedList<SongVO>)SongDAO.getInstance().findByTitle(search);
					}
					
					log.trace("search for: " + search + " artistresults: " + liste.size() + "    title results: " +   CDDAO.getInstance().findByTitle(search).size());
										
					session.setAttribute("showall", 0);
					session.setAttribute("shownew", 0);
					session.setAttribute("showletter", 0);
					session.setAttribute("showgenre", 0);
					session.setAttribute("DETAIL", 0);
					

					session.setAttribute("cdliste", liste);
					session.setAttribute("songliste", songliste);
		
					if (liste.size() == 0 && songliste.size() == 0)
					{			
						session.setAttribute("nothing", "yes");
							
					} else
					{						
						session.setAttribute("nothing", "no");
					}
					
				}
				
//###########################################
//################# Shopping#################
//###########################################
				
				if (req.getParameter("cdid") != null)
				{
					
					CD_VO temp = CDDAO.getInstance().findByEAN(Long.parseLong(req.getParameter("cdid")));
					session.setAttribute("CD", temp);
					session.setAttribute("CDID", req.getParameter("cdid"));
					session.setAttribute("DETAIL", 1);
					session.setAttribute("shownew", 0);
					session.setAttribute("showall", 0);
					session.setAttribute("linkartist", temp.getInterpret().replaceAll(" ", "%20"));
					
					//session.setAttribute("genr", "");
					session.setAttribute("showgenre", 0);					
					
				} 

				if ( req.getParameter("showall") != null)
				{
					
					session.setAttribute("showall", 1);
					session.setAttribute("shownew", 0);
			   		session.setAttribute("DETAIL", 0);
			   		session.setAttribute("DETAIL2", 0);
					session.setAttribute("fromShop", 1);
					session.setAttribute("fromAll", 1);
					session.setAttribute("fromNew", 0);
					session.setAttribute("genr", "");
					session.setAttribute("showgenre", 0);
					
			   		liste = (LinkedList<CD_VO>)CDDAO.getInstance().findAll();
			   		
					if (req.getParameter("showletter") != null)
					{						
						session.setAttribute("showletter", req.getParameter("showletter"));
					}
				} else
				{
					session.setAttribute("showall", 0);
				}
				
				
				if (req.getParameter("shownew") != null)
				{					
					liste = CDDAO.getInstance().findAll();
					session.setAttribute("shownew", 1);
					session.setAttribute("showall", 0);
					session.setAttribute("showgenre", 0);
					session.setAttribute("DETAIL", 0);
					session.setAttribute("DETAIL2", 0);
					session.setAttribute("fromShop", 1);
					session.setAttribute("fromNew", 1);
					session.setAttribute("fromAll", 0);
					session.setAttribute("genr", "");
					session.setAttribute("showgenre", 0);
				
				} 
				
				
				if (req.getParameter("showgenre") != null)
				{					
					session.setAttribute("showgenre", 1);
					session.setAttribute("showall", 0);
					session.setAttribute("shownew", 0);
					session.setAttribute("DETAIL", 0);
					session.setAttribute("DETAIL2", 0);
					session.setAttribute("genr", req.getParameter("showgenre"));
					session.setAttribute("fromShop", 1);
					session.setAttribute("fromNew", 0);
					session.setAttribute("fromAll", 0);
				
				} 
		
				if (req.getParameter("showletter") != null)
				{
					
					session.setAttribute("letter", req.getParameter("showletter"));
				
				} else
				{
					
					session.setAttribute("letter", "none");
				}
				
			   	String show = req.getParameter("show");
			   	String letter = req.getParameter("showletter");
			   	String genre = req.getParameter("showgenre");
			

			   	if (show != null)
			   	{			   		
			   		if (letter != null)
			   		{			   			
			   			liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist(letter + "%");
			   			
			   		} else
			   		{			   		
			   			liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist(show + "%");
			   		
			   		}
			   		
			   	} else
			   	{			   		
			   		if (letter != null)
			   		{
			   			liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist(letter + "%");
			   			
			   		} 
			   		
			   	}
			   	
		   		if (genre != null)
		   		{		   			
		   			if (letter != null)
		   			{		   				
		   				liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist(letter + "%");
		   				
		   				for (int x = 0; x < liste.size(); x++)
		   				{		   							  					
		   					if (liste.get(x).getGenre().equals(genre) == false)
		   					{		   						
		   						liste.remove(x);
		   						x = x -1;
		   					}
		   				}
		   			
		   			} else
		   			{
		   			
		   				liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByGenre(genre);
		   			
		   			}
		   			
		   			if (genre.equals("Diverse"))
		   			{		   			
			   			if (letter != null)
			   			{			   				
			   				liste = (LinkedList<CD_VO>)CDDAO.getInstance().findByArtist(letter + "%");
			   				
			   				for (int x = 0; x < liste.size(); x++)
			   				{   					
			  					
			   					if (liste.get(x).getGenre().equals("Rock") == true || liste.get(x).getGenre().equals("Pop") == true || liste.get(x).getGenre().equals("HipHop") == true
			   							|| liste.get(x).getGenre().equals("Elektro") == true || liste.get(x).getGenre().equals("House") == true)
			   					{
			   						
			   						liste.remove(x);
			   						x = x -1;
			   					}
			   				}
			   			
			   			} else
			   			{
			   			
			   				liste = (LinkedList<CD_VO>)CDDAO.getInstance().findAll();
			   				
			   				for (int x = 0; x < liste.size(); x++)
			   				{			   					
			  					
			   					if (liste.get(x).getGenre().equals("Rock") == true || liste.get(x).getGenre().equals("Pop") == true || liste.get(x).getGenre().equals("HipHop") == true
			   							|| liste.get(x).getGenre().equals("Elektro") == true || liste.get(x).getGenre().equals("House") == true)
			   					{
			   						
			   						liste.remove(x);
			   						x = x -1;
			   					}
			   				}
			   			
			   			}
		   			
		   			}
		   			
		   			session.setAttribute("showgenre", 1);
		   			session.setAttribute("genr", genre);
		   		} 
			

		   		session.setAttribute("cdliste", liste);	
			   	session.setAttribute("mainID", 4);
			
				if (Integer.parseInt(session.getAttribute("DETAIL").toString()) != 1) 
				{
					session.setAttribute("CDID", 0);
				}
				
	
			} //synchronize end
			
			forward(req, resp, "main.jsp");


		} catch (Exception e)
		{
			log.error("Fehler in ActionShow: \n\n" + e.getMessage());
		}
	}
}
