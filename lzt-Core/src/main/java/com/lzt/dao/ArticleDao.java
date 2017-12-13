package com.lzt.dao;


import java.util.List;
import java.util.Map;

import com.lzt.entity.Article;

public interface ArticleDao {
	
	public Article saveArt(Article art);
	
	public Map<String,Object> getPageArticle(Integer start,Integer pagesize);

	public Map<String,Object> getPageArticleList(Integer start,Integer pagesize);

	public Article getdetail(Integer id);

	public Integer deleteById(List<Integer> idList);

	public Integer getCountByTypeId(Integer typeId);



}
