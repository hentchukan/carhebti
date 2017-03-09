<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Customized functions -->
<script src="js/addType.js"></script>

<body>
	<form id="addTypeForm" class="form-horizontal" method="post" action="addType.action">
		<div class="form-group">
			<label class="control-label col-sm-4" for="name">Name :</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="name"
					placeholder="Enter type name">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="pwd">Provider type name:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="provider"
					placeholder="Enter provider type name">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<div class="checkbox">
					<label><input type="checkbox">Odometer</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<div class="checkbox">
					<label><input type="checkbox">Quantity</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-8">
				<button id="btn-submit" type="button" class="btn btn-default">Add</button>
			</div>
		</div>
		
		<div class="form-group">
			<div id="msg-error" class="col-sm-offset-2 col-sm-8 alert alert-danger collapse" role="alert">
				
			</div>
		</div>
	</form>
</body>