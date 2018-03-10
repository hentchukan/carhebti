<%@page import="prv.carhebti.business.entities.Car"%>
<%@page import="prv.carhebti.common.tools.ConversionTool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages.labels" />

<fmt:message key="labels.listcars.title" var="title"/>
<t:mainTemplate page="listCars" title="${title }">
	
	<jsp:body>
		<c:if test="${not empty createdCar}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listcars.addcar.title" /></strong> 
				<fmt:message key="labels.listcars.addcar.body">
					<fmt:param value="${createdcar }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<c:if test="${not empty updatedcar}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listcars.updatecar.title"/> </strong>
				<fmt:message key="labels.listcars.updatecar.body">
					<fmt:param value="${updatedcar }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<c:if test="${not empty deletedcar}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.listcars.deletecar.title" /> </strong> 
				<fmt:message key="labels.listcars.deletecar.body">
					<fmt:param value="${deletedcar }"/>
				</fmt:message>
			</div>
		</c:if>
		
		<div id="content" class="container table-responsive" style="width: inherit;">
			<input type='hidden' id='current_page' />  
			<input type='hidden' id='show_per_page' /> 
			
			<table class="listCar table table-bordered table-hover table-striped table-condensed" data-link="row">
				<thead>
					<tr>
						<td><fmt:message key="labels.listcars.id" /></td>
						<td><fmt:message key="labels.listcars.number" /></td>
						<td><fmt:message key="labels.listcars.trade" /></td>
						<td hidden="true">Manifacturer</td>
						<td hidden="true">Grey Card</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cars}" var="car">
						<tr class="${newCar == car.id ? 'selectedRow' : 'none' } line pagination" data-href="clickable" data-toggle="modal" data-target="#carModal" data-whatever="${car.id }">
							<td>${car.id } </td>
							
							<td><c:out value="${car.number }"></c:out></td>
							<td><c:out value="${car.trade }"></c:out></td>
							
							<td hidden="true"><input type="hidden" value="${car.manifacturer }"/></td>
							<td hidden="true"><input type="hidden" value="${car.greyCard }"/></td>
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
		<div id="formCar-div" class="container panel panel-default " style="width: inherit; display: none;" >
			<p>
			
			<form id="formCar" class="form-horizontal" method="post" >
				
				<input type="hidden" id="formCar-id" name="formCar-id">
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formCar-number"><fmt:message key="labels.listcars.number" /> :</label>
					<div class="col-sm-6">
						<input type="text" data-validation-length="50" class="form-control" id="formCar-number" name="formCar-number">						
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formCar-trade"><fmt:message key="labels.listcars.trade" /> :</label>
					<div class="col-sm-6">
						<input type="text" data-validation-length="50" class="form-control" id="formCar-trade" name="formCar-trade">						
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formCar-manifacturer"><fmt:message key="labels.listcars.manifacturer" /> :</label>
					<div class="col-sm-6">
						<input type="text" data-validation-length="50" class="form-control" id="formCar-manifacturer" name="formCar-manifacturer">						
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="formCar-greyCard"><fmt:message key="labels.listcars.greycard" /> :</label>
					<div class="col-sm-6">
						<input type="text" data-validation-length="50" class="form-control" id="formCar-greyCard" name="formCar-greyCard">						
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