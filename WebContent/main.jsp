<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
<head>
<title>~</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="MP3 Portal " />
<meta name="keywords" content="mp3, download, Musik, music, Musikdownload, Musikdownloads, Songs, MP3-Player" />
<meta name="author" content="mp3dealer.de" />
<meta name="copyright" content="(c) 2008 mp3dealer.de" />
<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate" />
<meta http-equiv="Cache-Control" content="post-check=0, pre-check=0" />
<meta http-equiv="Pragma" content="no-cache" />

<link rel="stylesheet" type="text/css" href="main.css" />

<style type="text/css">

/* prepares the background image to full capacity of the viewing area */
#bg {position:fixed; top:0; left:0; width:100%; height:100%;}

/* places the content ontop of the background image */
</style>
<!--[if IE 6]>
<style type="text/css">

/* some css fixes for IE browsers */
html {overflow-y:hidden;}
body {overflow-y:auto;}
#bg {position:absolute; z-index:-1;}
</style>
<![endif]-->

</head>

<body>
<div id="nonFooter">
<div id="bg"><img src="images/background.jpg" width="100%" height="100%" alt=""/></div>



<!--  header DIV -->
<div id="oben">

   <jsp:include page="oben.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</div>

<!-- linkes DIV -->
<div id="links">

	
   <jsp:include page="links.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</div>


<!-- mittleres DIV - main content -->
<div id="mitte">


<c:if test="${mainID == 1}">

   <jsp:include page="news.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 2}">

   <jsp:include page="login.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 3}">

   <jsp:include page="loginerror.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 4}">

   <jsp:include page="shop.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</c:if>


<c:if test="${mainID == 6}">

   <jsp:include page="faq.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 7}">

   <jsp:include page="detail.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 8}">

   <jsp:include page="register.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 10}">

   <jsp:include page="registered.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 11}">

   <jsp:include page="warenkorb.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 12}">

   <jsp:include page="profil.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 14}">

   <jsp:include page="impressum.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 15}">

   <jsp:include page="agb.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 16}">

   <jsp:include page="changeprofil.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 17}">

   <jsp:include page="myarticles.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 18}">

   <jsp:include page="bankeinzug.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>

<c:if test="${mainID == 19}">

   <jsp:include page="bill.jsp">
       <jsp:param name="extraparam" value="cdid"/>
   </jsp:include>

</c:if>
</div>

</div>


	







</body>
</html>
