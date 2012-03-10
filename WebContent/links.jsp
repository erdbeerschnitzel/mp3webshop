<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ page import="java.util.*"%>

<%
	
	int warenkorbsize = 0;

	synchronized(session)
	{
		//falls Song oder CD im Warenkorb wird die Anzahl ausgelesen und in der linken Navi dargestellt
		if (session.getAttribute("Warenkorb") != null)
		{
	
			LinkedList<Long> wkorb = (LinkedList<Long>) session.getAttribute("Warenkorb");
			warenkorbsize = wkorb.size();
	
			if (session.getAttribute("SWarenkorb") != null)
			{
				
				LinkedList<Long> wkorbs = (LinkedList<Long>) session.getAttribute("SWarenkorb");
				warenkorbsize = warenkorbsize + wkorbs.size();
	
			}
			
		} else
		{
			
			if (session.getAttribute("SWarenkorb") != null)
			{
				
				LinkedList<Long> wkorbs = (LinkedList<Long>) session.getAttribute("SWarenkorb");
				warenkorbsize = wkorbs.size();
			}
		}
		
	}
	
	//Warenkorbgröße in Request packen
	request.setAttribute("wsize", warenkorbsize); 
%>



<link rel="stylesheet" type="text/css" href="links.css" />


<div class="inhalt"> 

<!-- nur wenn Nutzer eingeloggt ist -->
<c:if test="${username != anonym}">

	<br />
	<p><b>Hallo ${username}</b></p><br />

	<a href="action?do=logout"><img alt="Logout" src="images/logout.png" border="0"/></a>

	<br /><br />
	
	<!-- hier wird die Warenkorbgröße ausgegeben -->
	<b>Objekte im </b><a href="action?do=korb"><b>Warenkorb:</b></a> &nbsp; <%=warenkorbsize%> 
	
	<br />
	
	<h3>&nbsp;<a href="/test/action?do=profil&amp;id=12">Mein Account</a></h3>
	<ul>
	<li><a href="/test/action?do=profilchanges">Profildaten ändern</a></li>
	<li><a href="/test/action?do=showmyarticles">Meine MP3s</a></li>
	</ul>
	
</c:if>

<c:if test="${username == anonym}">
	
	<br />
	<form method="post" action="/test/action?do=login">
	&nbsp;Username: <input
		type="text" name="username" size="15" /><br />
	&nbsp;Password: <input type="password" name="passwort" size="15" /><br />

		<br />
	    <div id=login>


  			<input name="mlogin" type="image" src="images/login.png"/>
			<a href="action?do=register"><img alt="Registrieren" src="images/registrieren.png" border="0"/></a>
		</div>
	</form>


<c:if test="${wsize > 0}">	
	
	<br />
	<b>&nbsp;Objekte im <a href="action?do=korb&amp;id=11"><strong>Warenkorb:</strong></a>&nbsp;</b><%=warenkorbsize%>
	<br />
	
</c:if>

</c:if>

<!-- Navigation -->
	<br /><br />
	
	<form action="action?do=show&"  method="get">
	<input type="hidden" name="do" value="show"/>
	
	<fieldset>
      <legend>Suche</legend>
      
      <input type="text" name="suchbegriff" value="" />
      
	      <select name="suchoption">
	      
	       <option value="0" selected="selected">Musik</option>
	       <option value="1">Band/Künstler</option>
	       <option value="2">Album/EP/Single</option>
	       <option value="3">Song</option>
	       
	      </select>
	      
      <input type="image" src="images/go.png" alt="Go!"/>
     </fieldset>
	
	
	</form>
	
	
<br />

<h3>&nbsp;Musik-Shop</h3>
<ul>
<li><a href="/test/action?do=show&amp;shownew=1">Neuheiten</a></li>
<li><a href="/test/action?do=show&amp;showgenre=Rock">Rock</a></li>
<li><a href="/test/action?do=show&amp;showgenre=Pop">Pop</a></li>
<li><a href="/test/action?do=show&amp;showgenre=Elektro">Elektro</a></li>
<li><a href="/test/action?do=show&amp;showgenre=House">House</a></li>
<li><a href="/test/action?do=show&amp;showgenre=HipHop">Hip Hop</a></li>
<li><a href="/test/action?do=show&amp;showgenre=Diverse">Diverse</a></li>
<li><a href="/test/action?do=show&amp;showall=1">Komplettübersicht</a></li>
</ul>

</div>
