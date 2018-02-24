<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages.labels" />

<fmt:message key="labels.listtypes.title" var="title"/>
<t:mainTemplate page="listTypes" title="${title }">
	
	<jsp:body>
		<c:if test="${not empty createdType}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listtypes.addtype.title"/> </strong>
				<fmt:message key="labels.listtypes.addtype.body">
					<fmt:param value="${createdType }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<c:if test="${not empty updatedType}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listtypes.updatetype.title"/> </strong>
				<fmt:message key="labels.listtypes.updatetype.body">
					<fmt:param value="${updatedType }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<c:if test="${not empty deletedType}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listtypes.deletettype.title" /> </strong> 
				<fmt:message key="labels.listtypes.deletetype.body">
					<fmt:param value="${deletedType }"/>
				</fmt:message>
			</div>
		</c:if>
		
		
		<div id="content" class="container table-responsive" style="width: inherit;">
			<input type='hidden' id='current_page' />  
			<input type='hidden' id='show_per_page' /> 
							
			<table class="listType table table-bordered table-hover table-striped table-condensed" data-link="row">
				<thead>
					<tr>
						<td hidden="true"><fmt:message key="labels.listtypes.id" /></td>
						<td><fmt:message key="labels.listtypes.type" /></td>
						<td><fmt:message key="labels.listtypes.provider" /></td>
						<td><fmt:message key="labels.listtypes.odometer" /></td>
						<td><fmt:message key="labels.listtypes.quantity" /></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${types}" var="type">
						<tr class="${newType == type.id ? 'selectedRow' : 'none' } line pagination" data-href="clickable">
							<td hidden="true"><input type="hidden" value="${type.id }"/> </td>
							
							<td>${type.name }</td>
							
							<td>${type.providerColumnName }</td>
							
							<td><input type="checkbox" disabled="disabled" ${type.odometerable ? "checked" : ""}></td>
							
							<td><input type="checkbox" disabled="disabled" ${type.quantifiable ? "checked" : ""}></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!-- An empty div which will be populated using jQuery -->  
		<p>
		<div id="page_navigation" align="center">
		</div>
		<br/>
		
		<p>
		<div class="btn-group btn-group-justified">
			
			<div class="btn-group" style="padding-right: 4px">
				<button type="button" id="btn-add" class="btn btn-default">
					<span class="glyphicon glyphicon-plus"></span> <fmt:message key="labels.add"/>
				</button>
			</div>
			
			<div class="btn-group">
				<button type="button" id="btn-save" class="btn btn-default disabled">
					<span class="glyphicon glyphicon-save"></span> <fmt:message key="labels.save"/>
				</button>
			</div>
			
			<div class="btn-group" style="padding-left: 4px">
				<button type="button" id="btn-delete" class="btn btn-default disabled">
					<span class="glyphicon glyphicon-remove"></span> <fmt:message key="labels.delete"/>
				</button>
			</div>
		</div>
		
		<p>
		<div id="formType-div" class="container panel panel-default " style="width: inherit; display: none;" >
			<p>
			
			<form id="formType" class="form-horizontal" method="post" >
				
				<input type="hidden" id="formType-id" name="formType-id">
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formType-name"><fmt:message key="labels.listtypes.type" /> :</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="formType-name"
								name="formType-name" placeholder="Enter type name">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formType-provider"><fmt:message key="labels.listtypes.provider" />:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="formType-provider"
								name="formType-provider" placeholder="Enter provider type name">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2 col-md-offset-2">
						<div class="checkbox">
							<label><input type="checkbox" name="formType-odometer"><b><fmt:message key="labels.listtypes.odometer" /></b></label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 col-md-offset-2">
						<div class="checkbox">
							<label><input type="checkbox" name="formType-quantity"><b><fmt:message key="labels.listtypes.quantity" /></b></label>
						</div>
					</div>
				</div>	
					
				<div class="form-group">
					<div id="msg-error"
						class="col-sm-offset-2 col-sm-8 alert alert-danger collapse"
						role="alert">
					</div>
				</div>
			</form>
		</div>
		
	</jsp:body>
</t:mainTemplate>