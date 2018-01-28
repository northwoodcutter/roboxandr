package com.roboxandr.Roboxandr;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.roboxandr.Roboxandr.models.Article;

public class ArticleServiceImpl implements ArticleService {
	private ArticleDAO articleDAO;

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	@Transactional
	public void addArticle(Article article, MultipartFile file) {
		this.articleDAO.addArticle(article, file);
	}

	@Override
	@Transactional
	public void updateArticle(Article article) {
		this.articleDAO.updateArticle(article);
	}

	@Override
	@Transactional
	public void removeArticle(int id) {
		this.articleDAO.removeArticle(id);
	}

	@Override
	@Transactional
	public Article getArticleById(int id) {
		return this.articleDAO.getArticleById(id);
	}

	@Override
	public List<Article> getArticles() {
		return this.articleDAO.getArticles();
	}

	@Override
	public Article getLastArticle() {
		return this.articleDAO.getLastArticle();
	}

	@Override
	public void loadNewsPhoto(MultipartFile file, String path, String fileName) {
		this.articleDAO.loadNewsPhoto(file, path, fileName);
	}

}
