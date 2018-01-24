<!doctype html>
<html>

<head>
	<%@ page import="java.util.List"%>
	
	<title>Web Calidad</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="msapplication-TileColor" content="#5bc0de" />
	<meta name="msapplication-TileImage" content="assets/img/metis-tile.png" />
	<link rel="Shortcut Icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="assets/lib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="assets/lib/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="assets/lib/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/css/main.css">
	<link rel="stylesheet" href="assets/lib/metismenu/metisMenu.css">
	<link rel="stylesheet" href="assets/lib/onoffcanvas/onoffcanvas.css">
	<link rel="stylesheet" href="assets/lib/animate.css/animate.css">
	<link rel="stylesheet" href="assets/css/style-switcher.css">
	<link rel="stylesheet/less" type="text/css"	href="assets/less/theme.less">
	<script src="assets/lib/jquery/jquery.js"></script>
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.7.1/less.js"></script>
	<script src="assets/lib/bootstrap/js/bootstrap.js"></script>
	<script src="assets/lib/metismenu/metisMenu.js"></script>
	<script src="assets/lib/onoffcanvas/onoffcanvas.js"></script>
	<script src="assets/lib/screenfull/screenfull.js"></script>
	<script src="assets/js/core.js"></script>
	<script src="assets/js/app.js"></script>
	<script src="assets/js/style-switcher.js"></script>
	
	<jsp:useBean id="DatosServlet" class="com.proceso.servlet.DatosServlet" scope="session"></jsp:useBean>
	<script>
		less = {
			env : "development",
			relativeUrls : false,
			rootpath : "/assets/"
		};
	</script>
	<script type="text/javascript">
	$( document ).ready(function() {
		<%
			String nombreApp = (String) request.getAttribute("nombreApp");
			List<String> datos = (List) request.getAttribute("exclusiones");
			List<String> app = (List)request.getAttribute("listaNombresApp");
		%>
	});
	</script>
</head>

<body class="  ">
	<div class="bg-dark dk" id="wrap">
		<div id="top">
			<!-- .navbar -->
			<nav class="navbar navbar-inverse navbar-static-top">
				<div class="container-fluid">


					<!-- Brand and toggle get grouped for better mobile display -->
					<header class="navbar-header">

						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-ex1-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a href="index.jsp" class="navbar-brand"><img
							src="" alt=""></a>

					</header>



					<div class="topnav">
						<div class="btn-group">
							<a href="login.jsp" data-toggle="tooltip"
								data-original-title="Logout" data-placement="bottom"
								class="btn btn-metis-1 btn-sm"> <i class="fa fa-power-off"></i>
							</a>
						</div>
					</div>
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- .nav -->
						<ul class="nav navbar-nav">
							<li><a href="DatosServlet">Dashboard</a></li>
						</ul>
						<!-- /.nav -->
					</div>
				</div>
				<!-- /.container-fluid -->
			</nav>
			<!-- /.navbar -->
			<header class="head">
				<div class="search-bar">
					<form class="main-search" action="">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Live Search ..."> <span
								class="input-group-btn">
								<button class="btn btn-primary btn-sm text-muted" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
					<!-- /.main-search -->
				</div>
				<!-- /.search-bar -->
				<div class="main-bar">
					<h3>
						<i class="fa fa-home"></i>&nbsp;

					</h3>
				</div>
				<!-- /.main-bar -->
			</header>
			<!-- /.head -->
		</div>
		<!-- /#top -->
		<div id="left">
			<div class="media user-media bg-dark dker">
				<div class="user-media-toggleHover">
					<span class="fa fa-user"></span>
				</div>
				<div class="user-wrapper bg-dark">
					<a class="user-link" href=""> <img
						class="media-object img-thumbnail user-img" alt="User Picture"
						src="assets/img/user.gif"> <span
						class="label label-danger user-label">16</span>
					</a>

					<div class="media-body">
						<h5 class="media-heading">Admin</h5>
						<ul class="list-unstyled user-info">
							<li><a href="">Administrator</a></li>
							<li>Last Access : <br> <small><i
									class="fa fa-calendar"></i>&nbsp;16 Mar 16:32</small>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- #menu -->
			<form action="DatosServlet" method="POST">
				<ul id="menu" class="bg-blue dker">
					<li class="nav-header">Menu</li>
					<li class="nav-divider"></li>
					<%for(int i = 0; i < app.size(); i++) {%>
					<li><a
						href="ConsultaServlet?nombreApp=<% out.print(app.get(i)); %>">
							<i class="fa fa-table"></i> <span class="link-title">&nbsp;<% out.print(app.get(i));%></span>
					</a></li>
					<%}%>

	
				</ul>
			</form>
			<!-- /#menu -->
		</div>
		<!-- /#left -->
		<div id="content">
			<div class="outer">
				<div class="inner bg-light lter">
					<div class="col-lg-12">
						<h1><%out.print(nombreApp); %></h1>


						<!--Begin Datatables-->
						<div class="row">
							<div class="col-lg-12">
							<div class="box">
									<header>
										<div class="icons">
											<i class="fa fa-table"></i>
										</div>
										<h5>Exclusiones</h5>
									</header>
									<div id="collapse4" class="body">
										
										<%
										if (datos.size() > 0) {
											for (int i = 0; i < datos.size(); i++) { 
												out.print(datos.get(i) + "<br/>");
											}
										} else {
											out.print("Aplicativo sin exclusiones");
										}
										%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.inner -->
			</div>
			<!-- /.outer -->
		</div>
		<!-- /#content -->
	</div>
	<!-- /#wrap -->
	<footer class="Footer bg-dark dker">
		<p>2017 Dashboard Calidad</p>
	</footer>

	<!--jQuery -->
	<script src="assets/lib/jquery/jquery.js"></script>
	<!--Bootstrap -->
	<script src="assets/lib/bootstrap/js/bootstrap.js"></script>
	<!-- MetisMenu -->
	<script src="assets/lib/metismenu/metisMenu.js"></script>
	<!-- onoffcanvas -->
	<script src="assets/lib/onoffcanvas/onoffcanvas.js"></script>
	<!-- Screenfull -->
	<script src="assets/lib/screenfull/screenfull.js"></script>
	<!-- Metis core scripts -->
	<script src="assets/js/core.js"></script>
	<!-- Metis demo scripts -->
	<script src="assets/js/app.js"></script>
	<script src="assets/js/style-switcher.js"></script>
</body>

</html>
