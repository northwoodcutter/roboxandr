package com.roboxandr.Roboxandr.controllers;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roboxandr.Roboxandr.ArticleService;
import com.roboxandr.Roboxandr.PhotoService;
import com.roboxandr.Roboxandr.JacksonService.JacksonService;
import com.roboxandr.Roboxandr.models.*;

@Controller
public class AdminController {
	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	private static final String ROOT_FOLDER_ID = "1AQVx7b8RuJ2L9yyUIGiXw75sk0tIk5UM";
	private PhotoService mPhotoService;

	@Autowired(required = true)
	@Qualifier(value = "photoService")
	public void setPhotoService(PhotoService photoService) {
		this.mPhotoService = photoService;
	}

	private JacksonService jacksonService;

	@Autowired(required = true)
	@Qualifier(value = "jacksonService")
	public void setJacksonService(JacksonService jacksonService) {
		this.jacksonService = jacksonService;
	}
	
	private ArticleService mArticleService;

	@Autowired(required = true)
	@Qualifier(value = "articleService")
	public void articleService(ArticleService articleService) {
		this.mArticleService = articleService;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@Transactional
	public String albumList(Model model) {
		model.addAttribute("album", new Album());
		model.addAttribute("article", new Article());
		model.addAttribute("albumList", this.mPhotoService.getAlbums());
		model.addAttribute("articleList", this.mArticleService.getArticles());
		return "admin";
	}

	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	public String addAlbum(@ModelAttribute("album") Album album, BindingResult bindingResult, Model model) {
		album.setLinkToPreviewPhoto(ROOT_FOLDER_ID);
		mPhotoService.add¿lbum(album);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateAlbum(@ModelAttribute("album") Album album) {
		mPhotoService.update¿lbum(album);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/photos", method = RequestMethod.GET, produces = { "text/html" })
	@ResponseBody
	public String getPhotos(@RequestParam(value = "albumFolderId", required = false) String albumFolderId) {
		List<Photo> photos = this.mPhotoService.getPhotosByGoogleDriveAlbumId(albumFolderId);
		return this.jacksonService.toJson(photos);
	}

	@RequestMapping(value = "/admin/remove/{id}")
	public String removeAlbum(@PathVariable("id") int id) {
		this.mPhotoService.remove¿lbum(id);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/{id}")
	public String editAlbum(@PathVariable("id") int id, Model model) {
		Album album = this.mPhotoService.getAlbum(id);
		List<Photo> photos = mPhotoService.getPhotosByGoogleDriveAlbumId(album.getAlbumFolderId());
		model.addAttribute("album", this.mPhotoService.getAlbum(id));
		model.addAttribute("photos", photos);
		return "edit";
	}

	@RequestMapping(value = "/removePhotos", method = RequestMethod.POST)
	@ResponseBody
	public String removePhotos(@RequestParam(value = "photoIds") List<String> photoIds) {
		this.mPhotoService.removePhotos(photoIds);
		return "photos deleted";
	}
}
