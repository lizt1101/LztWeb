package com.lzt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lzt.Bo.TimeCountBo;
import com.lzt.Bo.typeCountBo;
import com.lzt.exception.LztException;
import org.apache.solr.client.solrj.SolrServerException;

import com.lzt.dto.ArticleDto;
import com.lzt.entity.Article;
import com.lzt.solrEntity.ArticleResult;

public interface ArticleService {
	
	public Article saveArt(Article article) throws LztException;
	
	public List<ArticleResult> getArt(String keyword, Integer start, Integer pagesize) throws SolrServerException, IOException ;

	public Map<String,Object> getPageArticle(Integer start,Integer pagesize);

	public Map<String,Object> getPageArticleList(Integer start,Integer pagesize,String type,String time);

	public Map<String,Object> getPageArticleList1(Integer start,Integer pagesize);

	public Article getDetail(Integer id);

	public void deleteById(String ids) throws LztException;

	public void getCountByTypeId(Integer typeId) throws LztException;

	public Article getLastDetail(Integer id);

	public Article getNextDetail(Integer id);

	public List<typeCountBo> getTypeCount();

	public List<TimeCountBo> getTimeCount();
}
