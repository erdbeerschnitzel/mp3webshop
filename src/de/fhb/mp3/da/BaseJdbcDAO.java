package de.fhb.mp3.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Abstrakte Basisklasse für Datenzugriffs-Klassen
 * 
 * 
 * @author diesel
 *
 */

public abstract class BaseJdbcDAO
{
	@SuppressWarnings("unused")
	private String driverName;

	@SuppressWarnings("unused")
	private String dbURL, dbUser, dbPassword;

	/**
	 * Konstruktor
	 * 
	 * @param driverName Name des DB-Treibers
	 * @param dbURL URL der DB
	 * @param dbUser Username für DB-Login
	 * @param dbPassword Passwort für DB-Login
	 */
	public BaseJdbcDAO(String driverName, String dbURL, String dbUser, String dbPassword)
	{
		this.driverName = driverName;
		this.dbURL = dbURL;
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;
	}

	/**
	 * Default-Konstruktor
	 */
	public BaseJdbcDAO()
	{
		this("driverName", "dbURL", "dbUser", "dbPassword");
	}

	/**
	 * Getter für DB-Verbindung
	 * @return DB-Verbindung
	 * @throws DataAccessException
	 */
	protected Connection getConnection() throws DataAccessException 
	{
	    
		try 
	    {
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      //
	      //return DriverManager.getConnection("jdbc:mysql://athene/jb-jee02-db", "jb_jee02", "jeePW02");
	      return DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "diesel");
	   
	    } catch(SQLException sqlex) 
	    {
	       throw new DataAccessException("DB-Connection nicht verfuegbar");
	    
	    } catch(Exception ex) 
	    {
	       throw new DataAccessException("Fehler bei DB-Zugriff" + ex);
	    }
	}
	

	//abstrakte Deklaration
	protected abstract Object map2VO(ResultSet rs) throws SQLException;
	
	
}

