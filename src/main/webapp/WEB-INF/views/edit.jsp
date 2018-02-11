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
<script src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
<script src="<c:url value="/resources/js/main.js"/>"></script>
<link rel="shortcut icon" href="/images/favicon.png" type="image/png">
<title>${album.name}</title>
</head>
<body>
	<div class="container">
		<form:form method="POST" action="update" commandName="album"
			modelAttribute="album" class="edit-form">
			<fieldset>
				<form:input path="id" value="${album.id}" style="display: none;" />
				<form:input id="previewPhotoIdInput" path="previewPhotoId" value="${album.previewPhotoId}"
					style="display: none;"/>
				<form:input id="albumFolderId" path="albumFolderId"
					value="${album.albumFolderId}" style="display: none;" />
				<form:label path="name">Название альбома</form:label>
				<form:input class="form-control" path="name" value="${album.name}" />
				<form:label path="definition">Описание</form:label>
				<form:textarea class="form-control" path="definition"
					value="${album.definition}" />
				<form:label path="name">Отображать на главной странице</form:label>
				<form:checkbox path="published" />
				<br />
				<form:button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-floppy-disk"></span>
				</form:button>
			</fieldset>
		</form:form>
		<div class="row">
			<div class="col-md-1">
				<input id="selectAllCheckbox" type="checkbox">
			</div>
			<div class="col-md-2">
				<button id="deletePhotosBtn" class="trash-button btn btn-default">
					<span class="glyphicon glyphicon-trash"></span>
				</button>
			</div>
			<div class="col-md-9">
				<%@include file="templates/load_photo_form.jsp"%>
			</div>
		</div>
		<table id="photoList" class="table">
			<c:if test="${!empty photos}"></c:if>
			<c:forEach items="${photos}" var="photo">
				<tr class="photo-table-row">
					<td><input class="photo-sheckbox" type="checkbox"
						value="${photo.linkToPhoto}"></td>
					<td><img class="preview-img" alt="photo"
						src="<c:url value='https://drive.google.com/uc?id=${photo.linkToPhoto}'/>"></td>
					<td><p>Сделать обложкой альбома
					<input class="select-prewiev-checkbox" type="checkbox" data-preview-id="${photo.linkToPhoto}"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>

