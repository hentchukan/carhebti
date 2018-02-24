<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}_FR" />
<fmt:setBundle basename="messages.labels" />

<fmt:message key="labels.listservices.title" var="title"/>
<t:mainTemplate page="listServices" title="${title }">
	
	<jsp:body>
		<c:if test="${not empty createdService}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listservices.addoperation.title" /></strong> 
				<fmt:message key="labels.listservices.addoperation.body">
					<fmt:param value="${createdService }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<c:if test="${not empty updatedService}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listservices.updateoperation.title"/> </strong>
				<fmt:message key="labels.listservices.updateoperation.body">
					<fmt:param value="${updatedService }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<c:if test="${not empty deletedService}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listservices.deleteoperation.title" /> </strong> 
				<fmt:message key="labels.listservices.deleteoperation.body">
					<fmt:param value="${deletedService }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<div id="content" class="container table-responsive" style="width: inherit;">
			<input type='hidden' id='current_page' />  
			<input type='hidden' id='show_per_page' /> 
			
			<table class="listService table table-bordered table-hover table-striped table-condensed" data-link="row">
				<thead>
					<tr>
						<td><fmt:message key="labels.listservices.id" /></td>
						<td><fmt:message key="labels.listservices.date" /></td>
						<td><fmt:message key="labels.listservices.type" /></td>
						<td hidden="true">Provider</td>
						<td hidden="true">comment</td>
						<td hidden="true">Klm</td>
						<td hidden="true">Qte</td>
						<td hidden="true">Cost</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${services}" var="service">
						<tr class="${newService == service.idService ? 'selectedRow' : 'none' } line pagination" data-href="clickable" data-toggle="modal" data-target="#serviceModal" data-whatever="${service.idService }">
							<td>${service.idService } </td>
							
							<td>
								<c:out value="${service.dateService.date}/${service.dateService.month}/${service.dateService.year}"></c:out>
							</td>
							
							<td>${service.type}</td>
							
							<td hidden="true"><input type="hidden" value="${service.providerService }"/></td>
							<td hidden="true"><input type="hidden" value="${service.commentService }"/> </td>
							<td hidden="true"><input type="hidden" value="${service.odometerService }"/> </td>
							<td hidden="true"><input type="hidden" value="${service.qteService }"/> </td>
							
							<td hidden="true"><input type="hidden" value="${service.type.providerColumnName }"/></td>
							<td hidden="true"><input type="hidden" value="${service.cost }"/></td>
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
		<div id="formService-div" class="container panel panel-default " style="width: inherit; display: none;" >
			<p>
			
			<form id="formService" class="form-horizontal" method="post" >
				
				<input type="hidden" id="formService-id" name="formService-id">
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formService-type"><fmt:message key="labels.listservices.type" /> :</label>
					<div class="col-sm-6">
						<select class="form-control" id="formService-type" name="formService-type">
								<c:forEach items="${types }" var="type">
									<option value="${type.id }" data-val='{"id":${type.id },"name":"${type.name }","odometer":${type.odometer},"qte":${type.qte},"providerName":"${type.providerColumnName}"}'>${type.name }</option>
								</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formService-date"><fmt:message key="labels.listservices.date" /> :</label>
					<div class="col-sm-6">
    					<fmt:formatDate value="${dateService}" pattern="dd/MM/yyyy" var="myDate" />
                        <input readonly="readonly" size="12" type="text" class="form-control datepicker" id="formService-date" name="formService-date" value="${myDate }"/>
					</div>
				</div>
				
				<div class="form-group" id="formService-providerblock">
					<label class="control-label col-sm-2" for="formService-provider" id="formService-providerType"></label>
					<div class="col-sm-6">
						<input type="text" data-validation="alphanumeric" data-validation-length="50" class="form-control" id="formService-provider" name="formService-provider">
					</div>
				</div>
				
				<div class="form-group" id="formService-quantityblock">
					<label class="control-label col-sm-2" for="formService-quantity"><fmt:message key="labels.listservices.quantity" /> :</label>
					<div class="col-sm-6">
						<input type="text" data-validation="number" data-validation-allowing="integer" data-validation-optional="true" class="form-control" id="formService-quantity" name="formService-quantity">
					</div>
				</div>
				
				<div class="form-group" id="formService-odometerblock">
					<label class="control-label col-sm-2" for="formService-odometer"><fmt:message key="labels.listservices.odometer" /> :</label>
					<div class="col-sm-6">
						<input type="text" data-validation="number" data-validation-optional="true" class="form-control" id="formService-odometer" name="formService-odometer">
					</div>
				</div>
				
				<div class="form-group" id="formService-costblock">
					<label class="control-label col-sm-2" for="formService-cost"><fmt:message key="labels.listservices.cost" /> :</label>
					<div class="col-sm-4">
						<input type="text" data-validation="number" data-validation-optional="true" class="form-control" id="formService-cost" name="formService-cost">
					</div>
					<label class="control-label col-sm-2" for="formService-cost"><fmt:message key="labels.listservices.currency" /></label>
				</div>
					
				<div class="form-group">
					<label class="control-label col-sm-2" for="formService-comment"><fmt:message key="labels.listservices.comment" /> :</label>
					<div class="col-sm-6">
						<textarea data-validation="alphanumeric" data-validation-length="300" data-validation-optional="true" class="form-control" id="formService-comment" name="formService-comment">
						</textarea>
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