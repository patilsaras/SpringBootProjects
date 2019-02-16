package com.article.TestDemo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "article")
public class Article {
	
  @Id
  public String _id;
  
  public String link;
  public String title;
  public String description;
  public String pubdate;
  public String category;
  public String content;
  
  
  
  public Article()
  {
	  
  }
public Article(String _id, String link, String title, String description,
		String pubdate, String category, String content) {
	super();
	this._id = _id;
	this.link = link;
	this.title = title;
	this.description = description;
	this.pubdate = pubdate;
	this.category = category;
	this.content = content;
}
@Override
public String toString() {
	return "Article [_id=" + _id + ", link=" + link + ", title=" + title
			+ ", description=" + description + ", pubdate=" + pubdate
			+ ", category=" + category + ", content=" + content + "]";
}
public String get_id() {
	return _id;
}
public void set_id(String _id) {
	this._id = _id;
}
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getPubdate() {
	return pubdate;
}
public void setPubdate(String pubdate) {
	this.pubdate = pubdate;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
  
}