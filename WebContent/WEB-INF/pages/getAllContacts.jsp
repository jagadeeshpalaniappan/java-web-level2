<%@ include file="./includes.jsp"%>

<html>
<head>
<title>All Contacts</title>
</head>
<body>

	<br><br><br><br>

	<table align="center" style="border: 1px solid #F2F2F2;">
		<tr>
			<th><spring:message code="label.domain.contacts.contactId" /></th>
			<th><spring:message code="label.domain.contacts.contactName" /></th>
			<th><spring:message code="label.domain.contacts.contactMobile" /></th>
			<th><spring:message code="label.domain.contacts.contactLandline" /></th>
			<th></th>
			<th></th>
		</tr>
		
	<c:forEach var="contactVO" items="${contactVOs}">	
		<tr>
		<td><c:out value="${contactVO.contactId}" /></td>
		<td><c:out value="${contactVO.contactName}" /></td>
		<td><c:out value="${contactVO.contactMobile}" /></td>
		<td><c:out value="${contactVO.contactLandline}" /></td>
		<td><a href="getContact.htm?contactId=<c:out value="${contactVO.contactId}" />">Edit</a></td>
		<td><a href="deleteContact.htm?contactId=<c:out value="${contactVO.contactId}" />" title="Delete" style="color: red;">Delete</a></td>
		</tr>
	</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td align="right" colspan="2"><a href="getContact.htm"><spring:message code="label.domain.contacts.addContacts" /></a></td>
		</tr>

	</table>

	
</body>

</html>