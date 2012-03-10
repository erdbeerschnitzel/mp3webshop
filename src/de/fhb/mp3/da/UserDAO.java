package de.fhb.mp3.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;

import de.fhb.mp3.vo.*;

/**
 * Datenzugriffs-Klasse für User-Objekte -
 * erzeugt, verändert, holt und löscht in der bzw. aus der DB.
 * 
 * @author diesel
 *
 */
public class UserDAO extends BaseJdbcDAO 
{

	private static UserDAO instance = null;
	private static org.apache.log4j.Logger log = Logger.getLogger(UserDAO.class);


	/**
	 * Konstruktor
	 * 
	 * @param driverName Name des DB-Treibers
	 * @param dbURL URL der DB
	 * @param dbUser Username für DB-Login
	 * @param dbPassword Passwort für DB-Login
	 */
	public UserDAO(String driverName, String dbURL, String dbUser, String dbPassword)
	{
		super(driverName, dbURL, dbUser, dbPassword);
	}

	/**
	 * Default-Konstruktor
	 */
	private UserDAO()
	{
		super();
	}

	/**
	 * Getter für UserDAO-Instanz (Singleton-Pattern)
	 * @return Instanz der UserDAO-Klasse
	 */	 
	public static UserDAO getInstance() 
	{
		if (instance == null) 
		{
			instance = new UserDAO();
		}
		
		return instance;
	}

	/**
	 * Diese Methode erstellt einen User anhand der Daten des übergebenen Objekts in der DB
	 * @param vo das User-Objekt
	 */
	public void create(UserVO vo)
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long key = -1;

		String sqlStr = "INSERT INTO user (accountname, name, lastname, passwort, stadt, land, strasse, hausnummer, plz, cds, songs, zahlungsart, zahlungsdaten, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			con = getConnection();
			ps = con.prepareStatement(sqlStr, PreparedStatement.RETURN_GENERATED_KEYS);

			// set parameters for sql-insert
			int i = 1;
			ps.setString(i++, vo.getAccountname());
			ps.setString(i++, vo.getName());
			ps.setString(i++, vo.getLastname());
			ps.setString(i++, vo.getPassword());
			ps.setString(i++, vo.getStadt());
			ps.setString(i++, vo.getLand());
			ps.setString(i++, vo.getStrasse());
			ps.setString(i++, vo.getHausnummer());
			ps.setString(i++, vo.getPlz());
			//Listen mit CDs und Songs sind noch leer
			ps.setString(i++, "");
			ps.setString(i++, "");
			ps.setString(i++, vo.getZahlungsart());
			ps.setString(i++, vo.getZahlungsdaten());
			ps.setString(i++, vo.getEmail());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next())
			{
				key = rs.getInt(1);
				
			} else
			{				
				log.error("Error executing insert: " + sqlStr);
			}
			
			//User-ID setzen
			vo.setUserID(key);
			

		} catch (SQLException sqle) 
		{
			log.error("Error executing " + sqlStr + "\n" +  sqle);
			
		} finally 
		{
			try
			{
				// close the resources
				if (ps != null)	ps.close();
				if (con != null) con.close();
				
			} catch (SQLException sqlee) 
			{
				log.debug("Can't close DB connection...\n" + sqlee);
			}
		}

		log.info("new user registered: " + vo.getAccountname());
	
	}

	/**
	 * Diese Methode speichert Änderungen an einem User-Objekt in der DB
	 * @param vo das User-Objekt mit den neuen Daten
	 */
	public void update(UserVO vo)
	{
		Connection con = null;
		PreparedStatement ps = null;

		String sqlStr = "UPDATE user SET accountname = ?, name = ?, lastname = ?, passwort = ?, stadt = ?, land = ?, strasse = ?, hausnummer = ?, plz = ?, email = ?, cds = ?, songs = ?, zahlungsart = ?, zahlungsdaten = ? WHERE id = ?";
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);

			// set parameters for sql-update
			int i = 1;
			ps.setString(i++, vo.getAccountname());
			ps.setString(i++, vo.getName());
			ps.setString(i++, vo.getLastname());
			ps.setString(i++, vo.getPassword());
			ps.setString(i++, vo.getStadt());
			ps.setString(i++, vo.getLand());
			ps.setString(i++, vo.getStrasse());
			ps.setString(i++, vo.getHausnummer());
			ps.setString(i++, vo.getPlz());
			ps.setString(i++, vo.getEmail());
			
			String tempstring = "";
			
			for (int x = 0; x  < vo.getCds().size(); x++){
				
				tempstring = tempstring + vo.getCds().get(x) + ";";
			}
			
			
			ps.setString(i++, tempstring);
			
			tempstring = "";
			
			for (int x = 0; x  < vo.getSongs().size(); x++){
				
				tempstring = tempstring + vo.getSongs().get(x) + ";";
			}
			
			ps.setString(i++, tempstring);
			ps.setString(i++, vo.getZahlungsart());
			ps.setString(i++, vo.getZahlungsdaten());
			ps.setLong(i++, vo.getUserID());


			ps.executeUpdate();
			
		} catch (SQLException sqle) 
		{
			log.error("Error executing " + sqlStr + "\n" +  sqle);
			
		} finally 
		{
			try
			{
				// close the resources
				if (ps != null)	ps.close();
				if (con != null) con.close();
				
			} catch (SQLException sqlee) 
			{
				log.debug("Can't close DB connection...\n" + sqlee);
			}
		}
		
		log.debug("user " + vo.getAccountname() + " updated in DB");
	}

	/**
	 * Diese Methode sucht nach dem User mit der übergebenen ID in der DB und liefert das entsprechende User-Objekt zurück falls der User gefunden wurde
	 * @param userid ID des Users
	 * @return das User-OBjekt
	 */
	public UserVO findById(int userid) 
	{
		UserVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM user WHERE id = ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setInt(1, userid);
			rs = ps.executeQuery();

			vo = (UserVO) transformUserSetToVO(rs);
			
		} catch (SQLException sqle) 
		{
			log.error("Error executing " + sqlStr + "\n" +  sqle);
			
		} finally 
		{
			try
			{
				// close the resources
				if (ps != null)	ps.close();
				if (con != null) con.close();
				
			} catch (SQLException sqlee) 
			{
				log.debug("Can't close DB connection...\n" + sqlee);
			}
		}
		
		if (vo == null)
		{
			
			log.trace("user-id " + userid + " not found");
		}
		return vo;
	}
	
	/**
	 * Diese Methode sucht User anhand des Accountnamens in der DB und liefert ein User-Objekt zurück falls der User gefunden wurde
	 * @param acc Accountname des Users
	 * @return das User-Objekt
	 */
	public UserVO findAccountByAccountName(String acc) 
	{
		UserVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;

		String sqlStr = "SELECT * FROM user WHERE accountname = ?";
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setString(1, acc);
			rs = ps.executeQuery();
	
			vo = transformUserSetToVO(rs);  
			

		} catch (SQLException sqle) 
		{
			log.error("Error executing " + sqlStr + "\n" +  sqle);
			
		} finally 
		{
			try
			{
				// close the resources
				if (ps != null)	ps.close();
				if (con != null) con.close();
				
			} catch (SQLException sqlee) 
			{
				log.debug("Can't close DB connection...\n" + sqlee);
			}
		}
		
		return vo;
	}

	/**
	 * Diese Methode sucht  User anhand des Nachnamens in der DB und liefert eine Liste mit gefunden User-Objekten zurück
	 * @param name Nachname des Users
	 * @return Liste mit gefundenen User-Objekten
	 */	
	public LinkedList<UserVO> findAccountByLastName(String name) 
	{
		LinkedList<UserVO> vos = new LinkedList<UserVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM user WHERE lastname like ?";
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();

			vos = transformUserSet(rs);
			
			
		} catch (SQLException sqle) 
		{
			log.error("Error executing " + sqlStr + "\n" +  sqle);
			
		} finally 
		{
			try
			{
				// close the resources
				if (ps != null)	ps.close();
				if (con != null) con.close();
				
			} catch (SQLException sqlee) 
			{
				log.debug("Can't close DB connection...\n" + sqlee);
			}
		}
		
		return vos;
	}
	
	
	/**
	 * Diese Methode holt alle User-Objekte aus der DB 
	 * @return eine LinkedList vom Typ UserVO
	 */
	public LinkedList<UserVO> findAll() 
	{
		LinkedList<UserVO> vos = new LinkedList<UserVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM user";
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			rs = ps.executeQuery();

			vos = transformUserSet(rs);
			
		} catch (SQLException sqle) 
		{
			log.error("Error executing " + sqlStr + "\n" +  sqle);
			
		} finally 
		{
			try
			{
				// close the resources
				if (ps != null)	ps.close();
				if (con != null) con.close();
				
			} catch (SQLException sqlee) 
			{
				log.debug("Can't close DB connection...\n" + sqlee);
			}
		}
		return vos;
	}

	/**
	 * Diese Methode löscht den User mit der entsprechenden ID aus der DB
	 * @param userid ID des Users der gelöscht werden soll
	 */
	public void delete(int userid)
	{

		Connection con = null;
		PreparedStatement ps = null;


		String sqlStr = "DELETE FROM user WHERE id = ?";
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setLong(1, userid);
			ps.executeUpdate();

		} catch (SQLException sqle) 
		{
			log.error("Error executing " + sqlStr + "\n" +  sqle);
			
		} finally 
		{
			try
			{
				// close the resources
				if (ps != null)	ps.close();
				if (con != null) con.close();
				
			} catch (SQLException sqlee) 
			{
				log.debug("Can't close DB connection...\n" + sqlee);
			}
		}
		
		log.trace("User with ID " + userid + " deleted from DB");
	}

	/**
	 * Diese Methode wandelt ein SQL ResultSet mit einer Zeile aus der User-Tabelle in ein UserVO um
	 * @param rs Das SQL ResultSet welches umgewandelt werden soll
	 * @return das erzeugte UserVO
	 */
	@Override
	protected UserVO map2VO(ResultSet rs) throws SQLException
	{
		UserVO vo = new UserVO();
		
		vo.setUserID(rs.getLong("id"));
		vo.setAccountname(rs.getString("accountname"));
		vo.setName(rs.getString("name"));
		vo.setLastname(rs.getString("lastname"));
		vo.setPassword(rs.getString("passwort"));
		vo.setStadt(rs.getString("stadt"));
		vo.setLand(rs.getString("land"));
		vo.setStrasse(rs.getString("strasse"));
		vo.setHausnummer(rs.getString("hausnummer"));
		vo.setPlz(rs.getString("plz"));
		vo.setEmail(rs.getString("email"));
		
		
		String temp = rs.getString("cds");
		StringTokenizer st = new StringTokenizer(temp, ";");
		LinkedList<Long> longs = new LinkedList<Long>();
		
		while(st.hasMoreTokens())
		{
			longs.add(Long.parseLong(st.nextToken()));
		}
		
		vo.setCds(longs);
		
		temp = rs.getString("songs");
		st = new StringTokenizer(temp, ";");
		longs = new LinkedList<Long>();
		
		while(st.hasMoreTokens())
		{
			longs.add(Long.parseLong(st.nextToken()));
		}
		
		vo.setSongs(longs);
		
		vo.setZahlungsart(rs.getString("zahlungsart"));
		vo.setZahlungsdaten(rs.getString("zahlungsdaten"));
		
		return vo;
	}
	
	/**
	 * Diese Methode wandelt ein SQL ResultSet mit mehreren Zeilen in eine Liste mit UserVOs um
	 * @param rs das SQL ResultSet das umgewandelt werden soll
	 * @return Liste mit UserVOs
	 */
	protected LinkedList<UserVO> transformUserSet(ResultSet rs) throws SQLException
	{
		LinkedList<UserVO> voList = new LinkedList<UserVO>();
		UserVO vo;
		
		while (rs.next())
		{
			vo = (UserVO)map2VO(rs);
			voList.add(vo);
		}
		
		return voList;
	}

	/**
	 * Diese Methode wandelt ein SQL ResultSet mit mehreren Zeilen in ein einzelnes UserVO um
	 * @param rs das SQL ResultSet das umgewandelt werden soll
	 * @return ein UserVOs
	 */
	protected UserVO transformUserSetToVO(ResultSet rs) throws SQLException, DataAccessException
	{
		List<UserVO> voList;
		voList = transformUserSet(rs);
		
		if (voList.size() > 0)
		{
			return voList.get(0);
		
		} else
		{
			log.trace("wrong number of VOs in RS");
			return null;
		}
		
	}

}
