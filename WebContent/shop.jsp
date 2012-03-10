<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<link rel="stylesheet" type="text/css" href="shop.css" />



<div id="shopheader">

   <jsp:include page="shopheader.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</div>

<div id="shopcontent" style="">


<c:if test="${CDID == 0}">

   <jsp:include page="shopcontent.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>
   


</c:if>

<c:if test="${CDID > 0}">

   <jsp:include page="detail.jsp">
       <jsp:param name="extraparam" value="myvalue"/>
   </jsp:include>

</c:if>

</div>

