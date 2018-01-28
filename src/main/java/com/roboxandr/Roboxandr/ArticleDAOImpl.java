package com.roboxandr.Roboxandr;

import java.io.*;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.roboxandr.Roboxandr.models.Article;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	private static final Logger LOG = LoggerFactory.getLogger(PhotoDAOImpl.class);
	private SessionFactory mFactory;

	public void setFactory(SessionFactory factory) {
		this.mFactory = factory;
	}

	@Override
	public void addArticle(Article article, MultipartFile file) {
		Session session = this.mFactory.getCurrentSession();
		session.persist(article);
	}

	@Override
	public void updateArticle(Article article) {
		Session session = this.mFactory.getCurrentSession();
		session.update(article);
	}

	@Override
	public void removeArticle(int id) {
		Session session = this.mFactory.getCurrentSession();
		Article article = (Article) session.load(Article.class, new Integer(id));
		if (article != null) {
			session.delete(article);
		}
	}

	@Override
	public Article getArticleById(int id) {
		Session session = this.mFactory.getCurrentSession();
		Article article = (Article) session.load(Article.class, new Integer(id));
		return article;
	}

	@Override
	public List<Article> getArticles() {
		Session session = this.mFactory.getCurrentSession();
		List<Article> article = session.createQuery("from Article").list();
		return article;
	}

	@Override
	public Article getLastArticle() {
		Session session = this.mFactory.getCurrentSession();
		int maxId = (Integer) session.createQuery("select max(id) from Article").uniqueResult();
		Article article = (Article) session.createQuery("from Article where id=" + maxId).uniqueResult();
		return article;
	}

	@Override
	public void loadNewsPhoto(MultipartFile file, String path, String fileName) {
		try {
			file.transferTo(new File(path,fileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
