<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<head>
<link rel="stylesheet" type="text/css" href="detail.css" />
</head>


<div id ="warenkorb"  style="position:relative; width:97%; height:99%; border: 0px solid white;" >
<b>Inhalt des Warenkorbs: </b><br></br><br>
<table width="97%" border="0" cellpadding="0" cellspacing="0">

<tr>
<td><b>Artikelart</b></td><td><b>Artikel</b></td><td><b>Interpret</b></td><td><b>Preis</b></td></tr>
<tr>
<td>&nbsp;</td><td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td><td>&nbsp;</td> <td>&nbsp;</td> 
</tr>

<c:forEach  var='item' items='${wkcontent}' varStatus='number'> 


<tr class='hove'>
  <td> Album</td>
  <td> <c:out value='${item.titel}'/></td>
  <td> <c:out value='${item.interpret}'/></td>
  <td> <c:out value='${item.preis}'/> € </td>
  <td> <a href='action?do=show&amp;cdid=${item.ean}'><img alt="Details" src="images/details.png" border="0"/></a> </td>
  <td> <a href='action?do=korb&amp;id=11&amp;removeid=${item.ean}'><img alt="Artikel entfernen" src="images/artikelentfernen.png" border="0"/></a> </td>
 </tr>
  
  
</c:forEach> 

<tr>
<td>&nbsp;</td><td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td>
</tr>


<c:forEach  var='item' items='${wkscontent}' varStatus='number'> 


<tr class='hove'>


  <td> Song</td>
  <td> <c:out value='${item.titel}'/></td>
  <td> <c:out value='${item.interpret}'/> </td>
  <td> <c:out value='${item.preis}'/> € </td>
  <td> &nbsp; </td>
  <td><a href='action?do=korb&amp;id=11&amp;removesid=${item.id}'><img alt="Artikel entfernen" src="images/artikelentfernen.png" border="0"/></a></td>
</tr>
  
  
</c:forEach> 

<tr>
<td>&nbsp;</td><td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td>
</tr>

<tr>
<td>Gesamtsumme *</td><td>&nbsp;</td><td>&nbsp;</td> <td>${PRICE} €</td>
</tr>

</table>

<br>
* Die Summe versteht sich als Endpreis inklusive gesetzlicher Mehrwertsteuer und sonstiger Preisbestandteile.

<c:if test="${notloggedin == 'yes'}">

<br><br><h2>Sie sind nicht angemeldet. Bitte einloggen oder <a href="action?do=register">ein neues Konto erstellen</a>.</h2><br><br>

</c:if>

<br>
<c:if test="${notloggedin != 'yes'}">

	<div style="float:right;">
		<a href='action?do=buy'><img alt="Artikel kaufen" src="images/artikelkaufen.png" border="0"/></a>
	</div>
</c:if>
</div>




<%

session.setAttribute("fromKorb", "1");
session.setAttribute("fromShop", "0");
session.setAttribute("fromArticles", "0");

%>
