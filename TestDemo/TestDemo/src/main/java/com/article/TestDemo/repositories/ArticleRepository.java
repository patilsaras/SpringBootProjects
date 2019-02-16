package com.article.TestDemo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.article.TestDemo.bean.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {
  Article findBy_id(String id);
}