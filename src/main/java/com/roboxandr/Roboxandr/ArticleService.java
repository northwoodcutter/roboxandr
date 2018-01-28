package com.roboxandr.Roboxandr;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.roboxandr.Roboxandr.models.Article;

public interface ArticleService {
      void addArticle(Article article, MultipartFile file);
      void updateArticle(Article article);
      void removeArticle(int id);
      Article getArticleById(int id);
      List<Article> getArticles();
      Article getLastArticle();
      void loadNewsPhoto(MultipartFile file, String path, String fileName);
}
