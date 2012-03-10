<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:if test="${shownew == 1}">

	<table width="99%" border="0" cellpadding="0" cellspacing="0">
	
		<tr class='hove' onclick="window.location.href = 'action?do=show&amp;cdid=${item.ean}'" style="cursor: pointer">
			<td><b>Art</b></td>
			<td><b>Interpret</b></td>
			<td><b>Titel</b></td>
			<td><b>Genre</b></td>
			<td><b>Veröffentlichung</b></td>
			<td><b>Preis</b></td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
	
		<c:forEach  var='item' items='${cdliste}' varStatus='number'> 
	
		<c:if test="${item.veroeffentlichung == 2008}">
		
			<tr class='hove' onclick="window.location.href = 'action?do=show&amp;cdid=${item.ean}'" style="cursor: pointer">
			  <td> Album </td>
			  <td> <c:out value='${item.interpret}'/> </td>
			  <td> <c:out value='${item.titel}'/> </td>
			  <td> <c:out value='${item.genre}'/></td>
			  <td> <c:out value='${item.veroeffentlichung}'/> </td>
			  <td> <c:out value='${item.preis}'/> €</td>
			  <td> <a href='action?do=show&amp;buyid=${item.ean}'><img alt=" In den Warenkorb" src="images/warenkorb.png" border="0"/></a> </td>
			 </tr>
		 
		 </c:if>
		  
		</c:forEach>
		
	</table>

</c:if>

<c:if test="${shownew == 0}">

<c:if test="${nothing != 'yes'}">

	<table width="99%" border="0" cellpadding="0" cellspacing="0">
	
		<tr>
			<td><b>Art</b></td>
			<td><b>Interpret</b></td>
			<td><b>Titel</b></td>
			<td><b>Genre</b></td>
			<td><b>Veröffentlichung</b></td>
			<td><b>Preis</b></td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
	
		<c:forEach  var='item' items='${cdliste}' varStatus='number'> 
		
			
			<tr class='hove' onclick="window.location.href = 'action?do=show&amp;cdid=${item.ean}'" style="cursor: pointer">

			  <td>Album</td>
			  <td> <c:out value='${item.interpret}'/> </td>
			  <td> <c:out value='${item.titel}'/> </td>
			  <td> <c:out value='${item.genre}'/></td>
			  <td> <c:out value='${item.veroeffentlichung}'/> </td>
			  <td> <c:out value='${item.preis}'/> €</td>
			  <td> <a href='action?do=show&amp;buyid=${item.ean}'><img alt="In den Warenkorb" src="images/warenkorb.png" border="0"/></a> </td>

			 </tr>
		 
  
		</c:forEach>
		
		<c:forEach  var='item' items='${songliste}' varStatus='number'> 
	
			<tr class='hove'">
			  <td><b>Track</b></td>
			  <td> <c:out value='${item.interpret}'/> </td>
			  <td> <c:out value='${item.titel}'/> </td>

			  <td> <a href='action?do=show&amp;cdid=${item.cdean}'>Zur CD</a> </td>
			  
			  <c:set var="linktoartist" scope="session" > /test/action?do=show&amp;search=${item.interpret} </c:set>
			  
			  <td> <a href='/test/action?do=show&amp;search=${item.interpret}'>Zum Künstler</a> </td>

			  <td> <c:out value='${item.preis}'/> €</td>
			  <td>  				 	
			  
			  		<c:set var="link" scope="session" > action?do=show&amp;buysid=${item.id}&cdid=${item.cdean} </c:set>
 					<a href='${link}' ><img src="images/warenkorb.png" alt="kaufen" border="0" /></a> </td>
			 </tr>
		 
  
		</c:forEach>
		
	</table>
	
</c:if>
</c:if>

<c:if test="${nothing == 'yes'}">

	<h2>Leider wurden keine passenden Einträge gefunden...</h2>

</c:if>


<%

session.setAttribute("fromKorb", "0");
session.setAttribute("fromShop", "1");
session.setAttribute("fromArticles", "0");

%>



