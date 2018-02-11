<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
			<h1>Альбомы</h1>
			<table class="table">
				<tr>
					<td><h1>Название</h1></td>
					<td><h1>Публиковать на сайт</h1></td>
					<td><h1>Обложка альбома</h1></td>
					<td></td>
					<td></td>
				</tr>
				<c:if test="${!empty albumList}">
				</c:if>
				<c:forEach items="${albumList}" var="album">
					<tr>
						<td>
							<h2>${album.name}</h2>
						</td>
						<td><c:if test="${album.published==true}">
								<h2>Да</h2>
							</c:if> <c:if test="${album.published!=true}">
								<h2>Нет</h2>
							</c:if></td>
						<td><img class="preview-img" alt="preview_photo"
							src="<c:url value='https://drive.google.com/uc?id=${album.linkToPreviewPhoto}'/>"></td>
						<td><a class="black-link"
							href="<c:url value='/admin/remove/${album.id}'/>">Удалить</a></td>
						<td><a class="black-link"
							href="<c:url value='/${album.id}'/>">Редактировать</a>
						</td>
						<td>
						    <%@include file="load_photo_form.jsp" %>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		