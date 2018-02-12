<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/home.css"/>" rel="stylesheet">
<script
	src="<c:url value="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
<link rel="shortcut icon" href="/images/favicon.png" type="image/png">
<title>Home</title>
</head>
<body>
	<header class="header-panel">
		<div
			class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-sm-8 col-sm-offset-2 col-xs-8 col-xs-offset-2 logo text-uppercase">
			<h1>roboxandr</h1>
			<h1>фотостудия</h1>
			<h2>фотографы Александр Кузнецов и Екатерина Глазачева</h2>
		</div>
	</header>
	<div class="row menu-container">
		<div class="col-md-5 col-lg-5 menu-logo col-xs-12 hidden-xs child">roboxandr</div>
		<div class="col-md-4 col-lg-4 col-sm-8 col-xs-12 child">
			<ul class="menu">
				<li class="active"><a href="#works">Портфолио</a></li>
				<li><a href="#news">Новости</a></li>
				<li><a href="#bio">Биография</a></li>
				<li><a href="#contacts">Контакты</a></li>
			</ul>
		</div>
		<div class="col-md-3 col-lg-3 col-sm-12 col-xs-12 child">
			<ul class="social-menu content-center">
				<li class="active"><a href="#"><img alt="fb"
						src="<c:url value="/resources/icons/fb.png"/>"></a></li>
				<li><a href="https://vk.com/roboxandr"><img alt="vk"
						src="<c:url value="/resources/icons/vk.png"/>"></a></li>
				<li><a href="https://www.instagram.com/roboxandr.photography/"><img
						alt="instagram"
						src="<c:url value="/resources/icons/instagram.png"/>"></a></li>
				<li><a href="#"><img alt="twitter"
						src="<c:url value="/resources/icons/twitter.png"/>"></a></li>
			</ul>
		</div>
	</div>
	<div class="row gallery">
		<a name="works"></a>
		<div class="text-center text-uppercase">
			<h1>портфолио/</h1>
		</div>
		<div class="container text-center">
			<c:if test="${!empty albumList}"></c:if>
			<c:forEach items="${albumList}" var="album">
				<c:if test="${album.published==true}"></c:if>
				<div
					style="background-image: url(https://drive.google.com/uc?id=${album.linkToPreviewPhoto}); "
					class="col-md-6 preview-container" id="${album.albumFolderId}">
				</div>
			</c:forEach>
		</div>
		<div id="photo-carousel" class="carousel slide">
			<div class="text-right row">
				<span id="carouselRemoveBtn" class="glyphicon glyphicon-remove-sign"></span>
			</div>
			<div class="carousel-inner"></div>
			<a class="left carousel-control" href="#photo-carousel"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left"></span>
			</a> <a class="right carousel-control" href="#photo-carousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>
	</div>
	<div class="row slogan text-uppercase">
		<div class="col-md-8 col-md-offset-2 text-center">
			<h1>"Наименее ценное, что вы можете сообщить фотографу — это то,
				что вам нравятся или не нравятся его работы."</h1>
		</div>
		<div class="col-md-10 col-md-offset-1 text-right">
			<h2>David Bayles</h2>
		</div>
	</div>
	<div class="row news">
		<a name="news"></a>
		<div class="text-center text-uppercase">
			<h1>Новости/</h1>
		</div>
		<c:if test="${!empty selectedArticle}">
			<div class="col-md-4 col-md-offset-2">
				<div class="row">
					<div class="col-md-6">
						<span class="main-news-date">${selectedArticle.publicationDate}</span>
					</div>
					<div class="col-md-6 text-right">
						<p class="red-color main-tags">
							<span class="glyphicon glyphicon-tag blue-color main-tags-icon"></span><i><span
								class="select-tags">${selectedArticle.hashtags}</span></i>
						</p>
					</div>
				</div>
				<div class="row green-color news-caption selected-caption">${selectedArticle.caption}</div>
				<div class="row">
					<c:catch var="exception">${selectedArticle.linkToPhoto}</c:catch>
					<c:if test="${not empty exception}">
						<img class="main-news-img img-rounded"
							src="<c:url value='/resources/images/news/${selectedArticle.linkToPhoto}'/>" />
					</c:if>
				</div>
				<div class="selected-content">${selectedArticle.content}</div>
				<div>
					<p class="text-right red-color selected-author">${selectedArticle.author}</p>
				</div>
			</div>
		</c:if>
		<div class="col-md-3 col-md-offset-1">
			<%@include file="templates/article_list.jsp"%>
		</div>
	</div>
	<div class="row bio">
		<a name="bio"></a>
		<div class="text-center text-uppercase">
			<h1>биография/</h1>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-2 bio-text text-center">
				<h2 class="text-uppercase">Александр Кузнецов</h2>
				<img alt="Александр Кузнецов" class="img-circle bio-img"
					src="<c:url value="/resources/images/A.Kuznetsov.jpg"/>">
			</div>
			<div class="col-md-4 bio-text col-xs-10 col-xs-offset-1">
				<h3 class="text-uppercase">Какой-то девиз или слоган!</h3>
				<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
					do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
					enim ad minim veniam, quis nostrud exercitation ullamco laboris
					nisi ut aliquip ex ea commodo consequat.</p>
				<p>Duis aute irure dolor in reprehenderit in voluptate velit
					esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
					occaecat cupidatat non proident, sunt in culpa qui officia deserunt
					mollit anim id est laborum."</p>
				<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
					do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
					enim ad minim veniam, quis nostrud exercitation ullamco laboris
					nisi ut aliquip ex ea commodo consequat.</p>
				<p>Duis aute irure dolor in reprehenderit in voluptate velit
					esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
					occaecat cupidatat non proident, sunt in culpa qui officia deserunt
					mollit anim id est laborum."</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-2 bio-text text-center">
				<h2 class="text-uppercase">Екатерина Глазачева</h2>
				<img alt="Екатерина Глазачева" class="img-circle bio-img"
					src="<c:url value="/resources/images/ekaterina.jpg"/>">
				<ul class="social-menu">
					<li class="active"><a href="#"><img alt="fb"
							src="<c:url value="/resources/icons/fb.png"/>"></a></li>
					<li><a href="https://vk.com/roboxandr"><img alt="vk"
							src="<c:url value="/resources/icons/vk.png"/>"></a></li>
					<li><a href="https://www.instagram.com/roboxandr.photography/"><img
							alt="instagram"
							src="<c:url value="/resources/icons/instagram.png"/>"></a></li>
					<li><a href="#"><img alt="twitter"
							src="<c:url value="/resources/icons/twitter.png"/>"></a></li>
				</ul>
			</div>
			<div class="col-md-4 bio-text col-xs-10 col-xs-offset-1">
				<h3 class="text-uppercase">Какой-то девиз или слоган!</h3>
				<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
					do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
					enim ad minim veniam, quis nostrud exercitation ullamco laboris
					nisi ut aliquip ex ea commodo consequat.</p>
				</p>
				<p>Duis aute irure dolor in reprehenderit in voluptate velit
					esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
					occaecat cupidatat non proident, sunt in culpa qui officia deserunt
					mollit anim id est laborum."</p>
				<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
					do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
					enim ad minim veniam, quis nostrud exercitation ullamco laboris
					nisi ut aliquip ex ea commodo consequat.</p>
				</p>
				<p>Duis aute irure dolor in reprehenderit in voluptate velit
					esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
					occaecat cupidatat non proident, sunt in culpa qui officia deserunt
					mollit anim id est laborum."</p>
			</div>
		</div>
	</div>
	<footer>
		<div class="row contact">
			<a name="contacts"></a>
			<div class="text-center text-uppercase">
				<h1>контакты/</h1>
				<h2>+8899808080098</h2>
			</div>
			<div class="col-md-4 col-md-offset-4 text-center email-block">
				<h2>sdasdasd@gmail.com</h2>
			</div>
			<div class="text-center text-uppercase col-md-12">
				<h5>г. Архангельск, ул. Какая-то, д. такой-то</h5>
			</div>
		</div>
	</footer>
</body>
</html>

