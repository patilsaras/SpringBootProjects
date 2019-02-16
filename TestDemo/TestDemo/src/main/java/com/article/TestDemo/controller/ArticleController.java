package com.article.TestDemo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.article.TestDemo.bean.Article;
import com.article.TestDemo.repositories.ArticleRepository;
import com.article.TestDemo.service.ArticleService;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController{
	
  @Autowired
  private ArticleRepository articleRepository;
  
  @Autowired
  private ArticleService articleService;
  
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateAllArticles() throws IOException {
		ArrayList<Article> al =new ArrayList<Article>();
		al= (ArrayList<Article>) articleRepository.findAll();
		System.out.println(al.size());
		for(int i=0;i<al.size();i++)
		{
			String s=articleService.parseHtmlnUpdate(al.get(i).get_id());
			System.out.println(s);
	        String c = StringUtils.substringBetween(s, "<h1>", "</h1>");
			al.get(i).setContent(c);
			articleRepository.save(al.get(i));
			
			
		}
		
	}
  
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void createArticle() {
		try {
			
     		String s = "E:\\CodeBase\\Code2\\TestDemo\\src\\main\\rss\\Test2.rss";
			File fXmlFile = new File(s);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					Article article = new Article();

					article.setLink(eElement.getElementsByTagName("link").item(0).getTextContent());
					article.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
					article.set_id(eElement.getElementsByTagName("guid").item(0).getTextContent());
					article.setLink(eElement.getElementsByTagName("link").item(0).getTextContent());
					article.setPubdate(eElement.getElementsByTagName("pubDate").item(0).getTextContent());
					article.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
					article.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
					article.setContent(eElement.getElementsByTagName("content").item(0).getTextContent());
					System.out.println(article.toString());

					articleRepository.save(article);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * @RequestMapping(value = "/parseHtmlnUpdate", method = RequestMethod.GET)
	 * public void parseHtmlnUpdate() throws IOException {
	 * articleService.parseHtmlnUpdate(); }
	 */
	
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getArticleById(@PathVariable("id") String id)
  {
	  Article article = new Article();
	  article=articleRepository.findBy_id(id);
	  String page=article.get_id();
	  return page;
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public void modifyArticleById(@PathVariable("id") String id, @Valid @RequestBody Article article) {
	  article.set_id(id);
    articleRepository.save(article);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteArticle(@PathVariable String id) {
	  articleRepository.delete(articleRepository.findBy_id(id));
  }

}