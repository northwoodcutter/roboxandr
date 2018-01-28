package com.roboxandr.Roboxandr.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roboxandr.Roboxandr.PhotoService;
import com.roboxandr.Roboxandr.JacksonService.JacksonService;
import com.roboxandr.Roboxandr.googledrive.GoogleDrive;
import com.roboxandr.Roboxandr.googledrive.GoogleDriveImpl;
import com.roboxandr.Roboxandr.models.Photo;

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	private PhotoService mPhotoService;

	private JacksonService jacksonService;

	@Autowired(required = true)
	@Qualifier(value = "photoService")
	public void setPhotoService(PhotoService photoService) {
		this.mPhotoService = photoService;
	}

	@Autowired(required = true)
	@Qualifier(value = "jacksonService")
	public void setJacksonService(JacksonService jacksonService) {
		this.jacksonService = jacksonService;
	}

	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFiles(@RequestParam(value = "albumFolderId", required = false) String albumFolderId,
			@RequestParam(value = "files", required = false) MultipartFile[] files) throws IOException {
		if (files != null) {
			GoogleDrive googleDrive = new GoogleDriveImpl();
			for (MultipartFile multipartFile : files) {
				this.mPhotoService.addPhoto(albumFolderId, googleDrive.uploadFile(multipartFile, albumFolderId));
			}
			List<Photo> photos = this.mPhotoService.getPhotosByGoogleDriveAlbumId(albumFolderId);
			this.jacksonService.toJson(photos);
			return this.jacksonService.toJson(photos);
		} else {
			return "file is not uploaded!";
		}
	}

}
