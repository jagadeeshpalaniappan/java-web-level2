<%@ include file="./includes.jsp"%>

<html>
<head>
<title>Create Contact</title>
</head>
<body>

		<br><br><br><br>
		
	<form id="createContactForm" method="post" action="./saveContact.htm">
		<table align="center" style="border: 1px solid #F2F2F2;">
		<tr><td><a href="./getAllContacts.htm">&lt Back</a></td></tr>
		<tr>
		<td><spring:message code="label.domain.contacts.contactId" /></td>
		<td><input type="text" name="contactId" id="contactId" value="<c:out value="${contactVO.contactId}" />" readonly="readonly"/></td>
		</tr>
		<tr>
		<td><spring:message code="label.domain.contacts.contactName" /></td>
		<td><input type="text" name="contactName" id="contactName" value="<c:out value="${contactVO.contactName}" />"/></td>
		</tr>
		<tr>
		<td><spring:message code="label.domain.contacts.contactMobile" /></td>
		<td><input type="text" name="contactMobile" id="contactMobile" value="<c:out value="${contactVO.contactMobile}" />"/></td>
		</tr>
		
		<td><spring:message code="label.domain.contacts.contactLandline" /></td>
		<td><input type="text" name="contactLandline" id="contactLandline" value="<c:out value="${contactVO.contactLandline}" />"/></td>
		</tr>
		
		<tr>
			<td></td>
			<td><input type="submit" value=<spring:message code="label.domain.contacts.save" /> name="createContactFormSubmit" id="createContactFormSubmit" /></td>
		</tr>
		</table>
	</form>

	
</body>

</html>