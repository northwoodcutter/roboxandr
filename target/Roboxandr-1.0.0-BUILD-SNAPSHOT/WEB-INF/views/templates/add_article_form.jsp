<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container input-group">
	<div class="row">
		<h1>Добавление статьи</h1>
		<form:form method="POST" action="admin/article/add"
			commandName="article" enctype="multipart/form-data">
			<fieldset>
				<div>
					<form:label path="caption">Заголовок</form:label>
					<form:input class="form-control" path="caption" />
				</div>
				<div>
					<form:label path="hashtags">теги</form:label>
					<form:input class="form-control" path="hashtags" />
				</div>
				<div>
					<form:label path="publicationDate">Дата публикации</form:label>
					<form:input type="date" path="publicationDate" />
				</div>
				<div>
					<form:label path="">Прикрепить фотографию к новости</form:label>
					<input type="file" name="file" />
				</div>
				<div>
					<form:label path="published">Публиковать на сайт</form:label>
					<form:checkbox path="published" />
				</div>
				<div>
					<form:label path="author">Автор</form:label>
					<form:input class="form-control" path="author" />
				</div>
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#panel5">Текстовый
							режим</a></li>
					<li><a data-toggle="tab" href="#panel6">HTML режим</a></li>
				</ul>
				<div class="tab-content">
					<div id="panel5" class="tab-pane fade in active">
						<div>
							<form:button class="btn btn-default">
								<span class="glyphicon glyphicon-font"></span>
							</form:button>
							<form:button class="btn btn-default">
								<span class="glyphicon glyphicon-bold"></span>
							</form:button>

							<form:button class="btn btn-default">
								<span class="glyphicon glyphicon-italic"></span>
							</form:button>
						</div>
						<div>
							<form:label path="preview">Анонс статьи</form:label>
							<form:textarea class="form-control" path="preview" />
						</div>
						<div>
							<form:label path="content">Основной текст</form:label>
							<form:textarea class="form-control" path="content" />
						</div>
					</div>
					<div id="panel6" class="tab-pane fade">
						<div>
							<form:label path="preview">Анонс статьи</form:label>
							<form:textarea class="form-control" path="preview" />
						</div>
						<div>
							<form:label path="content">Основной текст</form:label>
							<form:textarea class="form-control" path="content" />
						</div>
					</div>
				</div>
				<input type="submit" value="сохранить" />
			</fieldset>
		</form:form>
	</div>
</div>