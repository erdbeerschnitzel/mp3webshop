<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<c:if test="${timedout != 'yes'}">

	<table border="0" cellpadding="0" cellspacing="0">
	
		<colgroup>
		    <col width="130">
		    <col width="420">
		</colgroup>
		
		<tr>
			<td>Profilname: </td><td>${userdaten.accountname}</td> 
		</tr>
		<tr>
			<td>Name: </td><td>${userdaten.lastname}</td>
		</tr>
		<tr>
			<td>Vorname: </td><td>${userdaten.name}</td>
		</tr>
		<tr>
			<td>Wohnanschrift: </td><td>${userdaten.strasse} ${userdaten.hausnummer}, ${userdaten.plz} ${userdaten.stadt}, ${userdaten.land}</td>
		</tr>
		<tr>
			<td>Zahlungsart: </td><td>${userdaten.zahlungsart}</td>
		</tr>
		<tr>
			<td>&nbsp;</td><td>&nbsp;</td>
		</tr>
		<tr>
			<td>gekaufte Artikel: </td><td>${complete}</td>
		</tr>
		<tr>
			<td>gekaufte Alben: </td><td>${cdsbought}</td>
		</tr>
		<tr>
			<td>gekaufte Songs: </td><td>${songsbought}</td>
		</tr>
			
	</table>
	
</c:if>

<c:if test="${timedout == 'yes'}">

	<p><b>Sie waren zu lange inaktiv. Zu ihrer eigenen Sicherheit müssen sie sich neu einloggen.</b></p>
	
</c:if>