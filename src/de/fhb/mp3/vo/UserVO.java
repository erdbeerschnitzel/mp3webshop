package de.fhb.mp3.vo;

import java.util.*;
import java.io.*;

/**
 * 
 * Diese Klasse repr‰sentiert ein User-Value-Objekt - 
 * implementiert das Serializable-Interface
 * @author Peter Reinecke
 *
 */
public class UserVO implements Serializable
{
	
	private static final long serialVersionUID = 6152046718309166795L;
	private long userID;
	private String accountname;
	private String name;
	private String lastname;
	private String password;
	private String stadt;
	private String land;
	private String strasse;
	private String hausnummer;
	private String plz;
	private String zahlungsart;
	private String zahlungsdaten;
	private String email;
	private LinkedList<Long> songs;
	private LinkedList<Long> cds;
	
	/**
	 * Default-Konstruktor
	 */
	public UserVO()
	{		
	}
	
	/**
	 * Konstruktor
	 * @param acc Accoutnname des Users
	 * @param first Vorname des Users
	 * @param last Nachname des User
	 * @param pass Passwort des Users (MD5-Summe)
	 * @param mail E-Mail des Users
	 * @param country Land des Users
	 * @param postlz Postleitzahl der Wohnanschrift
	 * @param town Stadt der Wohnanschrift
	 * @param street Straﬂe der Wohnanschrift
	 * @param number Hausnummer der Wohnanschrift
	 * @param zahlung Zahlungsart des Users
	 * @param bankdaten Kontodaten des Users
	 * @param cdliste Liste der vom User gekauften CDs
	 * @param songliste Liste der vom User gekauften Songs
	 */
	public UserVO(String acc, String first, String last, String pass, String mail, String country, String postlz, String town, String street, String number, String zahlung, String bankdaten, LinkedList<Long> cdliste, LinkedList<Long> songliste)
	{
		this.accountname = acc;
		this.name = first;
		this.lastname = last;
		this.password = pass;
		this.email = mail;
		this.land = country;
		this.plz = postlz;
		this.stadt = town;
		this.strasse = street;
		this.hausnummer = number;
		this.zahlungsart = zahlung;
		this.zahlungsdaten = bankdaten;
		this.cds = cdliste;
		this.songs = songliste;
		
	}
	
	
	public String[] getSplittedBankData()
	{
		
		String[] data = new String[4];
		data[0] = "";
		data[1] = "";
		data[2] = "";
		data[3] = "";

		int a = 0;
		
		StringTokenizer st = new StringTokenizer(this.getZahlungsdaten(), ";");
		
		while(st.hasMoreTokens()){
				
			data[a] = st.nextToken();
			a++;
		}
		
		return data;
	}


	/**
	 * @return the userID
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	/**
	 * @return the accountname
	 */
	public String getAccountname() {
		return accountname;
	}

	/**
	 * @param accountname the accountname to set
	 */
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the songs
	 */
	public LinkedList<Long> getSongs() {
		return songs;
	}

	/**
	 * @param songs the songs to set
	 */
	public void setSongs(LinkedList<Long> songs) {
		this.songs = songs;
	}

	/**
	 * @return the cds
	 */
	public LinkedList<Long> getCds() {
		return cds;
	}

	/**
	 * @param cds the cds to set
	 */
	public void setCds(LinkedList<Long> cds) {
		this.cds = cds;
	}

	/**
	 * @return the stadt
	 */
	public String getStadt() {
		return stadt;
	}

	/**
	 * @param stadt the stadt to set
	 */
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	/**
	 * @return the land
	 */
	public String getLand() {
		return land;
	}

	/**
	 * @param land the land to set
	 */
	public void setLand(String land) {
		this.land = land;
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	/**
	 * @return the hausnummer
	 */
	public String getHausnummer() {
		return hausnummer;
	}

	/**
	 * @param hausnummer the hausnummer to set
	 */
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the plz
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}

	/**
	 * @return the zahlungsart
	 */
	public String getZahlungsart() {
		return zahlungsart;
	}

	/**
	 * @param zahlungsart the zahlungsart to set
	 */
	public void setZahlungsart(String zahlungsart) {
		this.zahlungsart = zahlungsart;
	}

	/**
	 * @return the zahlungsdaten
	 */
	public String getZahlungsdaten() {
		return zahlungsdaten;
	}

	/**
	 * @param zahlungsdaten the zahlungsdaten to set
	 */
	public void setZahlungsdaten(String zahlungsdaten) {
		this.zahlungsdaten = zahlungsdaten;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
}


