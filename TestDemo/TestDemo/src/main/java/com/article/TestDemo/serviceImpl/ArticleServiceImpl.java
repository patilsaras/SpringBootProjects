package com.article.TestDemo.serviceImpl;

import org.springframework.stereotype.Service;

import com.article.TestDemo.service.ArticleService;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Override
	public String parseHtmlnUpdate(String id) throws IOException {

		//File input = new File("C:/Users/CKolambe/Downloads/TestDemo/TestDemo/src/main/webapp/index.html");		//filepath
		StringBuffer sb=new StringBuffer("E:\\CodeBase\\Code2\\TestDemo\\src\\main\\webapp\\");
		sb.append(id).append(".html");
		System.out.println(sb);
		File input = new File(sb.toString());		//filepath

		Document doc = Jsoup.parse(input, "UTF-8");

		Elements content = doc.getElementsByTag("h1");	//tag which we want to read. Here we want by class so use that method - getElementsByClass(String className)
		
		 //content.text().matches("<h>(.*)</h>");
		if (content.hasText()) 
			return content.outerHtml().toString();
		
	return content.toString();
}
}