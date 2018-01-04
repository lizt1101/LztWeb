package com.lzt.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lzt.Bo.TimeCountBo;
import com.lzt.Bo.typeCountBo;
import com.lzt.entity.User;
import com.lzt.exception.LztException;
import com.lzt.vo.MessageVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.CollectionUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzt.dao.ArticleDao;
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
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		art.setUserId(user.getId());
		art.setUpdateBy(user.getId());
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
	public Map<String,Object> getPageArticleList(Integer start, Integer pagesize,String type,String time) {
		Integer typeId = null;
		if(type != null){
			typeId = Integer.parseInt(type);
		}
		Map<String,Object> resultMap = articleDao.getPageArticleList(start, pagesize,typeId,time);
		return resultMap;
	}

	@Override
	public Map<String, Object> getPageArticleList1(Integer start, Integer pagesize) {
		Map<String,Object> resultMap = articleDao.getPageArticleList(start, pagesize,null,null);
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


	@Override
	public Article getLastDetail(Integer id) {
		return articleDao.getLastdetail(id);
	}

	@Override
	public Article getNextDetail(Integer id) {
		Article a = articleDao.getNextdetail(id);
		return a;
	}

	@Override
	public List<typeCountBo> getTypeCount() {
		return articleDao.getTypeCount();
	}

	@Override
	public List<TimeCountBo> getTimeCount() {
		return articleDao.getTimeCount();
	}
}















