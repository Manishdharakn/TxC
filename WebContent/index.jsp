<!doctype html>
<%@page import="com.xonlabs.txc.util.Constants"%>
<html class="no-js" lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<!-- Renamed Sentiment -->
<title>Sentimental Analysis</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- favicon
		============================================ -->
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<!-- Google Fonts
		============================================ -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,700,900"
	rel="stylesheet">
<!-- Bootstrap CSS
		============================================ -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Bootstrap CSS
		============================================ -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- owl.carousel CSS
		============================================ -->
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/owl.theme.css">
<link rel="stylesheet" href="css/owl.transitions.css">
<!-- meanmenu CSS
		============================================ -->
<link rel="stylesheet" href="css/meanmenu/meanmenu.min.css">
<!-- animate CSS
		============================================ -->
<link rel="stylesheet" href="css/animate.css">
<!-- normalize CSS
		============================================ -->
<link rel="stylesheet" href="css/normalize.css">
<!-- mCustomScrollbar CSS
		============================================ -->
<link rel="stylesheet"
	href="css/scrollbar/jquery.mCustomScrollbar.min.css">
<!-- jvectormap CSS
		============================================ -->
<link rel="stylesheet" href="css/jvectormap/jquery-jvectormap-2.0.3.css">
<!-- notika icon CSS
		============================================ -->
<link rel="stylesheet" href="css/notika-custom-icon.css">
<!-- wave CSS
		============================================ -->
<link rel="stylesheet" href="css/wave/waves.min.css">
<!-- main CSS
		============================================ -->
<link rel="stylesheet" href="css/main.css">
<!-- style CSS
		============================================ -->
<link rel="stylesheet" href="style.css">
<!-- responsive CSS
		============================================ -->
<link rel="stylesheet" href="css/responsive.css">
<!-- modernizr JS
		============================================ -->
<script src="js/vendor/modernizr-2.8.3.min.js"></script>
<!--Custom CSS Class to handle carousel inputs like height and width  -->
<style type="text/css">
.carousel {
	height: 410px;
}

.carousel-inner {
	height: 100%;
}
</style>
</head>

<body>
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
	<!-- Start Header Top Area -->
	<div class="header-top-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="logo-area">
						<h3 style='color: white;'><%=Constants.PROJECT_NAME%></h3>
					</div>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
					<div class="header-top-menu">
						<h4 style='float: right; color: white; padding-top: 20px;'><%=Constants.COMPANY_NAME%></h4>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Header Top Area -->
	<!-- Mobile Menu start -->
	<div class="mobile-menu-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="mobile-menu">
						<nav id="dropdown">
							<ul class="mobile-menu-nav">
								<li><a data-toggle="collapse" data-target="#Charts"
									href="#">Home</a> <!-- removed these by hiding (Mobile menu doesn't has carousel)-->
									<ul class="collapse dropdown-header-top" style="display: none;">
										<li><a href="index.jsp" style="display: none;">Abstract</a></li>
										<li><a href="existingsystem.jsp" style="display: none;">Existing
												System</a></li>
										<li><a href="proposedsystem.jsp" style="display: none;">Proposed
												System</a></li>
										<li><a href="developers.jsp" style="display: none;">Developers</a></li>
									</ul></li>
								<li><a data-toggle="collapse" data-target="#demoevent"
									href="#">Access</a>
									<ul id="demoevent" class="collapse dropdown-header-top">
										<li><a href="register.jsp">Register</a></li>
										<li><a href="login.jsp">Login</a></li>
										<!-- removed forgot password as functionality is yet to be developed -->
										<li><a href="forgotpassword.jsp" style="display: none;">Forgot
												Password</a></li>
									</ul></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Mobile Menu end -->
	<!-- Main Menu area start-->
	<div class="main-menu-area mg-tb-40">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<ul class="nav nav-tabs notika-menu-wrap menu-it-icon-pro">
						<li class="active"><a data-toggle="tab" href="#Home">
								Home</a></li>
						<li><a data-toggle="tab" href="#mailbox"> Access</a></li>
					</ul>
					<div class="tab-content custom-menu-content">
						<div id="Home"
							class="tab-pane in active notika-tab-menu-bg animated flipInX">

							<!-- Removed by commenting as this was leaving white box even after setting to display none -->
							<!-- <ul class="notika-main-menu-dropdown">
								<li><a href="index.jsp" style="display: none;">About</a></li>
								<li><a href="existingsystem.jsp" style="display: none;">Existing
										System</a></li>
								<li><a href="proposedsystem.jsp" style="display: none;">Proposed
										System</a></li>
								<li><a href="developers.jsp" style="display: none;">Developers</a>
								</li>
							</ul> -->
							<!-- Carousel component for images -->
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div id="carousel-example-generic" class="carousel slide"
									data-ride="carousel">
									<!-- Indicators -->
									<ol class="carousel-indicators">
										<li data-target="#carousel-example-generic" data-slide-to="0"
											class="active"></li>
										<li data-target="#carousel-example-generic" data-slide-to="1"></li>
										<li data-target="#carousel-example-generic" data-slide-to="2"></li>
									</ol>

									<!-- Wrapper for slides -->
									<div class="carousel-inner" role="listbox">
										<div class="item active">
											<img src="images/twitter/01.jpg" alt="img1"
												style="height: 410px; width: 100%; margin: auto;">
											<div class="carousel-caption"></div>
										</div>
										<div class="item ">
											<img src="images/twitter/02.png" alt="img1"
												style="height: 410px; width: 100%; margin: auto;">
											<div class="carousel-caption">
												<h2 style="color: #FFFFFF;">Twitter Analysis</h2>
											</div>
										</div>
										<div class="item ">
											<img src="images/twitter/03.jpg" alt="img1"
												style="height: 410px; width: 100%; margin: auto;">
											<div class="carousel-caption">
												<h2 style="color: #000000;">Twitter e-Library</h2>
											</div>
										</div>
									</div>

									<!-- Controls -->
									<a class="left carousel-control"
										href="#carousel-example-generic" role="button"
										data-slide="prev"> <span
										class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="right carousel-control"
										href="#carousel-example-generic" role="button"
										data-slide="next"> <span
										class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
							</div>
							<!-- carousel END -->
						</div>
						<div id="mailbox"
							class="tab-pane notika-tab-menu-bg animated flipInX">
							<ul class="notika-main-menu-dropdown">
								<li><a href="register.jsp">Register</a></li>
								<li><a href="login.jsp">Login</a></li>
								<!--Removed this as functionality yet to be developed  -->
								<li style="display: none;"><a href="forgotpassword.jsp">Forgot
										Password</a></li>
							</ul>
							<!-- Dummy container for adjusting footer -->
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
								style='min-height: 350px;'>
								<div class="container">
									<div class="row"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Main Menu area End-->
	<!-- Start Status area -->
	<!--Not using This as this is occupied by carousel  -->
	<div class="notika-status-area"
		style='min-height: 600px; display: none;'>
		<div class="container">
			<div class="row"></div>
		</div>
	</div>
	<!-- End Realtime sts area-->
	<!-- Start Footer area-->
	<div class="footer-copyright-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="footer-copy-right">
						<p>
							Copyright © 2019
							<%=Constants.PROJECT_NAME%>
							by
							<%=Constants.COMPANY_NAME%></p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Footer area-->
	<!-- jquery
		============================================ -->
	<script src="js/vendor/jquery-1.12.4.min.js"></script>
	<!-- bootstrap JS
		============================================ -->
	<script src="js/bootstrap.min.js"></script>
	<!-- wow JS
		============================================ -->
	<script src="js/wow.min.js"></script>
	<!-- price-slider JS
		============================================ -->
	<script src="js/jquery-price-slider.js"></script>
	<!-- owl.carousel JS
		============================================ -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- scrollUp JS
		============================================ -->
	<script src="js/jquery.scrollUp.min.js"></script>
	<!-- meanmenu JS
		============================================ -->
	<script src="js/meanmenu/jquery.meanmenu.js"></script>
	<!-- counterup JS
		============================================ -->
	<script src="js/counterup/jquery.counterup.min.js"></script>
	<script src="js/counterup/waypoints.min.js"></script>
	<script src="js/counterup/counterup-active.js"></script>
	<!-- mCustomScrollbar JS
		============================================ -->
	<script src="js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
	<!-- jvectormap JS
		============================================ -->
	<script src="js/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="js/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="js/jvectormap/jvectormap-active.js"></script>
	<!-- sparkline JS
		============================================ -->
	<script src="js/sparkline/jquery.sparkline.min.js"></script>
	<script src="js/sparkline/sparkline-active.js"></script>
	<!-- sparkline JS
		============================================ -->
	<script src="js/flot/jquery.flot.js"></script>
	<script src="js/flot/jquery.flot.resize.js"></script>
	<script src="js/flot/curvedLines.js"></script>
	<script src="js/flot/flot-active.js"></script>
	<!-- knob JS
		============================================ -->
	<script src="js/knob/jquery.knob.js"></script>
	<script src="js/knob/jquery.appear.js"></script>
	<script src="js/knob/knob-active.js"></script>
	<!--  wave JS
		============================================ -->
	<script src="js/wave/waves.min.js"></script>
	<script src="js/wave/wave-active.js"></script>
	<!--  todo JS
		============================================ -->
	<script src="js/todo/jquery.todo.js"></script>
	<!-- plugins JS
		============================================ -->
	<script src="js/plugins.js"></script>
	<!--  Chat JS
		============================================ -->
	<script src="js/chat/moment.min.js"></script>
	<script src="js/chat/jquery.chat.js"></script>
	<!-- main JS
		============================================ -->
	<script src="js/main.js"></script>
	<!-- tawk chat JS
		============================================ -->
</body>

</html>