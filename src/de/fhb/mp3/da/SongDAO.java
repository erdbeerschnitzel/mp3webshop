package de.fhb.mp3.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;

import de.fhb.mp3.vo.*;


/**
 * Datenzugriffs-Klasse für Song-Objekte -
 * erzeugt, verändert, holt und löscht in der bzw. aus der DB.
 * 
 * @author diesel
 *
 */
public class SongDAO extends BaseJdbcDAO {

	private static SongDAO instance = null;
	private static org.apache.log4j.Logger log = Logger.getLogger(SongDAO.class);
	
	
	/**
	 * Konstruktor
	 * 
	 * @param driverName Name des DB-Treibers
	 * @param dbURL URL der DB
	 * @param dbUser Username für DB-Login
	 * @param dbPassword Passwort für DB-Login
	 */
	public SongDAO(String driverName, String dbURL, String dbUser,
			  String dbPassword) {
		super(driverName, dbURL, dbUser, dbPassword);
	}
	
	/**
	 * Default-Konstruktor
	 */
	private SongDAO() {
		super();
	}
	
    
	/**
	 * Getter für SongDAO-Instanz (Singleton-Pattern)
	 * @return Instanz der SongDAO-Klasse
	 */	 
    public static SongDAO getInstance() {
        if (instance == null) {
            instance = new SongDAO();
    }
        return instance;
    }
	
    
	/**
	 * Diese Methode erstellt einen Song anhand der Daten des übergebenen Objekts in der DB
	 * @param vo das Song-Objekt
	 */
	public void create(SongVO vo)
	{
        Connection con = null; 
        PreparedStatement ps = null;
        ResultSet rs = null;
		long key = -1;
		
		String sqlStr = "INSERT INTO song (nr, titel, laenge, file, sample, preis, interpret, cdean) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr, PreparedStatement.RETURN_GENERATED_KEYS);
			
			// set Parameter for sql-insert
			int i = 1;
		
			ps.setInt(i++, vo.getNr());
			ps.setString(i++, vo.getTitel());
			ps.setBigDecimal(i++, vo.getLaenge());
			ps.setString(i++, vo.getFile());
			ps.setString(i++, vo.getSample());
			ps.setBigDecimal(i++, vo.getPreis());
			ps.setString(i++, vo.getInterpret());
			ps.setLong(i++, vo.getCdean());
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

		log.trace("new song saved: " + vo.getTitel());
	}
	

	/**
	 * Diese Methode speichert Änderungen an einem Song-Objekt in der DB
	 * @param vo das Song-Objekt mit den neuen Daten
	 */
	public void update(SongVO vo)
	{
        Connection con = null; 
        PreparedStatement ps = null;
        
		String sqlStr = "UPDATE song SET nr = ?, titel = ?, laenge = ?, file = ?, sample = ?, preis = ?, interpret = ?, cdean = ? WHERE id = ?";
        try
        {
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			
			// set Parameter for sql-update
			int i = 1;
			ps.setInt(i++, vo.getNr());
			ps.setString(i++, vo.getTitel());
			ps.setBigDecimal(i++, vo.getLaenge());
			ps.setString(i++, vo.getFile());
			ps.setString(i++, vo.getSample());
			ps.setBigDecimal(i++, vo.getPreis());
			ps.setString(i++, vo.getInterpret());
			ps.setLong(i++, vo.getCdean());
			ps.setLong(i++, vo.getId());
			
			
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
		
		log.debug("song " + vo.getTitel() + "updated in DB");
	}

	/**
	 * Diese Methode sucht nach dem Song mit der übergebenen ID in der DB und liefert das entsprechende Song-Objekt zurück 
	 * @param id ID des Songs
	 * @return das Song-Objekt
	 */
	public SongVO findById(long id) 
	{
        SongVO vo = null; 
        Connection con = null; 
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sqlStr = "SELECT * FROM song WHERE id = ?";
        
        try
        {
	        con = getConnection();
	        ps = con.prepareStatement(sqlStr);
	        ps.setLong(1, id);
	        rs = ps.executeQuery();
	
	        vo = transformSongSetToVO(rs);
	        
	        
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
			
			log.trace("song-id " + id + " not found");
		}
		
		return vo;
	}

	/**
	 * Diese Methode sucht nach dem Song mit dem übergebenen Titel in der DB und liefert eine Liste mit Song-Objekten zurück 
	 * @param title Titel des Songs
	 * @return Liste mit Song-OBjekten
	 */
	public LinkedList<SongVO> findByTitle(String title) 
	{

        LinkedList<SongVO> vos = new LinkedList<SongVO>(); 
        Connection con = null; 
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sqlStr = "SELECT * FROM song WHERE titel like ?";
        
        try
        {
	        con = getConnection();
	        ps = con.prepareStatement(sqlStr);
	        ps.setString(1, "%" + title + "%");
	        rs = ps.executeQuery();
	
	        vos = transformSongSet(rs);
	        
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
			
			log.trace("no song with title " + title + " found");
		}
		
		return vos;
	} 	
	
	/**
	 * Diese Methode sucht die Songs einer bestimmten CD in der DB und gibt eine Liste mit Song-Objekten zurück
	 * @param ean EAN der CD
	 * @return Liste mit Song-Objekten
	 */
	public LinkedList<SongVO> findSongsOfSpecificCD(long ean) 
	{
        LinkedList<SongVO> results = new LinkedList<SongVO>();
        Connection con = null; 
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sqlStr = "SELECT * FROM song WHERE cdean = ?";
        try
        {
	        con = getConnection();
	        ps = con.prepareStatement(sqlStr);
	        ps.setLong(1, ean);
	        rs = ps.executeQuery();
	
	        results = transformSongSet(rs);
	        
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
		 
		 if (results.size() == 0) log.trace("no songs found for CD with EAN " + ean);
		 
		 return results; 
	} 
	
	
	
	/**
	 * Diese Methode holt alle Song-Objekte aus der DB 
	 * @return eine LinkedList vom Typ SongVO
	 */
	public LinkedList<SongVO> findAll() 
	{
        LinkedList<SongVO> vos = new LinkedList<SongVO>(); 
        Connection con = null; 
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sqlStr = "SELECT * FROM song";
        try
        {
	        con = getConnection();
	        ps = con.prepareStatement(sqlStr);
	        rs = ps.executeQuery();
	
	        vos = transformSongSet(rs);

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
	 * Diese Methode löscht den Song mit der entsprechenden ID aus der DB
	 * @param userid ID des Songs der gelöscht werden soll
	 */
	public void delete(int songid)
	{

		Connection con = null;
		PreparedStatement ps = null;


		String sqlStr = "DELETE FROM song WHERE id = ?";
		
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(sqlStr);
			ps.setLong(1, songid);
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
		
		log.trace("Song with ID " + songid + " deleted from DB");
	}



	/**
	 * Diese Methode wandelt ein SQL ResultSet mit mehreren Zeilen in eine Liste mit SongVOs um
	 * @param rs das SQL ResultSet das umgewandelt werden soll
	 * @return eine Liste mit Song-Objekten
	 */
	protected LinkedList<SongVO> transformSongSet(ResultSet rs) throws SQLException
	{
		
		LinkedList<SongVO> voList = new LinkedList<SongVO>();
		SongVO vo;
		
		while (rs.next())
		{
			vo = (SongVO)map2VO(rs);
			voList.add(vo);
		}
		
		return voList;
	}

	/**
	 * Diese Methode wandelt ein SQL ResultSet mit mehreren Zeilen in ein einzelnes SongVO um
	 * @param rs das SQL ResultSet das umgewandelt werden soll
	 * @return ein Song-Objekt
	 */	
	protected SongVO transformSongSetToVO(ResultSet rs) throws SQLException
	{
		LinkedList<SongVO> voList = new LinkedList<SongVO>();
		SongVO vo;
		
		while (rs.next()) 
		{
			
			vo = (SongVO)map2VO(rs);
			voList.add(vo);
		}
		if (voList.size() > 0){
			
			return voList.get(0);
			
		} else {
			
			log.trace("wrong number of VOs in RS");
			return null;
		}
	}
	
	/**
	 * Diese Methode wandelt ein SQL ResultSet mit einer Zeile aus der User-Tabelle in ein SongVO um
	 * @param rs Das SQL ResultSet welches umgewandelt werden soll
	 * @return das erzeugte SongVO
	 */	
	@Override
	protected SongVO map2VO(ResultSet rs) throws SQLException {
		
		  SongVO vo = new SongVO();
		  
		  vo.setId(rs.getLong("id"));
	      vo.setNr(rs.getInt("nr"));
		  vo.setTitel(rs.getString("titel"));
		  vo.setLaenge(rs.getBigDecimal("laenge"));
		  vo.setFile(rs.getString("file"));
		  vo.setSample(rs.getString("sample"));
		  vo.setCdean(rs.getInt("cdean"));
		  vo.setPreis(rs.getBigDecimal("preis"));
		  vo.setInterpret(rs.getString("interpret"));

		  return vo;
	}
	
}
