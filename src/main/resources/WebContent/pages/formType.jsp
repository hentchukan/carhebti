<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<form id="typeForm" class="form-horizontal" method="post" action="saveType.action">
		<div class="form-group">
			<label class="control-label col-sm-4" for="name">Name :</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="typeform-name" name="typeform-name"
					placeholder="Enter type name">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="pwd">Provider type name:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="typeform-provider" name="typeform-provider"
					placeholder="Enter provider type name">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<div class="checkbox">
					<label><input type="checkbox" name="typeform-odometer">Odometer</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<div class="checkbox">
					<label><input type="checkbox" name="typeform-quantity">Quantity</label>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div id="msg-error" class="col-sm-offset-2 col-sm-8 alert alert-danger collapse" role="alert">
				
			</div>
		</div>
	</form>