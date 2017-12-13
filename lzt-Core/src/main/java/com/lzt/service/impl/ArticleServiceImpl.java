package com.lzt.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lzt.exception.LztException;
import com.lzt.vo.ArtVo;
import com.lzt.vo.MessageVo;
import org.apache.shiro.util.CollectionUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzt.dao.ArticleDao;
import com.lzt.dto.ArticleDto;
import com.lzt.entity.Article;
import com.lzt.service.ArticleService;
import com.lzt.solrClient.CommontData;
import com.lzt.solrClient.SolrClient;
import com.lzt.solrEntity.ArticleField;
import com.lzt.solrEntity.ArticleResult;
import com.lzt.util.DateUtil;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	@Resource
	private SolrClient solrClient;

	@Override
	public Article saveArt(Article art) throws LztException {
		Timestamp nowDate = DateUtil.getTimeStamp();
		if(art.getAid() == null){
			art.setCreateTime(nowDate);
		}
		art.setUpdateTime(nowDate);
		art.setStatus(0);
		art.setUserId(1);
		//保存OR更新
		Article article = articleDao.saveArt(art);
		if(article==null){
			throw new LztException("000002","保存失败");
		}
		//加入solr
		ArticleField af = new ArticleField(article.getAid()
				,article.getTitle(),article.getContentText(),String.valueOf(article.getCreateTime()));
		String result = (String) solrClient.addOrUpdateData(af);
		return article;
	}

	@Override
	public List<ArticleResult> getArt(String keyword, Integer start, Integer pagesize) throws SolrServerException, IOException {
		CommontData<ArticleResult> common = solrClient.serach(keyword, start, pagesize,ArticleResult.class);
		Map<String, Map<String, List<String>>> highlightings = common.getHighlightings();
		List<ArticleResult> artlist = common.getDataList();
		for (ArticleResult articleResult : artlist) {
			 Map<String, List<String>> highlighting = highlightings.get(articleResult.getId());
			 if(!CollectionUtils.isEmpty(highlighting.get("my_content"))){
				 articleResult.setMy_content(highlighting.get("my_content").get(0));
			 }
			 if(!CollectionUtils.isEmpty(highlighting.get("my_title"))){
				 articleResult.setMy_title(highlighting.get("my_title").get(0));
			 }
		}
		return artlist;
	}

	@Override
	public Map<String,Object> getPageArticle(Integer start, Integer pagesize) {
		return articleDao.getPageArticle(start, pagesize);
	}

	@Override
	public Map<String,Object> getPageArticleList(Integer start, Integer pagesize) {
		Map<String,Object> resultMap = articleDao.getPageArticleList(start, pagesize);
		return resultMap;
	}

	@Override
	public Article getDetail(Integer id) {
		Article article = articleDao.getdetail(id);
		return article;
	}

	@Override
	public void deleteById(String ids) throws LztException {
		List<Integer> integerList = new ArrayList<Integer>();
		String[] idss = ids.split(",");
		for(String str:idss){
			integerList.add(Integer.parseInt(str));
		}
		Integer i = articleDao.deleteById(integerList);
		if(i != null && i<1){
			throw new LztException(MessageVo.ERROR,"更新失败");
		}
	}

	@Override
	public void getCountByTypeId(Integer typeId) throws LztException {
		Integer i = articleDao.getCountByTypeId(typeId);
		if(i != null){
			throw new LztException("000003","该分类下有文章,不能删除该分类!");
		}
	}
}















