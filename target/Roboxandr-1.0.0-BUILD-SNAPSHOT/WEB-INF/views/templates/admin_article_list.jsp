<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
			<h1>Статьи</h1>
			<table class="table">
				<tr>
					<td><h5>Заголовок</h5></td>
					<td><h5>Опубликовано на сайте</h5></td>
					<td><h5>Анонс</h5></td>
					<td><h5>Дата создания</h5></td>
					<td><h5>Автор</h5></td>
					<td></td>
					<td></td>
				</tr>
				<c:if test="${!empty articleList}">
				</c:if>
				<c:forEach items="${articleList}" var="article">
					<tr>
						<td>
							<h2>${article.caption}</h2>
						</td>
						<td><c:if test="${article.published==true}">
								<h2>Да</h2>
							</c:if> <c:if test="${article.published!=true}">
								<h2>Нет</h2>
							</c:if></td>
						<td>${article.preview}</td>
						<td>${article.publicationDate}</td>
						<td>${article.author}</td>
						<td><a class="black-link"
							href="<c:url value='admin/article/remove/${article.id}'/>">Удалить</a></td>
						<td><a class="black-link"
							href="<c:url value='/admin/article/article/${article.id}'/>">Редактировать</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		