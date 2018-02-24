<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:mainTemplate page="listServices" title="Manage operations">
	
	<jsp:body>
		<c:if test="${not empty createdService}">
			<div class="alert alert-success">
				<strong>New operation added</strong> Operation id ${createdService } is successfully added to the list
			</div>
		</c:if>
		
		<c:if test="${not empty updatedService}">
			<div class="alert alert-success">
				<strong>Operation updated</strong> Operation id ${updatedService } is successfully updated
			</div>
		</c:if>
		
		<c:if test="${not empty deletedService}">
			<div class="alert alert-success">
				<strong>Operation deleted</strong> Operation id ${deletedService } successfully deleted from the list
			</div>
		</c:if>
		
		<div class="container table-responsive" style="width: inherit;">
			<table class="listService table table-striped table-condensed" data-link="row">
				<thead>
					<tr>
						<td>Id</td>
						<td>Date</td>
						<td>Type</td>
						<td hidden="true">Provider</td>
						<td hidden="true">comment</td>
						<td hidden="true">Klm</td>
						<td hidden="true">Qte</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${services}" var="service">
						<tr class="${newService == service.idService ? 'selectedRow' : 'none' }" data-href="clickable" data-toggle="modal" data-target="#serviceModal" data-whatever="${service.idService }">
							<td>${service.idService } </td>
							
							<td>
								<fmt:formatDate value="${service.dateService}" pattern="dd/MM/yyyy"/>
							</td>
							
							<td>${service.type}</td>
							
							<td hidden="true"><input type="hidden" value="${service.providerService }"/></td>
							<td hidden="true"><input type="hidden" value="${service.commentService }"/> </td>
							<td hidden="true"><input type="hidden" value="${service.odometerService }"/> </td>
							<td hidden="true"><input type="hidden" value="${service.qteService }"/> </td>
							
							<td hidden="true"><input type="hidden" value="${service.type.providerColumnName }"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<p>
		<div class="btn-group btn-group-justified">
			
			<div class="btn-group" style="padding-right: 4px">
				<button type="button" id="btn-add" class="btn btn-default">
					<span class="glyphicon glyphicon-plus"></span> Add
				</button>
			</div>
			
			<div class="btn-group">
				<button type="button" id="btn-save" class="btn btn-default disabled">
					<span class="glyphicon glyphicon-save"></span> Save
				</button>
			</div>
			
			<div class="btn-group" style="padding-left: 4px">
				<button type="button" id="btn-delete" class="btn btn-default disabled">
					<span class="glyphicon glyphicon-remove"></span>  Delete
				</button>
			</div>
		</div>
		
		<p> 
		<div id="formService-div" class="container panel panel-default " style="width: inherit; display: none;" >
			<p>
			
			<form id="formService" class="form-horizontal" method="post" >
				
				<input type="hidden" id="formService-id" name="formService-id">
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formService-type">Type :</label>
					<div class="col-sm-6">
						<select class="form-control" id="formService-type" name="formService-type">
								<c:forEach items="${types }" var="type">
									<option value="${type.id }" data-val='{"id":${type.id },"name":"${type.name }","odometer":${type.odometer},"qte":${type.qte},"providerName":"${type.providerColumnName}"}'>${type.name }</option>
								</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formService-date">Date :</label>
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
					<label class="control-label col-sm-2" for="formService-quantity">Quantity :</label>
					<div class="col-sm-6">
						<input type="text" data-validation="number" data-validation-allowing="integer" data-validation-optional="true" class="form-control" id="formService-quantity" name="formService-quantity">
					</div>
				</div>
				
				<div class="form-group" id="formService-odometerblock">
					<label class="control-label col-sm-2" for="formService-odometer">Odometer :</label>
					<div class="col-sm-6">
						<input type="text" data-validation="number" data-validation-optional="true" class="form-control" id="formService-odometer" name="formService-odometer">
					</div>
				</div>
					
				<div class="form-group">
					<label class="control-label col-sm-2" for="formService-comment">Comment :</label>
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