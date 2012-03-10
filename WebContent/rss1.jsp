<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

    

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="main.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>RSS</title>
</head>
<body>

<h2>Die neusten Artikel auf mp3dealer.de</h2>
<br/>

<table border="0" cellpadding="0" cellspacing="0">  
<colgroup>
    <col width="180"/>
    <col width="180"/>
    <col width="70"/>
    <col width="70"/>
</colgroup>

	<tr bgcolor="black">
		<td><span style="font-weight:bold; color:white">Artist</span></td>
		<td><span style="font-weight:bold; color:white">Titel</span></td>
		<td><span style="font-weight:bold; color:white">Preis</span></td>
		<td><span style="font-weight:bold; color:white">Link</span></td>
	</tr>	


	<c:forEach var='cd' items='${list}'>

	<tr>
		<td>${cd.interpret}</td>
		<td>${cd.titel}</td>
		<td>${cd.preis} €</td>
		
		<td><a href="/test/action?do=show&amp;cdid=${cd.ean}">Details</a></td>
	</tr>	
	
	

	</c:forEach>

</table>

</body>
</html>
