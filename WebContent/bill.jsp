<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">

<head>
<title></title>
</head>
<body>

<h2>Rechnung</h2>

Sie haben folgende Artikel erworben: <br></br><br></br>

<table  border="0" cellpadding="0" cellspacing="0">
	
	<colgroup>
	    <col width="100"/>
	    <col width="200"/>
	    <col width="200"/>
	    <col width="80"/>
	</colgroup>

	<tr>
		<td><b>Artikelart</b></td><td><b>Artikel</b></td><td><b>Interpret</b></td><td><b>Preis</b></td>
	</tr>
	
	<tr>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
	</tr>
	
	<c:forEach var='item' items='${gekauftecds}' varStatus='number'>
	
		
		<tr>
			<td><b>Album</b></td><td><b><c:out value='${item.titel}'/></b></td><td><b><c:out value='${item.interpret}'/></b></td><td><b><c:out value='${item.preis}'/> €</b></td>
		</tr>
	
	</c:forEach>
	
	<tr>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
	</tr>
	
	<c:forEach var='item' items='${gekauftesongs}' varStatus='number'>
	
		<tr>
			<td><b>Song</b></td><td><b><c:out value='${item.titel}'/></b></td><td><b><c:out value='${item.interpret}'/></b></td><td><b><c:out value='${item.preis}'/> €</b></td>
		</tr>
	
	</c:forEach>

	<tr>
		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
	</tr>
	<tr>
		<td>Gesamtsumme</td><td>&nbsp;</td><td>&nbsp;</td><td>${PRICE} €</td>
	</tr>
</table>

<br></br><br></br>
<c:if test="${zahlung == 'Rechnung'}">

<h3>Sie haben als Zahlungsart Rechnung gewählt.</h3><br></br>
<h3>Sie erhalten die Rechnung demnächst per E-Mail.</h3><br></br>
<h3>Vielen Dank für ihren Einkauf.</h3><br></br>

</c:if>

<c:if test="${zahlung != 'Rechnung'}">

<h3>Sie haben als Zahlungsart Bankeinzug gewählt.</h3><br></br>
<h3>Der entsprechende Betrag wird innerhalb der nächsten 5 Tage von ihrem konto abgebucht.</h3><br></br>
<h3>Vielen Dank für ihren Einkauf.</h3><br></br>

</c:if>


<div style="float:right;">
	<a href='action?do=showmyarticles'><img alt="Weiter" src="images/weiter.png" border="0"/></a>
</div>

</body>
</html>