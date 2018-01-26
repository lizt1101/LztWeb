package com.lzt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzt.entity.ArtCount;
import com.lzt.entity.Type;
import com.lzt.exception.LztException;
import com.lzt.service.ArtCountService;
import com.lzt.service.TypeService;
import com.lzt.system.RestServer;
import com.lzt.util.JsonUtil;
import com.lzt.vo.ArtDetailsVo;
import com.lzt.vo.MessageVo;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.lzt.dto.ArticleDto;
import com.lzt.entity.Article;
import com.lzt.service.ArticleService;
import com.lzt.solrEntity.ArticleResult;
import com.lzt.util.Page;
import com.lzt.util.pageUitl;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	private final Logger log = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private ArtCountService artCountService;

	/**
	 * 发表修改文章
	 * @param article
	 * @param res
	 * @param req
	 * @return
	 */
	@RequestMapping("/saveArt.do")
	public String saveArticle(Article article, HttpServletRequest res,HttpServletResponse req){
		log.info("保存文章,入参:"+article.toString());
		Article article1 = null;
		MessageVo messageVo = new MessageVo();
		try {
			article1 = articleService.saveArt(article);
			messageVo.setData(article1);
		} catch (LztException e) {
			messageVo.setCode(MessageVo.ERROR);
			messageVo.setMessage(e.getMessage());
		}
		RestServer restServer = new RestServer(res,req);
		restServer.send(JsonUtil.jsonToString(messageVo));
		return null;
	}

	/**
	 * 获取详情
	 * @param artId
	 * @param res
	 * @param req
	 * @return
	 */
	@RequestMapping("/getArtDetails/{artId}")
	public String getArtDetails(@PathVariable("artId") String artId, HttpServletRequest req, HttpServletResponse res){
		log.info("获取前端页面详情,入参:"+artId);
		Article article = articleService.getDetail(Integer.parseInt(artId));
		Type type = typeService.getType(article.getTypeId());
		List<String> keylist = new ArrayList<String>();
		if(article.getSign() != null){
			String[] keys = article.getSign().split(",");
			for(String s:keys){
				keylist.add(s);
			}
		}
		Article lastarticle = articleService.getLastDetail(Integer.parseInt(artId));
		Article nextarticle = articleService.getNextDetail(Integer.parseInt(artId));
		String pageBean = pageUitl.getLastAndNextPage(lastarticle,nextarticle,req.getContextPath());
		req.setAttribute("pageBean",pageBean);
		//增加阅读次数
		ArtCount artCount = new ArtCount();
		artCount.setArtId(Integer.parseInt(artId));
		artCount.setLook(1);
		Integer reads = 0;
		try {
			reads = artCountService.updateArtCount(artCount);
		} catch (LztException e) {
			e.printStackTrace();   //跳转到错误页面
		}
		Integer pings = artCountService.getArtReadCount(Integer.parseInt(artId),2);
		ArtDetailsVo artDetailsVo = new ArtDetailsVo();
		artDetailsVo.setId(article.getAid());
		artDetailsVo.setContent(article.getContent());
		artDetailsVo.setKeys(keylist);
		artDetailsVo.setReads(reads);
		artDetailsVo.setCreateTime(article.getCreateTime());
		artDetailsVo.setTypeName(type.getTypeName());
		artDetailsVo.setTitle(article.getTitle());
		artDetailsVo.setPings(pings);
		req.setAttribute("article",artDetailsVo);
		return "mainPage/artDetails";

	}

	@RequestMapping("/serach.do")
	public String serachArticle(HttpServletRequest res,HttpServletResponse req){
		String keyword = res.getParameter("keyword");
		log.info("搜索文章,关键字:"+keyword);
		try {
			List<ArticleResult> articleResultList= articleService.getArt(keyword, 0, 4);
			for (ArticleResult articleResult : articleResultList) {
				System.out.println("标题:"+articleResult.getMy_title());
			}
			res.setAttribute("articleResultList", articleResultList);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "articleIndex";
	}
	
	/**
	 * 分页查询文章
	 * @param res
	 * @param req
	 * @return
	 */
	/*@RequestMapping("/getArticle.do")
	public String getPageArticle(@RequestParam(value="Start",required=false) String Start,HttpServletRequest res,HttpServletResponse req){
		log.info("分页查询文章开始"+Start);
		if(Start==null){
			Start = "1"; 
		}
		Map<String,Object> artMap= articleService.getPageArticle(Integer.valueOf(Start), 3);
		@SuppressWarnings("unchecked")
		List<Article> artList = (List<Article>) artMap.get("artList");
		for (Article article : artList) {
			article.setContent(article.getContent().substring(0, 30));
		}
		Page page = (Page) artMap.get("page");
		String pageCode = pageUitl.getPage(page, res.getContextPath()+"/getArticle.do");
		res.setAttribute("artList", artList);
		res.setAttribute("pageCode", pageCode);
		return "articleIndex";
		
	}*/
	
	
	
	
	
	

}









