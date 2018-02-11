<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row input-group">
	<div class="col-md-4 ">
		<h1>Добавить альбом</h1>
		<form:form method="POST" action="admin/add" commandName="album">
			<fieldset>
				<form:label class="form-control" path="name">Название альбома</form:label>
				<form:input class="form-control" path="name" />
				<form:label class="form-control" path="definition">Описание</form:label>
				<form:textarea class="form-control" path="definition" />
				<form:label class="form-control" path="name">Доступ</form:label>
				<form:checkbox path="published" />
				<input type="submit" class="form-control" value="сохранить" />
			</fieldset>
		</form:form>
	</div>
</div>