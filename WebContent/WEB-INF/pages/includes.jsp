
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List,java.util.ArrayList"%>  


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 
<%@page errorPage="../common/error.jsp" %>



<%
String pageContextPath=request.getContextPath();
String cssFolder = "midas";
%>


<script>
var context = '<%=pageContextPath%>';
</script>



<spring:htmlEscape defaultHtmlEscape="true" /> 