<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form id="${album.albumFolderId}" class="upload-form">
	<fieldset>
		<label class="btn btn-default">
		    <input type="file" name="file" accept="image/*"
			multiple="multiple" id="fileLoader" hidden/>
            <span class="glyphicon glyphicon-folder-open"> 
		</span>
		</label>
		<button id="uploadButton${album.albumFolderId}" type="submit" class="submit-button btn btn-default">
			<span class="glyphicon glyphicon-cloud-upload"></span>
		</button>
		<div id="filesList${album.albumFolderId}" class="file-list">
        </div>
	</fieldset>
</form>
<div id="progress${album.albumFolderId}"
	class="progress progress-striped" style="display: none;">
	<div id="progressBar${album.albumFolderId}"
		class="progress-bar progress-bar-info" role="progressbar"
		aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
		style="width: 0%"></div>
</div>
