<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}_FR" />
<fmt:setBundle basename="messages.labels" />

<fmt:message key="labels.budget.title" var="title"/>
<t:mainTemplate page="budget" title="${title }">
	<jsp:body>
		
		<div id="content" class="container responsive" style="width: inherit;">
			<div id="budgetParameters-div" class="container panel panel-default " style="width: inherit;" >
			<div class="form-horizontal">
				<p/>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="budgetParameters-startDate"><fmt:message key="labels.budget.startDate" /> :</label>
					<div class="col-sm-6">
    					<fmt:formatDate value="${startDate}" pattern="dd/MM/yyyy" var="start_date" />
                        <input readonly="readonly" size="12" type="text" class="form-control datepicker" id="budgetParameters-startDate" name="budgetParameters-startDate" value="${start_date }"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="budgetParameters-endDate"><fmt:message key="labels.budget.endDate" /> :</label>
					<div class="col-sm-6">
    					<fmt:formatDate value="${endDate}" pattern="dd/MM/yyyy" var="end_date" />
                        <input readonly="readonly" size="12" type="text" class="form-control datepicker" id="budgetParameters-endDate" name="budgetParameters-endDate" value="${end_date }"/>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-2">
						<button type="button" id="budget-btn" class="btn btn-default">
							<span class="glyphicon glyphicon-plus"></span> <fmt:message key="labels.budget.show"/>
						</button>
					</div>
				</div>
				
				<p/>
			</div>
			</div>
			
			<div id="chartContainer" style="height: 300px; width: 100%;">
			
			</div>			
		</div>
	</jsp:body>
</t:mainTemplate>