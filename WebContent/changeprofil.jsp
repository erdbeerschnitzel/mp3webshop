<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<head>
<link rel="stylesheet" type="text/css" href="changeprofil.css" />
</head>


<div id="profillinks">

<c:if test="${timedout == 'no'}">

	<p><b>Hier können Sie Ihre Profildaten ändern...</b></p><br><br>
	
	<form method="POST" action="/test/action?do=profilchanges&save=true"> 
	
	<table border="0" cellpadding="0" cellspacing="0">  
	<colgroup>
	    <col width="200">
	    <col width="200">
	  </colgroup>
	  
	<tr>
	 <td>Passwort: </td><td> <input type="password" name="passwort" size="15" value="" /></td>
	</tr>
	<tr>
	 <td>Passwort wiederholen: </td><td>  <input type="password" name="passwortagain" size="15" /></td>
	</tr>
	<tr> 
	 <td>E-Mail: </td><td> <input type="text" name="email" size="15" value="${userdaten.email}" /></td>
	</tr>
	<tr> 
	 <td>Name: </td><td> <input type="text" name="nachname" size="15" value="${userdaten.lastname}" /></td>
	</tr>
	<tr>
	 <td>Vorname: </td><td> <input type="text" name="vorname" size="15" value="${userdaten.name}" /></td>
	</tr>
	<tr>
	
	 <td>Land: </td>
	 <td>
		<c:if test="${userdaten.land == 'Deutschland'}">
		 
		 		<select name="Land" size="1" style="width:117px">
		       		<option value="Deutschland" selected>Deutschland</option>
		      	 	<option value="Österreich">Österreich</option>
		 			<option value="Schweiz">Schweiz</option>
		 		</select>
		
		</c:if>
		
		<c:if test="${userdaten.land == 'Schweiz'}">
		
		 		<select name="Land" size="1" style="width:117px">
		       		<option value="Deutschland">Deutschland</option>
		      	 	<option value="Österreich">Österreich</option>
		 			<option value="Schweiz" selected>Schweiz</option>
		 		</select>
		
		</c:if>
		<c:if test="${userdaten.land == 'Österreich'}">
		
		  		<select name="Land" size="1" style="width:117px">
		       		<option value="Deutschland">Deutschland</option>
		      	 	<option value="Österreich" selected>Österreich</option>
		 			<option value="Schweiz">Schweiz</option>
		 		</select>
		
		</c:if>
	 </td>
	</tr>
	<tr>
	 <td>Postleitzahl: </td><td> <input type="text" name="plz" size="15" value="${userdaten.plz}" /></td></tr>
	<tr>  
	 <td>Ort: </td><td> <input type="text" name="stadt" size="15" value="${userdaten.stadt}" /></td></tr>
	<tr> 
	 <td>Straße: </td><td> <input type="text" name="strasse" size="15" value="${userdaten.strasse}" /> </td></tr>
	<tr>
	  <td>Hausnummer: </td><td> <input type="text" name="hausnummer" size="15" value="${userdaten.hausnummer}" /></td></tr>
	 
	
	</table> 
	
	<br><br>
	
	<c:if test="${saved == 'yes'}">
	
		<p><b>Änderungen gespeichert!</b></p>
	
	</c:if>
	
	<div align="left" style="position: relative; width:50%; left:110px;">  <p><input type="submit" value="Änderungen speichern" /></p></div></form>
	
	
</div>

<div id="profilrechts">
	
	<br><br><br>
	<p><b>Rechnungsoptionen</b></p>
	
	<form method="POST" action="/test/action?do=profilchanges&savebank=true">
	  <p>Momentan gewählte Zahlungsart:</p>
	  <p>
	  
		<c:if test="${zahlung == 'Rechnung'}">
		
		    <input type="radio" name="Zahlmethode" value="Rechnung" checked> Rechnung<br>
		    <input type="radio" name="Zahlmethode" value="Bankeinzug"> Bankeinzug<br>
		    
	    </c:if>
	    
   
		<c:if test="${zahlung == 'Bankeinzug'}">
		
		    <input type="radio" name="Zahlmethode" value="Rechnung"> Rechnung<br>
		    <input type="radio" name="Zahlmethode" value="Bankeinzug" checked> Bankeinzug<br>

		    <br>
			<table border="0" cellpadding="0" cellspacing="0">  
			<colgroup>
			    <col width="200">
			    <col width="200">
			  </colgroup>
			
			<tr> 
			 <td>Kontoinhaber: </td><td> <input type="text" name="inhaber" size="15" value="${bankdaten[0]}" /></td>
			</tr>
			<tr> 
			 <td>Kontonummer: </td><td> <input type="text" name="kontonr" size="15" value="${bankdaten[1]}" /></td>
			</tr>			  
			<tr> 
			 <td>Bankleitzahl: </td><td> <input type="text" name="blz" size="15" value="${bankdaten[2]}" /></td>
			</tr>
			<tr> 
			 <td>Kreditinstitut: </td><td> <input type="text" name="bank" size="15" value="${bankdaten[3]}" /></td>
			</tr>
			</table>
		    
	    </c:if>
	    
		</p><br>
	  
		<c:if test="${savedbank == 'yes'}">
		
			<p><b>Änderungen gespeichert!</b></p>
		
		</c:if>

		<div align="left" style="position: relative; width:50%; left:110px;">  <p><input type="submit" value="Änderungen speichern" /></p></div></form>

	</c:if>

	<c:if test="${timedout == 'yes'}">
	
		<p><b>Sie waren zu lange inaktiv. Zu Ihrer eigenen Sicherheit müssen Sie sich neu einloggen.</b></p>
	
	</c:if>
</div>
