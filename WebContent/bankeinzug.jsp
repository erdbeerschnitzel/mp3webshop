<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<h3>Sie haben als Zahlungsart "Bankeinzug" gewählt und Ihre Kontodaten nicht oder unvollständig angegeben.</h3>

<br>
Bevor Sie den Kauf abschließen können müssen Sie Ihre Kontodaten vervollständigen.
<br>



<form method="POST" action="/test/action?do=buy&save=true">

			<c:if test="${zahlung == 'Bankeinzug'}">
		       	<input type="radio" name="Zahlmethode" value="Bankeinzug" checked> Bankeinzug 
		    </c:if>
		    
			<c:if test="${zahlung != 'Bankeinzug'}">
		       	<input type="radio" name="Zahlmethode" value="Bankeinzug"> Bankeinzug 
		    </c:if>
		    <br>
		    <br>
		    
			<table border="0" cellpadding="0" cellspacing="0">  
			<colgroup>
			    <col width="200">
			    <col width="130">
			    <col width="100">
			  </colgroup>

			<tr> 
			 <td>Kontoinhaber: </td><td> <input type="text" name="inhaber" size="15" value="${bankdaten[0]}" /></td>
			 
			 <td> 
			 	<c:set var='data' scope="session" >${fn:length(bankdaten[0])}</c:set>
			 
			 	<c:if test="${data > 5}">
			 	
			 		<img src='/test/images/haken.png' alt="Gültig" border=0/>
			 		
			 	</c:if>
			 	
			 	<c:if test="${data < 6}">
			 	
			 		<img src='/test/images/ausrufezeichen.png' alt="Ungültig" border=0/>
			 		
			 	</c:if>
			 </td>
			</tr>
			

			<tr> 
			 <td>Kontonummer: </td><td> <input type="text" name="kontonr" size="15" value="${bankdaten[1]}" /></td>

			 <td> 
			 	<c:set var='data' scope="session" >${fn:length(bankdaten[1])}</c:set>
			 
			 	<c:if test="${data > 5}">
			 	
			 		<img src='/test/images/haken.png' alt="Gültig" border=0/>
			 		
			 	</c:if>
			 	
			 	<c:if test="${data < 6}">
			 	
			 		<img src='/test/images/ausrufezeichen.png' alt="Ungültig" border=0/>
			 		
			 	</c:if>
			 </td>
			</tr>
	  
			<tr> 
			 <td>Bankleitzahl: </td><td> <input type="text" name="blz" size="15" value="${bankdaten[2]}" /></td>
			 
			 <td> 
			 	<c:set var='data' scope="session" >${fn:length(bankdaten[2])}</c:set>
			 
			 	<c:if test="${data > 9}">
			 	
			 		<img src='/test/images/haken.png' alt="Gültig" border=0/>
			 		
			 	</c:if>
			 	
			 	<c:if test="${data < 10}">
			 	
			 		<img src='/test/images/ausrufezeichen.png' alt="Ungültig" border=0/>
			 		
			 	</c:if>
			 </td>
			</tr>
			<tr> 
			 <td>Kreditinstitut: </td><td> <input type="text" name="bank" size="15" value="${bankdaten[3]}" /></td>
			 
			 <td> 
			 	<c:set var='data' scope="session" >${fn:length(bankdaten[3])}</c:set>
			 
			 	<c:if test="${data > 5}">
			 	
			 		<img src='/test/images/haken.png' alt="Gültig" border=0/>
			 		
			 	</c:if>
			 	
			 	<c:if test="${data < 6}">
			 	
			 		<img src='/test/images/ausrufezeichen.png' alt="Ungültig" border=0/>
			 		
			 	</c:if>
			 </td>
			</tr>
			</table>
	    
		<br>
		
		<c:if test="${zahlung != 'Bankeinzug'}">
			<input type="radio" name="Zahlmethode" value="Rechnung" checked> Rechnung<br>
	    </c:if>
	    
		<c:if test="${zahlung == 'Bankeinzug'}">
			<input type="radio" name="Zahlmethode" value="Rechnung"> Rechnung<br>
	    </c:if>    
	    
		<c:if test="${savebank == 'yes'}">
		
			<p><b>Änderungen gespeichert!</b></p>
		
		</c:if>

		<div align="left" style="position: relative; width:50%; left:110px;">  
		
			<input type="submit" value="Änderungen speichern" />
		
		</div>
		
		<br>
		<div style="float:right;">
			<a href='action?do=korb&id=11'><img alt="Zurück zum Warenkorb" src="images/zureuckzumwarenkorb.png" border="0"/></a>
		</div>

</form>
