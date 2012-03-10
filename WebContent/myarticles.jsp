<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<head>
<link rel="stylesheet" type="text/css" href="shop.css" />
</head>


<h2>Bereits erworbene Artikel</h2><br>

<br>

<table width="96%" border="0" cellpadding="0" cellspacing="0">
	<colgroup>
	    <col width="70">
	    <col width="180">
	    <col >
	    <col width="70">
	    <col width="180">
	    <col width="85">
	 </colgroup>
		<tr>
			<td><b>Art</b></td>
			<td><b>Interpret</b></td>
			<td><b>Titel</b></td>
			<td><b>Veröffentlichung</b></td>
			<td><b>&nbsp;Label</b></td>
			<td><b>Download</b></td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>

		<c:forEach  var='item' items='${usercds}' varStatus='number'> 
	
			<tr class='hove' onclick="window.location.href = 'action?do=show&amp;cdid=${item.ean}'" style="cursor: pointer">
			  <td> Album </td>
			  <td> <c:out value='${item.interpret}'/> </td>
			  <td> <c:out value='${item.titel}'/> </td>
			  <td> <c:out value='${item.veroeffentlichung}'/> </td>
			  <td> <c:out value='${item.label}'/> </td>
			  <td> <a href='${item.file}'>Download</a>  </td>
			</tr>

		  
		</c:forEach>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		<c:forEach  var='item' items='${usersongs}' varStatus='number'> 
	
			<tr class='hove'">
			  <td><b>Track</b></td>
			  <td> <c:out value='${item.interpret}'/> </td>
			  <td> <c:out value='${item.titel}'/> </td>
			  <td> <a href='action?do=show&amp;cdid=${item.cdean}'>Zur CD</a> </td>
			  
			  <c:set var="linktoartist" scope="session" > /test/action?do=show&amp;search=${item.interpret} </c:set>
			  
			  <td> <a href='/test/action?do=show&search=${item.interpret}'>Zum Künstler</a> </td>
			  <td> <a href='${item.file}'>Download</a> </td>
			 </tr>
		 
  
		</c:forEach>
		
</table>

<%
//Session-Attribute löschen
session.setAttribute("fromKorb", "0");
session.setAttribute("fromShop", "0");
session.setAttribute("fromArticles", "1");

%>
