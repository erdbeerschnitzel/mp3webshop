package de.fhb.mp3.vo;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * 
 * Diese Klasse repräsentiert ein CD-Value-Objekt - 
 * implementiert das Serializable-Interface
 * @author Peter Reinecke
 *
 */
public class CD_VO implements Serializable
{
	
	
	private static final long serialVersionUID = 6079213604771753491L;
	private List<SongVO> songs;
	private long id;
	private long ean;
	private int verkauefe;
	private int veroeffentlichung;
	private BigDecimal preis;
	private String titel;
	private String interpret;
	private String genre;
	private String cover;
	private String laufzeit;
	private String label;
	private String file;


	/**
	 * Default-Konstruktor
	 */
	public CD_VO()
	{		
	}
	
	/**
	 * Konstruktor
	 * @param ean EAN der CD 
	 * @param title Titel der CD
	 * @param band Interpret der CD
	 * @param year Veröffentlichungsjahr der CD
	 * @param cove Cover der CD
	 * @param genr Genre der CD
	 * @param labl Label der CD
	 * @param price Preis der CD
	 */
	public CD_VO(long ean, String title, String band, int year, String cove, String genr, String labl, BigDecimal price)
	{
		this.ean = ean;
		this.titel = title;
		this.interpret = band;
		this.veroeffentlichung = year;
		this.cover = cove;
		this.genre = genr;
		this.label = labl;
		this.preis = price;
	}
	
	/**
	 * Methode zur Prüfung auf Objektidentität
	 * @param o Objekt das geprüft werden soll
	 * @return Wahrheitswert
	 */
	public boolean equals(Object o)
	{
		
		CD_VO temp = (CD_VO)o;
		
		if(temp.ean == this.ean)
		{
			return true;
			
		} else
		{			
			return false;
		}
	}
	
	/**
	 * Methode zum Clonen eines CD-Objekts
	 * @return das geclonte CD-Objekt
	 */
	public CD_VO clone()
	{
		
		CD_VO clone = new CD_VO(this.ean, this.titel, this.interpret, this.veroeffentlichung, this.cover, this.genre, this.label, this.preis);
		
		return clone;
	}
	
	/**
	 * Methode um die wichtigsten Attribute einer CD als String auszugeben
	 * @return String mit den Attributen
	 */
	public String toString()
	{
		
		String result = "EAN: " + this.ean + "\n" + this.interpret + " - " + this.titel 
						+ "\nVeröffentlichung: " + this.veroeffentlichung;
		
		return result;
	}

/**
 * @return the songs
 */
public List<SongVO> getSongs()
{
	return songs;
}

/**
 * @param songs the songs to set
 */
public void setSongs(List<SongVO> songs)
{
	this.songs = songs;
}

/**
 * @return the id
 */
public long getId()
{
	return id;
}

/**
 * @param id the id to set
 */
public void setId(long id)
{
	this.id = id;
}

/**
 * @return the ean
 */
public long getEan()
{
	return ean;
}

/**
 * @param ean the ean to set
 */
public void setEan(long ean)
{
	this.ean = ean;
}

/**
 * @return the titel
 */
public String getTitel()
{
	return titel;
}

/**
 * @param titel the titel to set
 */
public void setTitel(String titel)
{
	this.titel = titel;
}

/**
 * @return the interpret
 */
public String getInterpret()
{
	return interpret;
}

/**
 * @param interpret the interpret to set
 */
public void setInterpret(String interpret)
{
	this.interpret = interpret;
}

/**
 * @return the genre
 */
public String getGenre()
{
	return genre;
}

/**
 * @param genre the genre to set
 */
public void setGenre(String genre)
{
	this.genre = genre;
}

/**
 * @return the verkauefe
 */
public int getVerkauefe()
{
	return verkauefe;
}

/**
 * @param verkauefe the verkauefe to set
 */
public void setVerkauefe(int verkauefe)
{
	this.verkauefe = verkauefe;
}

/**
 * @return the veroeffentlichung
 */
public int getVeroeffentlichung() 
{
	return veroeffentlichung;
}

/**
 * @param veroeffentlichung the veroeffentlichung to set
 */
public void setVeroeffentlichung(int veroeffentlichung)
{
	this.veroeffentlichung = veroeffentlichung;
}

/**
 * @return the cover
 */
public String getCover()
{
	return cover;
}

/**
 * @param cover the cover to set
 */
public void setCover(String cover)
{
	this.cover = cover;
}

/**
 * @return the track count
 */
public int getAnzahl()
{

	return this.getSongs().size();
}


/**
 * @return the preis
 */
public BigDecimal getPreis()
{
	return preis;
}

/**
 * @param preis the preis to set
 */
public void setPreis(BigDecimal preis)
{
	this.preis = preis;
}

/**
 * @return the laufzeit
 */
public String getLaufzeit()
{
	return laufzeit;
}

/**
 * @param laufzeit the laufzeit to set
 */
public void setLaufzeit(String laufzeit)
{
	this.laufzeit = laufzeit;
}

/**
 * @return the label
 */
public String getLabel()
{
	return label;
}

/**
 * @param label the label to set
 */
public void setLabel(String label)
{
	this.label = label;
}



/**
 * @return the file
 */
public String getFile()
{
	return file;
}

/**
 * @param file the file to set
 */
public void setFile(String file)
{
	this.file = file;
}

/**
 * @return the serialVersionUID
 */
public static long getSerialVersionUID()
{
	return serialVersionUID;
}


}
