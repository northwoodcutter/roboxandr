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
<title>admin</title>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="#"> Фотографии <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a data-toggle="tab" href="#panel1">Альбомы</a></li>
					<li><a data-toggle="tab" href="#panel2">Добавить альбом</a></li>
				</ul>
			</li>
			<li class="dropdown"><a data-toggle="dropdown"
				class="dropdown-toggle" href="#"> Статьи <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a data-toggle="tab" href="#panel3">Список статей</a></li>
					<li><a data-toggle="tab" href="#panel4">Добавить статью</a></li>
				</ul>
			</li>
		</ul>
		<div class="tab-content">
			<div id="panel1" class="tab-pane fade in active">
				<%@include file="templates/albums_list.jsp"%>
			</div>
			<div id="panel2" class="tab-pane fade">
				<%@include file="templates/add_album_form.jsp"%>
			</div>
			<div id="panel3" class="tab-pane fade">
				<%@include file="templates/admin_article_list.jsp"%>
			</div>
			<div id="panel4" class="tab-pane fade">
				<%@include file="templates/add_article_form.jsp"%>
			</div>
		</div>

	</div>
</body>
</html>

