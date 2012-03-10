package de.fhb.mp3.vo;
import java.math.*;
import java.io.*;

/**
 * 
 * Diese Klasse repräsentiert ein Song-Value-Objekt - 
 * implementiert das Serializable-Interface
 * @author Peter Reinecke
 *
 */
public class SongVO implements Serializable {

	private static final long serialVersionUID = 615204453166795L;
	private int nr;
	private long id;
	private long cdean;
	private String titel;
	private String interpret;
	private BigDecimal laenge;
	private String file;
	private String sample;
	private BigDecimal preis;

	
	/**
	 * Default-Konstruktor
	 */
	public SongVO()
	{		
	}
	
	/**
	 * Konstruktor
	 * @param ident Song-ID
	 * @param cdident EAN der CD
	 * @param nummer Nummer auf der CD
	 * @param title Titel des Songs
	 * @param length Länge des Songs
	 * @param artist Interpret des Songs
	 * @param price Preis des Songs 
	 * @param file Song-Datei
	 * @param samplefile Song-Sample-Datei
	 */
	public SongVO(long ident, long cdident, int nummer, String title, BigDecimal length, String artist, BigDecimal price, String file, String samplefile)
	{
		this.nr = nummer;
		this.titel = title;
		this.laenge = length;
		this.id = ident;
		this.cdean = cdident;
		this.interpret = artist;
		this.preis = price;
		this.file = file;
		this.sample = samplefile;
	}
	

	/**
	 * Methode zur Prüfung auf Objektidentität
	 * @param o Objekt das geprüft werden soll
	 * @return Wahrheitswert
	 */
	public boolean equals(Object o)
	{		
		SongVO temp = (SongVO)o;
		
		if(temp.id == this.id)
		{
			return true;
			
		} else
		{			
			return false;		
		}
	}

	/**
	 * Methode zum Clonen eines Song-Objekts
	 * @return das geclonte Song-Objekt
	 */
	public SongVO clone()
	{
		
		SongVO clone = new SongVO(this.id, this.cdean, this.nr, this.titel, this.laenge, this.interpret, this.preis, this.file, this.sample);
		
		return clone;
	}
	
	/**
	 * Methode um die wichtigsten Attribute eines Songs als String auszugeben
	 * @return String mit den Attributen
	 */
	public String toString()
	{
		
		String result = "Interpret " + this.interpret + " Titel: " + this.titel + "  Nr: " + this.nr + "\n" 	+ this.titel + "\nLänge: " + this.laenge;
		
		return result;
	}


	/**
	 * @return the nr
	 */
	public int getNr()
	{
		return nr;
	}


	/**
	 * @param nr the nr to set
	 */
	public void setNr(int nr)
	{
		this.nr = nr;
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
	 * @return the cdean
	 */
	public long getCdean()
	{
		return cdean;
	}


	/**
	 * @param cdean the cdean to set
	 */
	public void setCdean(long cdean)
	{
		this.cdean = cdean;
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
	 * @return the laenge
	 */
	public BigDecimal getLaenge() {
		return laenge;
	}


	/**
	 * @param laenge the laenge to set
	 */
	public void setLaenge(BigDecimal laenge)
	{
		this.laenge = laenge;
	}


	/**
	 * @return the soundfile
	 */
	public String getFile() 
	{
		return file;
	}


	/**
	 * @param soundfile the soundfile to set
	 */
	public void setFile(String soundfile)
	{
		this.file = soundfile;
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
	 * @return the sample
	 */
	public String getSample()
	{
		return sample;
	}


	/**
	 * @param sample the sample to set
	 */
	public void setSample(String sample)
	{
		this.sample = sample;
	}


	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	
	
}