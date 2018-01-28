<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${!empty articleList}">
	<div class="tags">
		<c:forEach items="${articleList}" var="article">
			<c:if test="${article.published==true}">
				<span class="news-tag select-none" data-tag="${article.hashtags}">${article.hashtags}</span>
			</c:if>
		</c:forEach>
	</div>
	<c:forEach items="${articleList}" var="article">
		<c:if test="${article.published==true}">
			<div class="news-item" data-tag="${article.hashtags}">
			    <div>${article.hashtags}</div>
				<div class="news-caption green-color">${article.caption}</div>
				<div class="news-preview">${article.preview}</div>
				<div class="red-color">${article.publicationDate}</div>
				<div>
					<p class="text-right">
						<a class="link-to-detail" href="#news" data-selected-id="${article.id}">Читать далее...<span
							class="glyphicon glyphicon-arrow-right red-color"></span></a>
					</p>
				</div>
			</div>
		</c:if>
	</c:forEach>
</c:if>

