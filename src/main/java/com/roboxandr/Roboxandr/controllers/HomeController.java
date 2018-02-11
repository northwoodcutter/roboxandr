package com.roboxandr.Roboxandr.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roboxandr.Roboxandr.ArticleService;
import com.roboxandr.Roboxandr.PhotoService;
import com.roboxandr.Roboxandr.models.Article;

@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	private PhotoService mPhotoService;

	@Autowired(required = true)
	@Qualifier(value = "photoService")
	public void setPhotoService(PhotoService photoService) {
		this.mPhotoService = photoService;
	}
	
	private ArticleService mArticleService;

	@Autowired(required = true)
	@Qualifier(value = "articleService")
	public void articleService(ArticleService articleService) {
		this.mArticleService = articleService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional
	public String home(Model model) {
		model.addAttribute("albumList", this.mPhotoService.getAlbums());
		model.addAttribute("selectedArticle", this.mArticleService.getLastArticle());
		model.addAttribute("articleList", this.mArticleService.getArticles());
		return "home";
	}
	
}
