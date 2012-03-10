<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


    
<p><b>Neues Profil erstellen</b></p><br>

Um ein eigenes Profil zu erstellen wählen Sie bitte einen Profilnamen und ein Passwort.<br>
Für die Rechnungsabwicklung wird außerdem die Wohnanschrift benötigt.<br><br>

<form method="POST" action="/test/action?do=register&doreg=true"> 

<c:if test="${userexists == desiredusername}">

<br>
<p>Der angegebene Accountname existiert leider schon!</p>

</c:if>

<c:if test="${passwordequal == 'no'}">

<br>
<p>Wiederholtes Passwort stimmt nicht mit Passwort überein!</p> 

</c:if>

<c:if test="${emailinvalid == 'yes'}">

<br>
<p>Die E-Mail-Adresse ist ungültig!</p> 

</c:if>

<c:if test="${all == 'false' }">

<br>
<p>Sie haben leider nicht alle benötigten Angaben gemacht!</p>
<br>

</c:if>

<table border="0" cellpadding="0" cellspacing="0">  
<colgroup>
    <col width="200">
    <col width="200">
</colgroup>
  

<tr>
 <td>Profilname: </td><td> <input type="text" name="desiredusername" size="25" value="${desiredusername}" /></td>
</tr>
<tr> 
 <td>Passwort: </td><td> <input type="password" name="passwort" size="25" value="" /></td>
</tr>
<tr>
 <td>Passwort wiederholen: </td><td>  <input type="password" name="passwortagain" size="25" /></td>
</tr>
<tr> 
 <td>E-Mail: </td><td> <input type="text" name="email" size="25" value="${email}" /></td>
</tr>
<tr> 
 <td>Name: </td><td> <input type="text" name="nachname" size="25" value="${nachname}" /></td>
</tr>
<tr>
 <td>Vorname: </td><td> <input type="text" name="vorname" size="25" value="${vorname}" /></td>
</tr>

<tr>

<c:if test="${LandF == 'Deutschland'}">
 <td>Land: </td>
 
 <td> 
 		<select name="Land" size="1">
       		<option value="Deutschland" selected>Deutschland</option>
      	 	<option value="Österreich">Österreich</option>
 			<option value="Schweiz">Schweiz</option>
 		</select>
 </td>
</c:if>
<c:if test="${LandF == 'Österreich'}">
 <td>Land: </td>
 
 <td> 
 		<select name="Land" size="1">
       		<option value="Deutschland">Deutschland</option>
      	 	<option value="Österreich" selected>Österreich</option>
 			<option value="Schweiz">Schweiz</option>
 		</select>
 </td>
</c:if>
<c:if test="${LandF == 'Schweiz'}">
 <td>Land: </td>
 
 <td> 
 		<select name="Land" size="1">
       		<option value="Deutschland">Deutschland</option>
      	 	<option value="Österreich">Österreich</option>
 			<option value="Schweiz" selected>Schweiz</option>
 		</select>
 </td>
</c:if>

<c:if test="${LandF == 'default'}">
 <td>Land: </td>
 
 <td> 
 		<select name="Land" size="1">
       		<option value="Deutschland" selected>Deutschland</option>
      	 	<option value="Österreich">Österreich</option>
 			<option value="Schweiz">Schweiz</option>
 		</select>
 </td>
</c:if>

</tr>
<tr>
 <td>Postleitzahl: </td><td> <input type="text" name="plz" size="25" value="${plz}" /></td></tr>
<tr>  
 <td>Ort: </td><td> <input type="text" name="stadt" size="25" value="${stadt}" /></td></tr>
<tr> 
 <td>Straße: </td><td> <input type="text" name="strasse" size="25" value="${strasse}" /> </td></tr>
<tr>
  <td>Hausnummer: </td><td> <input type="text" name="hausnummer" size="25" value="${hausnummer}" /></td></tr>
 

</table> 

<br>
		<c:if test="${zahlung != 'Bankeinzug'}">
		
		    <input type="radio" name="Zahlmethode" value="Rechnung" checked> Rechnung<br>
		    <input type="radio" name="Zahlmethode" value="Bankeinzug"> Bankeinzug<br>
		    
	    </c:if>
	    
   
		<c:if test="${zahlung == 'Bankeinzug'}">
		
		    <input type="radio" name="Zahlmethode" value="Rechnung"> Rechnung<br>
		    <input type="radio" name="Zahlmethode" value="Bankeinzug" checked> Bankeinzug<br>
		</c:if>
		
<br>
<input type="checkbox" name="agbaccept"> Hiermit akzeptiere ich die <a href="agb.jsp" target="_blank">AGB</a>.
<br>
<div align="left" style="position: relative; width:50%; left:300px;">  <p><input type="submit" value="Profil erstellen!" /></p></div></form>