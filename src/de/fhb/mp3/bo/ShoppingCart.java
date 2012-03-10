package de.fhb.mp3.bo;

/* nötige importe */
import java.util.*;
import java.math.*;
/* paketinterne importe */
import de.fhb.mp3.vo.*;
import de.fhb.mp3.da.*;

/**
 * 
 * Diese Klasse repräsentiert einen Warenkorb -
 * bietet Methoden zum löschen von CDs und Songs und Getter für die Preise
 * 
 * @author diesel 
 *
 */
public class ShoppingCart
{
	
	private LinkedList<CD_VO> cds;
	private LinkedList<SongVO> songs;
	private LinkedList<Long> cdslong;
	private LinkedList<Long> songslong;
	private BigDecimal price;
	private BigDecimal pricesongs;
	private BigDecimal pricecds;

	/**
	 * Default-Konstruktor
	 */
	public ShoppingCart()
	{
		
		cds = new LinkedList<CD_VO>();
		songs = new LinkedList<SongVO>();
		price = new BigDecimal("0.00");
		pricesongs = new BigDecimal("0.00");
		pricecds = new BigDecimal("0.00");
		
	}
	
	/**
	 * Konstruktor
	 * @param cdlist Liste der CD-EANs
	 * @param songlist Liste der Song-IDs
	 */
	public ShoppingCart(LinkedList<Long> cdlist, LinkedList<Long> songlist)
	{
		
		this.cds = new LinkedList<CD_VO>();
		this.songs = new LinkedList<SongVO>();
		this.price = new BigDecimal("0.00");
		this.pricesongs = new BigDecimal("0.00");
		this.pricecds = new BigDecimal("0.00");
		this.cdslong = cdlist;
		this.songslong = songlist;
		
		//CDs hinzufügen und Preis berechnen
		for (int i = 0; i < cdlist.size(); i++)
		{	
			this.cds.add(CDDAO.getInstance().findByEAN(cdlist.get(i)));
			this.pricecds = this.pricecds.add(CDDAO.getInstance().findByEAN(cdlist.get(i)).getPreis());
		
		}
		
		//Songs hinzufügen
		for (int i = 0; i < songlist.size(); i++)
		{	
			
			songs.add(SongDAO.getInstance().findById(songlist.get(i)));
			pricesongs = pricesongs.add(SongDAO.getInstance().findById(songlist.get(i)).getPreis());
		
		}
		
		//Preis von CDs und Songs zusammenrechnen
		price = pricecds.add(pricesongs);
	}
	
	/**
	 * Methode zum löschen einer CD aus dem Warenkorb
	 * 
	 * @param cdean EAN der CD die gelöscht werden soll
	 */
	public void removeCD(long cdean)
	{	
		//erstes Auftreten der CD-EAN löschen
		this.cdslong.removeFirstOccurrence(cdean);
		
		
		//CD aus der Liste löschen
		for (int i = 0; i < this.getCds().size(); i++)
		{
			if (this.getCds().get(i).getEan() == cdean)
			{
				LinkedList<CD_VO> temp = this.getCds();
				temp.remove(i);
				this.setCds(temp);
				temp = null;
				
			}
		}
	}
	
	/**
	 * Methode zum löschen eines Songs aus dem Warenkorb
	 * 
	 * @param songid ID des Songs der gelöscht werden soll
	 */
	public void removeSong(long songid)
	{	
		//erstes Auftreten der Song-ID löschen
		this.songslong.removeFirstOccurrence(songid);
	
		
		//Song aus der Liste löschen
		for (int i = 0; i < this.getSongs().size(); i++)
		{
			if (this.getSongs().get(i).getId() == songid)
			{
				LinkedList<SongVO> temp = this.getSongs();
				temp.remove(i);
				this.setSongs(temp);
				temp = null;
				
			}
		}
	}
	
	/**
	 * @return the cds
	 */
	public LinkedList<CD_VO> getCds()
	{
		return cds;
	}
	
	/**
	 * @return the songs
	 */
	public LinkedList<SongVO> getSongs()
	{
		return songs;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice()
	{
		return price;
	}



	/**
	 * @return the pricesongs
	 */
	public BigDecimal getPricesongs()
	{
		return pricesongs;
	}


	

	/**
	 * @param cds the cds to set
	 */
	public void setCds(LinkedList<CD_VO> cds)
	{
		this.cds = cds;
	}

	/**
	 * @param songs the songs to set
	 */
	public void setSongs(LinkedList<SongVO> songs)
	{
		this.songs = songs;
	}

	/**
	 * @param cdslong the cdslong to set
	 */
	public void setCdslong(LinkedList<Long> cdslong)
	{
		this.cdslong = cdslong;
	}

	/**
	 * @param songslong the songslong to set
	 */
	public void setSongslong(LinkedList<Long> songslong)
	{
		this.songslong = songslong;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	/**
	 * @param pricesongs the pricesongs to set
	 */
	public void setPricesongs(BigDecimal pricesongs)
	{
		this.pricesongs = pricesongs;
	}

	/**
	 * @param pricecds the pricecds to set
	 */
	public void setPricecds(BigDecimal pricecds)
	{
		this.pricecds = pricecds;
	}

	/**
	 * @return the pricecds
	 */
	public BigDecimal getPricecds()
	{
		return pricecds;
	}


	/**
	 * @return the cdslong
	 */
	public LinkedList<Long> getCdslong()
	{	
		//Liste leeren
		cdslong.clear();
		
		//neue Liste anlegen
		for (int i = 0; i < this.getCds().size(); i++)
		{			
			cdslong.add(this.getCds().get(i).getEan());
		}
		
		return cdslong;
	}


	/**
	 * @return the songslong
	 */
	public LinkedList<Long> getSongslong()
	{		
		//Liste leeren
		songslong.clear();		
		
		
		//neue Liste anlegen
		for (int i = 0; i < this.getSongs().size(); i++)
		{			
			songslong.add(this.getSongs().get(i).getId());
		
		}
		
		return songslong;
	}

}
