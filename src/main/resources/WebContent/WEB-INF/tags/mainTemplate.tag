<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true"%>
<%@ attribute name="page" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<c:set var="direction" value="${language == 'ar' ? 'rtl' : 'ltr'}"/>

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="messages.labels" />

<html lang="${language}" dir="${direction }">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CarHebti Manager</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- Customized style -->
<link rel="stylesheet" href="css/main.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="js/jquery-dateFormat.min.js"></script>
	
<!-- Customized functions -->
<script src="js/${page}.js"></script>
<script src="js/template.js"></script>
</head>

<body>
		
	<div class="container">
		
		<div class="row">
			<div class="col-sm-12">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Me</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar" style="padding-right: 5px">
					<ul class="nav navbar-nav navbar-right" style="padding-top: 10px">
						<li><img src="flags/gb.svg" style="width: 25px; height: 20px; margin: 5px" class="flag-icon-gb"/></li>
						<li><img src="flags/fr.svg" style="width: 25px; height: 20px; margin: 5px" class="flag-icon-fr"/></li>
						<li><img src="flags/tn.svg" style="width: 25px; height: 20px; margin: 5px" class="flag-icon-tn"/></li>
					</ul>
				</div>
			</div>
			</nav>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-3">
				<div class="sidebar-nav">
					<div class="navbar navbar-default" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle"
								data-toggle="collapse" data-target=".sidebar-navbar-collapse">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<span class="visible-xs navbar-brand"><fmt:message key="labels.mainpage.functions"></fmt:message> </span>
						</div>
						
						<div class="navbar-collapse collapse sidebar-navbar-collapse navbar-ul${direction == 'rtl' ?  '-right' : ''}">
							<ul class="nav navbar-nav">
								<li><div class="btn btn-default.focus btn-menu${direction == 'rtl' ?  '-right' : ''}" id="btn-serviceList"><fmt:message key="labels.mainpage.manageservice" /> </div></li>
								<li><div class="btn btn-menu${direction == 'rtl' ?  '-right' : ''}" id="btn-typeList"><fmt:message key="labels.mainpage.managetype" /></div></li>
								<li><div class="btn btn-menu${direction == 'rtl' ?  '-right' : ''}" id="btn-budget"><fmt:message key="labels.mainpage.budget" /></div></li>
							</ul>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			
			<div class="col-sm-9" >
				<div id="body" class="panel panel-default">
					<div class="panel-heading" id="panelTitle">
						${title }
					</div>
					<div class="panel-body" id="panelBody">
						<jsp:doBody />
					</div>
				</div>
			</div>
		</div>		
	</div>    
</body>
</html>