package de.fhb.mp3.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;

import de.fhb.mp3.vo.CD_VO;

/**
 * Datenzugriffs-Klasse für CD-Objekte -
 * erzeugt, verändert, holt und löscht in der bzw. aus der DB.
 * 
 * @author diesel
 *
 */
public class CDDAO extends BaseJdbcDAO
{

	private static CDDAO instance = null;
	private static org.apache.log4j.Logger log = Logger.getLogger(SongDAO.class);
	
	
	/**
	 * Konstruktor
	 * 
	 * @param driverName Name des DB-Treibers
	 * @param dbURL URL der DB
	 * @param dbUser Username für DB-Login
	 * @param dbPassword Passwort für DB-Login
	 */
	public CDDAO(String driverName, String dbURL, String dbUser,String dbPassword)
	{
		super(driverName, dbURL, dbUser, dbPassword);
	}

	
	/**
	 * Default-Konstruktor
	 */
	private CDDAO()
	{
		super();
	}

	/**
	 * Getter für CDDAO-Instanz (Singleton-Pattern)
	 * @return Instanz der CDDAO-Klasse
	 */	 
	public static CDDAO getInstance()
	{
		if (instance == null) {
			instance = new CDDAO();
		}
		return instance;
	}

	/**
	 * Diese Methode erstellt eine CD anhand der Daten des übergebenen Objekts in der DB
	 * 
	 * @param vo das CD-Objekt
	 */	
	public void create(CD_VO vo) 
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long key = -1;

		//SQL-string erzeugen
		String sqlStr = "INSERT INTO cd (ean, titel, artist, veroeffentlichung, cover, preis, verkaeufe, genre, label, laufzeit, file) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			con = getConnection();
			ps = con.prepareStatement(sqlStr, PreparedStatement.RETURN_GENERATED_KEYS);

			//laufvariable
			int i = 1;
			
			//parameter für sql-insert setzen
			ps.setLong(i++, vo.getEan());
			ps.setString(i++, vo.getTitel());
			ps.setString(i++, vo.getInterpret());
			ps.setLong(i++, vo.getVeroeffentlichung());
			ps.setString(i++, vo.getCover());
			ps.setBigDecimal(i++, vo.getPreis());
			ps.setInt(i++, vo.getVerkauefe());
			ps.setString(i++, vo.getGenre());
			ps.setString(i++, vo.getLabel());
			ps.setString(i++, vo.getLaufzeit());
			ps.setString(i++, vo.getFile());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next())
			{
				key = rs.getLong(1); 
			}
			else
			{				
				log.error("Error executing insert: " + sqlStr);
			}
			
			//CD-ID setzen
			vo.setId(key);
			
			
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

		log.trace("new CD saved: " + vo.getTitel() + " - " +  vo.getInterpret());
	}

	
	/**
	 * Diese Methode speichert Änderungen an einem CD-Objekt in der DB
	 * @param vo das CD-Objekt mit den neuen Daten
	 */
	public void update(CD_VO vo) 
	{
		Connection con = null;
		PreparedStatement ps = null;

		String sqlStr = "UPDATE cd SET ean = ?, artist = ?, titel = ?, veroeffentlichung = ?, cover = ?, preis = ?, verkaeufe =?, genre= ?, laufzeit = ?, label = ?, file = ? WHERE id = ?";
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);

			// set parameters for sql-update
			int i = 1;
			ps.setLong(i++, vo.getEan());
			ps.setString(i++, vo.getInterpret());
			ps.setString(i++, vo.getTitel());
			ps.setInt(i++, vo.getVeroeffentlichung());
			ps.setString(i++, vo.getCover());
			ps.setBigDecimal(i++, vo.getPreis());
			ps.setLong(i++, vo.getId());
			ps.setInt(i++, vo.getVerkauefe());
			ps.setString(i++, vo.getGenre());
			ps.setString(i++, vo.getLaufzeit());
			ps.setString(i++, vo.getLabel());
			ps.setString(i++, vo.getFile());
			
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
		
		log.debug("CD with EAN " + vo.getEan() + "updated in DB");
	}

	
	/**
	 * Diese Methode sucht nach dem CD mit der übergebenen EAN in der DB und liefert das entsprechende CD-Objekt zurück 
	 * @param ean EAN der CD
	 * @return das CD-Objekt
	 */
	public CD_VO findByEAN(long ean) 
	{
		CD_VO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM cd WHERE ean = ?";
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setLong(1, ean);
			rs = ps.executeQuery();

			vo = (CD_VO) transformCDSetToVO(rs);
			
			
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
			
			log.trace("CD-EAN " + ean + " not found");
		}
		
		return vo;
	}


	/**
	 * Diese Methode sucht nach CDs mit der übergebenen Titel in der DB und liefert eine Liste mit CD-Objekten zurück 
	 * @param title Titel der CD
	 * @return Liste mit CD-Objekten
	 */
	public LinkedList<CD_VO> findByTitle(String title) 
	{
		
		LinkedList<CD_VO> vos = new LinkedList<CD_VO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM cd WHERE titel like ?";
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setString(1, "%" + title + "%");
			rs = ps.executeQuery();

			vos = transformCDSet(rs);

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
		
		if (vos.size() == 0){
			
			log.trace("no CD with title " + title + " found");
		}
		
		return vos;
	} 
	
	/**
	 * Diese Methode sucht nach CDs mit der übergebenen Interpreten in der DB und liefert eine Liste mit CD-Objekten zurück 
	 * @param artist Interpret der CD
	 * @return Liste mit CD-Objekten
	 */
	public LinkedList<CD_VO> findByArtist(String artist) 
	{
		
		LinkedList<CD_VO> vos = new LinkedList<CD_VO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM cd WHERE artist like ?";
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setString(1, "%" + artist + "%");
			rs = ps.executeQuery();

			vos = transformCDSet(rs);

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
		
		if (vos.size() == 0){
			
			log.trace("no CD with interpret " + artist + " found");
		}
		
		return vos;
	} 
	
	
	/**
	 * Diese Methode sucht nach CDs mit den übergebenen Genre in der DB und liefert eine Liste mit CD-Objekten zurück 
	 * @param genre Genre der CD
	 * @return Liste mit CD-Objekten
	 */
	public LinkedList<CD_VO> findByGenre(String genre) 
	{
		
		LinkedList<CD_VO> vos = new LinkedList<CD_VO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM cd WHERE genre like ?";
		
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setString(1, genre);
			rs = ps.executeQuery();

			vos = transformCDSet(rs);


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
		
		if (vos.size() == 0){
			
			log.trace("no CD with genre " + genre + " found");
		}
		
		return vos;
	} 
	
	
	/**
	 * Diese Methode holt alle CDs aus der DB und liefert eine Liste mit CD-Objekten zurück 
	 * @return Liste mit CD-Objekten
	 */	
	public LinkedList<CD_VO> findAll() 
	{
		
		LinkedList<CD_VO> vos = new LinkedList<CD_VO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sqlStr = "SELECT * FROM cd";
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			rs = ps.executeQuery();

			vos = transformCDSet(rs);
			
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
		
		if (vos.size() == 0){
			
			log.trace("no CD found");
		}
		
		return vos;
	} 


	/**
	 * Diese Methode löscht die CD mit der entsprechenden ID aus der DB
	 * @param userid ID der CD die gelöscht werden soll
	 */
	public void delete(long cdid) throws DataAccessException {

		Connection con = null;
		PreparedStatement ps = null;

		String sqlStr = "DELETE FROM cd WHERE id = ?";
		
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setLong(1, cdid);
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
		
		log.trace("CD with ID " + cdid + " deleted from DB");
	}
	
	
	/**
	 * Diese Methode wandelt ein SQL ResultSet mit mehreren Zeilen in eine Liste mit CD_VOs um
	 * @param rs das SQL ResultSet das umgewandelt werden soll
	 * @return eine Liste mit CD-Objekten
	 */
	protected LinkedList<CD_VO> transformCDSet(ResultSet rs) throws SQLException{
		LinkedList<CD_VO> voList = new LinkedList<CD_VO>();
		CD_VO vo;
		while (rs.next()) {
			vo = (CD_VO)map2VO(rs);

			vo.setSongs(SongDAO.getInstance().findSongsOfSpecificCD(vo.getEan()));
			
			voList.add(vo);
		}
		return voList;
	}
	

	/**
	 * Diese Methode wandelt ein SQL ResultSet mit mehreren Zeilen in ein einzelnes CD_VO um
	 * @param rs das SQL ResultSet das umgewandelt werden soll
	 * @return ein CD-Objekt
	 */	
	protected CD_VO transformCDSetToVO(ResultSet rs) throws SQLException
	{
		LinkedList<CD_VO> voList;
		voList = transformCDSet(rs);
		
		if (voList.size() > 0)
		{
			return voList.get(0);
		
		} else
		{
			log.trace("wrong number of VOs in RS");
			return null;
		}
		
	}
	
	/**
	 * Diese Methode wandelt ein SQL ResultSet mit einer Zeile aus der CD-Tabelle in ein CD_VO um
	 * @param rs Das SQL ResultSet welches umgewandelt werden soll
	 * @return das erzeugte CD_VO
	 */		
	@Override
	protected CD_VO map2VO(ResultSet rs) throws SQLException 
	{
		
		CD_VO vo = new CD_VO();
		
		vo.setId(rs.getLong("id"));
		vo.setEan(rs.getLong("ean"));
		vo.setInterpret(rs.getString("artist"));
		vo.setTitel(rs.getString("titel"));
		vo.setVeroeffentlichung(rs.getInt("veroeffentlichung"));
		vo.setCover(rs.getString("cover"));
		vo.setPreis(rs.getBigDecimal("preis"));
		vo.setVerkauefe(rs.getInt("verkaeufe"));
		vo.setGenre(rs.getString("genre"));
		vo.setLaufzeit(rs.getString("laufzeit"));
		vo.setLabel(rs.getString("label"));
		vo.setFile(rs.getString("file"));
		

		return vo;
	}

	


}
