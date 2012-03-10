<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<!-- header of shop content div -> navi -->

<c:if test="${showall == 1}">

	<c:if test="${letter != 'none'}">
		
		<% session.setAttribute("letterlink", "/test/action?do=show&amp;showall=1&amp;showletter=" + session.getAttribute("letter").toString()); %>
		<b>&nbsp; <a href="/test/action?do=main&amp;id=4&amp;showall=1">Musik</a> &gt; <a href="/test/action?do=show&amp;showall=1">Komplettübersicht</a> &gt; <a href=${letterlink}>${letter}</a> </b><p></p>
	
	</c:if>

	<c:if test="${letter == 'none'}">
		
		<b>&nbsp; <a href="/test/action?do=show&amp;showall=1">Musik</a> &gt; <a href="/test/action?do=show&amp;showall=1">Komplettübersicht</a> </b><p></p>
	
	</c:if>	
	
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=a"><img alt="A" src="images/a.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=b"><img alt="B" src="images/b.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=c"><img alt="C" src="images/c.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=d"><img alt="D" src="images/d.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=e"><img alt="E" src="images/e.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=f"><img alt="F" src="images/f.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=g"><img alt="G" src="images/g.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=h"><img alt="H" src="images/h.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=i"><img alt="I" src="images/i.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=j"><img alt="J" src="images/j.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=k"><img alt="K" src="images/k.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=l"><img alt="L" src="images/l.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=m"><img alt="M" src="images/m.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=n"><img alt="N" src="images/n.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=o"><img alt="O" src="images/o.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=p"><img alt="P" src="images/p.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=q"><img alt="Q" src="images/q.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=r"><img alt="R" src="images/r.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=s"><img alt="S" src="images/s.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=t"><img alt="T" src="images/t.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=u"><img alt="U" src="images/u.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=v"><img alt="V" src="images/v.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=w"><img alt="W" src="images/w.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=x"><img alt="X" src="images/x.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=y"><img alt="Y" src="images/y.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=z"><img alt="Z" src="images/z.png" border="0"/></a>
	<a href="/test/action?do=show&amp;showall=1&amp;showletter=sonstige"><img alt="Sonstige" src="images/sonstige.png" border="0"/></a>
	
</c:if>


<c:if test="${shownew == 1}">

	<c:if test="${letter != 'none'}">
		
		<% session.setAttribute("letterlink", "/test/action?do=show&amp;shownew=1&amp;showletter=" + session.getAttribute("letter").toString()); %>
		<b>&nbsp; <a href="/test/action?do=show&amp;showall=1">Musik</a> &gt; <a href="/test/action?do=show&amp;shownew=1">Neuheiten</a> &gt; <a href=${letterlink}>${letter}</a> </b><p></p>
	
	</c:if>

	<c:if test="${letter == 'none'}">
		
		<b>&nbsp; <a href="/test/action?do=show&amp;shownew=1">Musik</a> &gt; <a href="/test/action?do=show&amp;showall=1">Neuheiten</a> </b><p></p>
	
	</c:if>	

	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=a"><img alt="A" src="images/a.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=b"><img alt="B" src="images/b.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=c"><img alt="C" src="images/c.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=d"><img alt="D" src="images/d.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=e"><img alt="E" src="images/e.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=f"><img alt="F" src="images/f.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=g"><img alt="G" src="images/g.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=h"><img alt="H" src="images/h.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=i"><img alt="I" src="images/i.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=j"><img alt="J" src="images/j.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=k"><img alt="K" src="images/k.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=l"><img alt="L" src="images/l.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=m"><img alt="M" src="images/m.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=n"><img alt="N" src="images/n.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=o"><img alt="O" src="images/o.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=p"><img alt="P" src="images/p.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=q"><img alt="Q" src="images/q.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=r"><img alt="R" src="images/r.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=s"><img alt="S" src="images/s.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=t"><img alt="T" src="images/t.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=u"><img alt="U" src="images/u.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=v"><img alt="V" src="images/v.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=w"><img alt="W" src="images/w.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=x"><img alt="X" src="images/x.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=y"><img alt="Y" src="images/y.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=z"><img alt="Z" src="images/z.png" border="0"/></a>
	<a href="/test/action?do=show&amp;shownew=1&amp;showletter=sonstige"><img alt="Sonstige" src="images/sonstige.png" border="0"/></a>
	
</c:if>

<c:if test="${showgenre == 1}">

	<c:if test="${letter != 'none'}">
		
		<c:set var="letterlink" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=${letter} </c:set>
		<b>&nbsp; <a href="/test/action?do=show&amp;showall=1">Musik</a> &gt; <a href="/test/action?do=show&amp;showgenre=${genr}">${genr}</a> &gt; <a href=${letterlink}>${letter}</a> </b><p></p>
	
	</c:if>

	<c:if test="${letter == 'none'}">
		
		<b>&nbsp; <a href="/test/action?do=show&amp;showall=1">Musik</a> &gt; <a href="/test/action?do=show&amp;showgenre=${genr}">${genr}</a> </b><p></p>
	
	</c:if>	

	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=a </c:set>
	<a href=${link}><img alt="A" src="images/a.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=b </c:set>
	<a href=${link}><img alt="B" src="images/b.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=c </c:set>
	<a href=${link}><img alt="C" src="images/c.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=d </c:set>
	<a href=${link}><img alt="D" src="images/d.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=e </c:set>
	<a href=${link}><img alt="E" src="images/e.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=f </c:set>
	<a href=${link}><img alt="F" src="images/f.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=g </c:set>
	<a href=${link}><img alt="G" src="images/g.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=h </c:set>
	<a href=${link}><img alt="H" src="images/h.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=i </c:set>
	<a href=${link}><img alt="I" src="images/i.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=j </c:set>
	<a href=${link}><img alt="J" src="images/j.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=k </c:set>
	<a href=${link}><img alt="K" src="images/k.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=l </c:set>
	<a href=${link}><img alt="L" src="images/l.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=m </c:set>
	<a href=${link}><img alt="M" src="images/m.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=n </c:set>
	<a href=${link}><img alt="N" src="images/n.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=o </c:set>
	<a href=${link}><img alt="O" src="images/o.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=p </c:set>
	<a href=${link}><img alt="P" src="images/p.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=q </c:set>
	<a href=${link}><img alt="Q" src="images/q.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=r </c:set>
	<a href=${link}><img alt="R" src="images/r.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=s </c:set>
	<a href=${link}><img alt="S" src="images/s.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=t </c:set>
	<a href=${link}><img alt="T" src="images/t.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=u </c:set>
	<a href=${link}><img alt="U" src="images/u.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=v </c:set>
	<a href=${link}><img alt="V" src="images/v.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=w </c:set>
	<a href=${link}><img alt="W" src="images/w.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=x </c:set>
	<a href=${link}><img alt="X" src="images/x.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=y </c:set>
	<a href=${link}><img alt="Y" src="images/y.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=z </c:set>
	<a href=${link}><img alt="Z" src="images/z.png" border="0"/></a>
	<c:set var="link" scope="session" > /test/action?do=show&amp;showgenre=${genr}&amp;showletter=sonstige </c:set>
	<a href=${link}><img alt="Sonstige" src="images/sonstige.png" border="0"/></a>
	
</c:if>

<c:if test="${DETAIL == 1}">

	<b>&nbsp; <a href="/test/action?do=show&amp;showall=1">Musik</a> &gt; <a href=/test/action?do=show&amp;showgenre=${CD.genre}>${CD.genre}</a> &gt; <a href=/test/action?do=show&amp;search=${linkartist}>${CD.interpret}</a> &gt; ${CD.titel} </b><p></p>
	
</c:if>

<c:if test="${SEARCH == 1}">

	<b>&nbsp; <a href="/test/action?do=show&amp;showall=1">Musik</a> &gt; ${searched} </b><p></p>
	
</c:if>

<c:if test="${SEARCH2 == 1}">

	<b>&nbsp; <a href="/test/action?do=show&amp;showall=1">Musik</a> &gt; Suche &gt; ${searchedthat} </b><p></p>
	
</c:if>
