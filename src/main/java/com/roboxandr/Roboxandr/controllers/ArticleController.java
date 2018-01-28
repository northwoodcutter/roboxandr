package com.roboxandr.Roboxandr.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.roboxandr.Roboxandr.ArticleService;
import com.roboxandr.Roboxandr.JacksonService.JacksonService;
import com.roboxandr.Roboxandr.models.*;

@Controller
public class ArticleController {
	private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

	private ArticleService mArticleService;

	@Autowired(required = true)
	@Qualifier(value = "articleService")
	public void articleService(ArticleService articleService) {
		this.mArticleService = articleService;
	}

	private JacksonService jacksonService;

	@Autowired(required = true)
	@Qualifier(value = "jacksonService")
	public void setJacksonService(JacksonService jacksonService) {
		this.jacksonService = jacksonService;
	}

	@RequestMapping(value = "/admin/article/add", method = RequestMethod.POST)
	public String addArticle(@RequestParam("file") MultipartFile file, @ModelAttribute("article") Article article,
			HttpServletRequest request) {
		if(!file.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/resources/images/news");
			String fileName = UUID.randomUUID().toString()+".jpg";
			this.mArticleService.loadNewsPhoto(file, path, fileName);
			article.setLinkToPhoto(fileName);
		}
		this.mArticleService.addArticle(article, file);
		return "redirect:/admin";
	}

	@RequestMapping(value = "article/detail", method = RequestMethod.GET, produces = {"text/plain; charset=UTF-8"})
	@ResponseBody
	public String detailArticle(@RequestParam(value = "newsId", required = false) int id) {

		return this.jacksonService.toJson(this.mArticleService.getArticleById(id));
	}
	
	@RequestMapping(value = "/admin/article/remove/{id}")
	public String removeArticle(@PathVariable("id") int id) {
		this.mArticleService.removeArticle(id);
		return "redirect:/admin";
	}
}
