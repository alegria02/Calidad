<!doctype html>
<html>
<head>
	<%@ page import="java.util.ArrayList"%>
<%
	HttpSession sesion = request.getSession();
	if(sesion.getAttribute("usuario") == null){
	   response.sendRedirect("index.jsp");
	} %>
	
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
	
	<script>
		less = {
			env : "development",
			relativeUrls : false,
			rootpath : "/assets/"
		};
	</script>
</head>
<body class="  " onload="funcion()">
	<div class="bg-dark dk" id="wrap">
		<div id="top">
			<!-- .navbar -->
			<nav class="navbar navbar-inverse navbar-static-top">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<header class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
							<span class="sr-only">Toggle navigation</span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<a href="DatosServlet" class="navbar-brand"><img src="" alt=""></a>
					</header>
					<div class="topnav">
						<div class="btn-group">
							<p>Bienvenido ${sessionScope.usuario}</p>
						</div>
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
									<span class="fa fa-search"></span>
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
					

				</ul>
			</form>
			<!-- /#menu -->
		</div>
		<!-- /#left -->
		<div id="content">
			<div class="outer">
				<div class="inner bg-light lter">
					<div class="col-lg-12">
						<h1>Totales</h1>


						<!--Begin Datatables-->
						<div class="row">
							<div class="col-lg-12">
							<div class="box">
									<header>
										<div class="icons">
											<i class="fa fa-table"></i>
										</div>
										<h5>Actualizacion indices de calidad</h5>
									</header>
									<div id="collapse4" class="body">
										
										
									<form enctype="multipart/form-data" action="UpdateServlet" method="post"> 
										<div class="form-group">
											<input id="file-1" name="archivo2" type="file" class="btn btn-default" multiple=true data-preview-file-type="any">
										</div>
									
										<div class="form-group">
											<button class="btn btn-primary">Enviar</button>
											<button class="btn btn-default" type="reset">Limpiar</button>
											<span id="mensaje"></span>
										</div>
									</form>
										
										
									</div>
								</div>
								<div class="box">
									<header>
										<div class="icons">
											<i class="fa fa-table"></i>
										</div>
										<h5>Carga de Planilla Chp, para control de calidad</h5>
									</header>
									<div id="collapse4" class="body">
										
										
									<form enctype="multipart/form-data" action="PlanillaServlet" method="post"> 
										<div class="form-group">
											<input id="file-1" name="archivo" type="file" class="btn btn-default" multiple=true data-preview-file-type="any">
										</div>
									
										<div class="form-group">
											<button class="btn btn-primary">Enviar</button>
											<button class="btn btn-default" type="reset">Limpiar</button>
											<span id="mensaje"></span>
										</div>
									</form>
										
										
									</div>
								</div>
								<div class="box">
									<header>
										<div class="icons">
											<i class="fa fa-table"></i>
										</div>
										<h5>Ingresar datos de Aplicativo</h5>
									</header>
									<div id="collapse4" class="body">
										
										
									<form method="post" action="NuevaInformacionServlet" > 
										<div class="form-group">
											Fecha 
											<input type="date" class="text" id="fecha" name="fecha"><br>
											Dda/Inci
											<input type="text" class="text" id="dda" name="dda"><br>
											Aplicativo 
											<input type="text" class="text" id="aplicativo" name="aplicativo"><br>
											Eficiencia
											<input type="text" class="text" id="efi" name="efi"><br>
											Mantenibilidad
											<input type="text" class="text" id="man" name="man"><br>
											Portabilidad
											<input type="text" class="text" id="por" name="por"><br>
											Fiabilidad
											<input type="text" class="text" id="fia" name="fia"><br>
											Seguridad
											<input type="text" class="text" id="seg" name="seg"><br>
											Observacion
											<input type="text" class="text" id="obs" name="obs"><br>
										</div>
									
										<div class="form-group">
											<button class="btn btn-primary">Ingresar</button>
											<button class="btn btn-default" type="reset">Limpiar</button>
										</div>
									</form>
										
										
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
</body>
<script type="text/javascript">
		function funcion() {
						
			<% if (request.getAttribute("datos") != null) {%>
				alert('<%=request.getAttribute("datos")%>');
			<%}%>
			
			<% if (request.getAttribute("mensaje") != null) {%>
				alert('<%=request.getAttribute("mensaje")%>');
			<%}%>
		}
	</script>

</html>
