$(document).ready(function() {
	$('#photo-carousel').hide();
	function getPhotos(albumFolderId) {
		$.ajax({
			type : 'GET',
			sync : false,
			url : 'photos',
			data : ({
				albumFolderId : albumFolderId
			}),
			success : function(response) {
				 var jsonObj = $.parseJSON([response]);
				 for (var i = 0; i < jsonObj.length; i++) {
					if(i==0){
						$('.carousel-inner').append("<div class='item active'> " +
								"<img src=\"https://drive.google.com/uc?id="+
								jsonObj[i].linkToPhoto+"\" alt=\"...\"></div>");
					}
					else{ 
						$('.carousel-inner').append("<div class='item'> " +
								"<img src=\"https://drive.google.com/uc?id="+
								jsonObj[i].linkToPhoto+"\" alt=\"...\"></div>");
					}
				}
				jsonObj=null;
			},
			error : function() {
				alert('Error while request. url');
			}
		});
	}
	
	$(".preview-container").click(function() {
		var albumFolderId = $(this).attr('id');
		getPhotos(albumFolderId);
		$('#photo-carousel').show(300);
	});
	
	$(".preview-container").mouseenter(function() {
		$(this).append("<div class=\"img-overlay\"></div>");
	});
	
	$(".preview-container").mouseleave(function() {
		var id = $(this).attr('id');
		$("#"+id).empty();
	});
	
	$("#carouselRemoveBtn").click(function() {
		$('#photo-carousel').hide();
	});
	
	
	// upload photos
	var files = [];

	$(document).on("change", "#fileLoader", function(event) {
		 files = event.target.files;
		 var albumFolderId =$(".upload-form").attr('id');
	});

	$(".upload-form").submit(function(e){
	    var albumFolderId = $(this).attr('id');
	    var progressBarId ="#progressBar"+albumFolderId;
	    var progressId ="#progress"+albumFolderId;
	    var formData= new FormData();
	    formData.append('albumFolderId', albumFolderId);
	    for (var i = 0; i < files.length; i++) {
	    	 formData.append('files', files[i]);
	    	 $('#filesList'+albumFolderId).append("<p>"+files[i].name+"</p>");
		}
	    
	    $.ajax({
	    	xhr: function () {
	            var xhr = new window.XMLHttpRequest();
	            xhr.upload.addEventListener("progress", function (evt) {
	                if (evt.lengthComputable) {
	                    var percentComplete = evt.loaded / evt.total;
	                    console.log(percentComplete);
	                    $(progressBarId).css({
	                        width: percentComplete*100 + '%'
	                    });
	                }
	            }, false);
	            return xhr;
	        },
	    	type: "POST",
	        url: "uploadFiles",
	        data: formData,
	        contentType: false,
	        processData : false,
	        cache: false,
	        beforeSend: function(){
	            $(progressBarId).show();
	            $(progressId).show();
	        },
	        success: function(data){
	        	$(progressBarId).hide();
	        	$(progressId).hide();
	        	$('#filesList').empty();
	        	$('#uploadButton').show();
	        	console.log(data);
	        	var photos = JSON.parse(data)
	        	$('#photoList').empty();
	        	for (var i = 0; i < photos.length; i++) {
	        		console.log(photos[i].linkToPhoto)
	        		$('#photoList').append(
		        			"<tbody><tr class=\"photo-table-row\"><td><input class=\"photo-sheckbox\" " +
		        			"type=\"checkbox\" data-preview-id=\""+photos[i].linkToPhoto+"\"></td>" +
		        			"<td><img class=\"preview-img\" alt=\"photo\" " +
		        			"src=\"https://drive.google.com/uc?id="+
		        			photos[i].linkToPhoto+"\"></td></tr></tbody>"		
		        	);
	    	    }
	        },
	        error: function(error){
	        	$('#filesList').append("<p>"+error+"</p")
	        }
	     });
	    return false;
	});
	
	// remove photos
	var photoData=new FormData();
	$("#deletePhotosBtn").click(function() {
		$(".photo-sheckbox").each(function() {
			if($(this).is(":checked")){
			var photoId = $(this).attr('data-preview-id');
			photoData.append('photoIds',photoId);
			}
		});
		deletePhotos(photoData);
	});
	function deletePhotos(photoData) {
		$.ajax({
			type : 'POST',
			url : 'removePhotos',
			contentType: false,
	        processData : false,
	        cache: false,
	        data:photoData,
			success : function(data) {
				$(".photo-table-row").each(function() {
					var checkbox= $(this).find(".photo-sheckbox");
					if(checkbox.is(":checked")){
					   $(this).remove();
					}
				});
			photoData.delete('photoIds');
			},
			error : function() {
			}
		});
	}
	
	// select all checkboxes
	$('#selectAllCheckbox').change(function() {
	    var checkboxes = $('.photo-sheckbox');;
	    checkboxes.prop('checked', $(this).is(':checked'));
	});
	
	// select one checkbox as radiobutton
	$(function(){
	      $("input:checkbox.select-prewiev-checkbox").each(function(){
	        $(this).change(function(){
	            $("input:checkbox.select-prewiev-checkbox").not($(this)).prop("checked",false);
	            $(this).prop("checked", $(this).prop("checked"));
	            var previewId = $(this).attr('data-preview-id');
	            $('#previewPhotoIdInput').attr('value',previewId)
	        });   
	      });
	 });
	
	// select tags
	var tags=[];
	$(function(){
	      $(".news-tag").each(function(){
	        $(this).click(function(){
	        	var data = $(this).attr('data-tag');
	        	if($(this).hasClass('active-tag')){
	        		$(this).removeClass('active-tag');
	        		tags.splice(tags.indexOf(data), 1);
	        		console.log(tags.length);
	        	}
	        	else {
	        		$(this).addClass('active-tag');
	        		tags.push(data);
	        		console.log(tags.length);
	        	}
	        	filterNews(tags)
	        });   
	      });
	 });
	
	// news filter
	function filterNews(tags) {
		$(".news-item").each(function(){
			var notThis = $(".news-item").not($(this));
			if(tags.length>0){
			var data = $(this).attr('data-tag');
			console.log(data);
			for (var i = 0; i < tags.length; i++) {
				if(tags[i]==data){
					$(this).show();
					if(notThis.hasClass('active-tag')==false){
					   notThis.hide();
				    }
			    }
				else{
					$(this).hide();
					if(notThis.hasClass('active-tag')){
				       notThis.show();
					}
				}
			  }
		    }
			else{
				$(".news-item").show();
			}
	  });
	}
	
	//select detail news
	
	$(".link-to-detail").click(function() {
		var id = $(this).attr('data-selected-id');
		console.log("link-to-detail");
		selectDetailNews(id)
	});
	
	function selectDetailNews(id) {
		$.ajax({
			type : 'GET',
			sync : false,
			dataType: 'text',
			url : 'article/detail',
			data : ({
				newsId : id
			}),
			success : function(response) {
				 jsonObj = $.parseJSON([response]);
				 console.log(jsonObj);
				 $(".main-news-date").empty();
				 $(".main-news-date").append(jsonObj.publicationDate);
				 $(".select-tags").empty();
				 $(".select-tags").append(jsonObj.hashtags);
				 $(".selected-caption").empty();
				 $(".selected-caption").append(jsonObj.caption);
				 $(".selected-content").empty();
				 $(".selected-content").append(jsonObj.content);
				 $(".selected-author").empty();
				 $(".selected-author").append(jsonObj.author);
			},
			error : function() {
				alert('Error while request. url');
			}
		});
	}
	
});

$(document).mouseup(function(e) {
	var div = $("#photo-carousel");
	if (!div.is(e.target)
	    && div.has(e.target).length === 0) {
		div.hide(300);
		$('.carousel-inner').empty();
	}
});




