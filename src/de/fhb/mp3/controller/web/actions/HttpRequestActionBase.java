package de.fhb.mp3.controller.web.actions;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Abstrakte Basisklasse für Web-Aktionen welche vom Servlet ausgeführt werden
 * @author diesel
 *
 */
public abstract class HttpRequestActionBase 
{

	
	/**Aktion die durch Ableitung definiert werden muss.
	 * 
	 * Die Aktion fuehrt Operationen mit dem Modell aus, und 
	 * bereitet die Daten fuer die Ausgabe auf. Als letzte Aktion sollte eine 
	 * Aktion zu einer View den Request weiterleiten, wo dann das Ergebnis
	 * der Aktion eingelesen und in eine (HTML-)Seite einegunden wird
	 * @param req aktueller Request der bearbeitet werden soll
	 * @param res Response-Objekt fuer die Weiterleitung zu dem View
	 * @throws ServletException 
	 */
	public abstract void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException;
	
	/**
	 * @param req aktueller Request der bearbeitet werden soll und in dem Ergebnisse der Action abgelegt sind
	 * @param resp  Response-Objekt zum Schreiben des Ergebnisses
	 * @param forwardName Name von Seite/JSP/Servlet, an die Kontrolle uebergeben wird
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String forwardName) throws ServletException, IOException
	{
		RequestDispatcher reqDis = req.getRequestDispatcher(forwardName);
		reqDis.forward(req, resp);
		
	}
	
	
}
