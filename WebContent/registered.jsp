<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<br><br>
<p><b>Neues Konto erfolgreich erstellt...</b></p>
<br><br>


<table border="0" cellpadding="0" cellspacing="0">  
<colgroup>
    <col width="350">
    <col width="350">
</colgroup>

<tr>
 <td>Kontoname: </td><td>${user.accountname}</td>
</tr>
<tr>
 <td>E-Mail: </td><td>${user.email}</td>
</tr>
<tr>
 <td>Name: </td><td>${user.lastname}</td>
</tr>
<tr>
 <td>Vorname: </td><td>${user.name}</td>
</tr>
<tr>
 <td>Adresse: </td><td>${user.land}, ${user.plz}, ${user.stadt}, ${user.strasse}, ${user.hausnummer}</td>
</tr>

</table>

<c:if test="${user.zahlungsart == 'Bankeinzug'}">

	<br>
	Da Sie als Zahlungsart den Bankeinzug gewählt haben benötigen wir zusätzlich Ihre Kontodaten.<br>
	Diese können Sie <a href="/test/action?do=profilchanges">hier</a> eintragen.
	Danach können Sie Ihren Account vollständig nutzen.
</c:if>

<c:if test="${user.zahlungsart != 'Bankeinzug'}">
<br>
<p><b>Sie können nun Ihren Account nutzen.</b></p>
</c:if>