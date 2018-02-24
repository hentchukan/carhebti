<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:mainTemplate page="listTypes" title="Types List">
	<%
		
	%>
	<jsp:body>
		<c:if test="${not empty param.newType}">
			<div class="alert alert-success">
				<strong>New Type added</strong>Type id ${param.newType } is successfully added to the list
			</div>
		</c:if>
		
		<div class="container">
			<p>Service Type List.
			
			<table class="table table-striped">
				<thead>
					<tr>
						<td>Id</td>
						<td>Type name</td>
						<td>Provider column name</td>
						<td>Need for Odometer</td>
						<td>Need for quantity</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${types}" var="type">
					
					</c:forEach>
				</tbody>
			</table>
		</div>
	</jsp:body>
</t:mainTemplate>