<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true"%>
<%@ attribute name="page" required="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CarHebti Manager</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<<<<<<< HEAD
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
				<div class="collapse navbar-collapse" id="myNavbar" style="margin-right: 5px">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">WHO</a></li>
						<li><a href="#">WHAT</a></li>
						<li><a href="#">WHERE</a></li>
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
							<span class="visible-xs navbar-brand">Functions</span>
						</div>
						<div class="navbar-collapse collapse sidebar-navbar-collapse">
							<ul class="nav navbar-nav">
								<!-- <li class="active"><a href="#">Functions</a></li> -->
								<li><div class="btn btn-default.focus btn-menu" id="btn-serviceList">Manage Services</div></li>
								<li><div class="btn btn-menu" id="btn-typeList" > Manage Types</div></li>
=======
<!-- Customized style -->
<link rel="stylesheet" href="css/main.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Customized functions -->
<script src="js/${page}.js"></script>
<script src="js/template.js"></script>
</head>

<body>
	<div class="container">
		
		<div class="row">
			<div class="col-sm-12">
			<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#mainHeader">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Me</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar" style="margin-right: 5px">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">WHO</a></li>
						<li><a href="#">WHAT</a></li>
						<li><a href="#">WHERE</a></li>
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
							<span class="visible-xs navbar-brand">Sidebar menu</span>
						</div>
						<div class="navbar-collapse collapse sidebar-navbar-collapse">
							<ul class="nav navbar-nav">
								<li class="active"><a href="#">Functions</a></li>
								<li><div class="btn btn-default.focus btn-menu" id="btn-addService">Add Service</div></li>
								<li><div class="btn btn-menu" id="btn-addType" > Add Type</div></li>
>>>>>>> refs/remotes/origin/master
								<li><div class="btn btn-menu" id="btn-showHistory" >Show history</div></li>
							</ul>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>

			<div class="col-sm-9">
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