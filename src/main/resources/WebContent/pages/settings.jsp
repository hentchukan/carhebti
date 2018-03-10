<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages.labels" />

<fmt:message key="labels.settings.title" var="title"/>
<t:mainTemplate page="settings" title="${title }">
	
	<jsp:body>
		
		<c:if test="${not empty updatedSettings}">
			<div class="alert alert-success">
				<strong><fmt:message key="labels.settings.updatesettings.title"/> </strong>
				<fmt:message key="labels.settings.updatesettings.body">
				</fmt:message>
			</div>
		</c:if>
		
		<p>
		<div class="btn-group btn-group-justified">
			
			<div class="btn-group" style="padding-right: 4px">
				<button type="button" id="btn-add" class="btn btn-default" disabled="disabled">
					<span class="glyphicon glyphicon-plus"></span> <fmt:message key="labels.add"/>
				</button>
			</div>
			
			<div class="btn-group">
				<button type="button" id="btn-save" class="btn btn-default">
					<span class="glyphicon glyphicon-save"></span> <fmt:message key="labels.save"/>
				</button>
			</div>
			
			<div class="btn-group" style="padding-left: 4px">
				<button type="button" id="btn-delete" class="btn btn-default disabled" disabled="disabled">
					<span class="glyphicon glyphicon-remove"></span> <fmt:message key="labels.delete"/>
				</button>
			</div>
		</div>
		
		<p>
		<div id="formSettings-div" class="container panel panel-default " style="width: inherit;" >
			<p>
			
			<form id="formSettings" class="form-horizontal" method="post" >
				
				<input type="hidden" id="formType-id" name="formType-id">
				
				<div class="form-group">
					<label class="control-label col-sm-4" for="formSettings-language"><fmt:message key="labels.settings.language" /> :</label>
					<div class="col-sm-6">
						<div class="col-sm-6">
						<select class="form-control" id="formSettings-language" name="formSettings-language">
							<option value="EN" ${settings.language == 'EN' ? 'selected' : ''}>English</option>
							<option value="FR" ${settings.language == 'FR' ? 'selected' : ''}>Français</option>
							<option value="AR" ${settings.language == 'AR' ? 'selected' : ''}>العربيّة</option>
						</select>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-4" for="formSettings-car"><fmt:message key="labels.settings.defaultcar" />:</label>
					<div class="col-sm-6">
						<div class="col-sm-6">
						<select class="form-control" id="formSettings-car" name="formSettings-car">
							<c:forEach items="${cars }" var="car">
								<option value="${car.id }" ${settings.car.id == car.id ? 'selected' : ''} data-val='{"id":${car.id },"manifacturer":"${car.manifacturer }","trade":${car.trade},"number":${car.number},"greyCard":"${car.greyCard}"}'>${car.number }</option>
							</c:forEach>
						</select>
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