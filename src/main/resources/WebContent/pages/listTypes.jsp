<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:mainTemplate page="listTypes" title="Types List">
	
	<jsp:body>
		<c:if test="${not empty createdType}">
			<div class="alert alert-success">
				<strong>New Type added</strong> Type id ${createdType } is successfully added to the list
			</div>
		</c:if>
		
		<c:if test="${not empty updatedType}">
			<div class="alert alert-success">
				<strong>Type updated</strong> Type id ${updatedType } is successfully updated
			</div>
		</c:if>
		
		<c:if test="${not empty deletedType}">
			<div class="alert alert-success">
				<strong>Type deleted</strong> Type id ${deletedType } successfully deleted from the list
			</div>
		</c:if>
		
		
		<div class="container table-responsive" style="width: inherit;">
			<table class="listType table table-striped table-condensed" data-link="row">
				<thead>
					<tr>
						<td hidden="true">Id</td>
						<td>Type</td>
						<td>Provider</td>
						<td>Klm</td>
						<td>Qte</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${types}" var="type">
						<tr class="${newType == type.id ? 'selectedRow' : 'none' }" data-href="clickable">
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
		<div id="formType-div" class="container panel panel-default " style="width: inherit; display: none;" >
			<p>
			
			<form id="formType" class="form-horizontal" method="post" >
				
				<input type="hidden" id="formType-id" name="formType-id">
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formType-name">Name :</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="formType-name"
								name="formType-name" placeholder="Enter type name">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formType-provider">Provider type:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="formType-provider"
								name="formType-provider" placeholder="Enter provider type name">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-2 col-md-offset-2">
						<div class="checkbox">
							<label><input type="checkbox" name="formType-odometer"><b>Odometer</b></label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 col-md-offset-2">
						<div class="checkbox">
							<label><input type="checkbox" name="formType-quantity"><b>Quantity</b></label>
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