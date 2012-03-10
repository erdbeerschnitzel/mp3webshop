<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<link rel="stylesheet" type="text/css" href="detail.css" />


<div id="detailinfo">
	
	<br>
	&nbsp; <img src=${CD.cover} alt="Cover" border=0/>
	
	

	<h2>&nbsp; ${CD.titel}</h2>
	
	<ul>
		<c:set var="genrelink" scope="session" > /test/action?do=show&showgenre=${CD.genre} </c:set>
		<li>Genre: <a href=${genrelink}>${CD.genre}</a></li>
		<li>Format: MP3, 192 kbps</li>
		<li>Veröffentlichung: ${CD.veroeffentlichung}</li>
		<li>EAN: ${CD.ean}</li>
		<li>Label: ${CD.label}</li>
		<li>Preis:  <span style="color: #555;">${CD.preis} €</span></li>
	
	</ul>


	
	<c:if test="${fromShop == 1}">
	

		&nbsp; <a href='action?do=show&buyid=${CD.ean}'><img alt="In den Warenkorb" src="images/indenwarenkorb.png" border="0"/></a>
	
		<br>
	
		<c:if test="${fromAll == 1}">
		
			&nbsp; <a href='action?do=show&showall=1'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>
		
		<c:if test="${fromNew == 1}">

			&nbsp; <a href='action?do=show&shownew=1'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>
		
		<c:if test="${genr == 'Rock'}">
		
			&nbsp; <a href='action?do=show&showgenre=Rock'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>
		
		<c:if test="${genr == 'Pop'}">
		
			&nbsp; <a href='action?do=show&showgenre=Pop'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>
		
		<c:if test="${genr == 'Elektro'}">
		
			&nbsp; <a href='action?do=show&showgenre=Elektro'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>
		
		<c:if test="${genr == 'HipHop'}">
		
			&nbsp; <a href='action?do=show&showgenre=HipHop'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>
		
		<c:if test="${genr == 'House'}">
		
			&nbsp; <a href='action?do=show&showgenre=House'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>

		<c:if test="${genr == 'House'}">
		
			&nbsp; <a href='action?do=show&showgenre=House'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
		
		</c:if>
	
	</c:if>
	
	<c:if test="${fromArticles == 1}">
	
		<a href='action?do=showmyarticles'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
	
	</c:if>	
	
	
	
	<c:if test="${fromKorb == 1}">
	
		<a href='action?do=korb&id=11'><img alt="Zurück" src="images/zurueck.png" border="0"/></a> 
	
	</c:if>

</div>




<div id="detailtracks">

	Anzahl Songs auf dieser CD: ${CD.anzahl }<p></p>
	
	<table width="99%" border="0" cellpadding="0" cellspacing="0">
	
	
		<tr bgcolor="black">
			<td><span style="font-weight:bold; color:white">#</span></td>
			<td><span style="font-weight:bold; color:white">Titel</span></td>
			<td><span style="font-weight:bold; color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Format</span></td>
			<td><span style="font-weight:bold; color:white">Genre</span></td>
			<td><span style="font-weight:bold; color:white">Länge</span></td>
			<td><span style="font-weight:bold; color:white">Preis</span></td>
		</tr>
	
	
	
	
		<c:forEach var='song' items='${CD.songs}' varStatus='status'>
		
			<tr class='hove'>
				<td>${song.nr}</td>
				<td>${song.titel}</td>
 				<td>
 				 	<c:set var="link" scope="session" > action?do=show&buysid=${song.id}&cdid=${CD.ean} </c:set>
 				 	<c:set var="number" scope="session" > ${status.count} </c:set>
 				 	<c:set var="sample" scope="session" >${song.sample}</c:set>
 				 	
 				 	<% session.setAttribute("sample" + session.getAttribute("number").toString(), "\"src=http://unwichtich.doesntexist.com:8080" + session.getAttribute("sample").toString() + "&amp;autoload=yes&amp;autostart=yes\""); %>
					
					
 					<a href='action?do=show&buysid=${song.id}&cdid=${CD.ean}' ><img src="images/warenkorb.png" alt="kaufen" border="0" /></a>&nbsp;
 					
 					<!-- only a sample sample -->
 					<a href=javascript:void(window.open('sample-jsps/sample${status.count}.jsp','Sample','width=200,height=100,resizable=no'));><img src="images/lautsprecher.png" alt="play" border="0" /></a>&nbsp;&nbsp;&nbsp;&nbsp;MP3</td>
					
 					
 				<td>${CD.genre}</td>
 				<td>${song.laenge} min</td>
 				<td>${song.preis} € </td>
 			</tr>
 			
		</c:forEach>
		
	</table>
	
</div>
<% session.setAttribute("CDID", ""); %>
