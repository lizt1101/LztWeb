package com.lzt.controller;

import com.lzt.Bo.PingCountBo;
import com.lzt.Bo.ReadCountBo;
import com.lzt.entity.Url;
import com.lzt.entity.User;
import com.lzt.entity.WebContent;
import com.lzt.exception.LztException;
import com.lzt.service.*;
import com.lzt.solrEntity.ArticleResult;
import com.lzt.system.RestServer;
import com.lzt.util.Base64Util;
import com.lzt.util.JsonUtil;
import com.lzt.util.Page;
import com.lzt.util.pageUitl;
import com.lzt.vo.ArtListVo;
import com.lzt.vo.ArtVo;
import com.lzt.vo.MessageVo;
import com.lzt.vo.WebVo;
import com.sun.tools.internal.ws.wsdl.document.Documentation;
import org.apache.solr.client.solrj.SolrServerException;
import org.hibernate.type.IntegerType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
	
	public static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArtCountService artCountService;
	@Autowired
	private WebService webService;
	@Autowired
	private UserService userService;
	@Autowired
	private UrlService urlService;

	@RequestMapping("/toIndex.do")
	public String toIndex(@RequestParam(value="Start",required=false) String start,
						  @RequestParam(value="type",required=false) String type,
						  @RequestParam(value="typeName",required = false) String typeName,
						  @RequestParam(value="time",required=false) String time,
						  @RequestParam(value="key",required=false) String key,HttpServletRequest req, HttpServletRequest res) throws ParseException {

		log.info("请求主页");
		String type1 =type;
		String time1 = null;
		if(start==null || "".equals(start.trim())){
			start = "1";
		}
		if(type !=null && !type.equals("") && type!=""){
		    type = Base64Util.getFromBase64(type).substring(3);
        }
        if(time !=null && !time.equals("") && time!=""){
			time1 = time.replaceAll(" ","+");
			time = Base64Util.getFromBase64(time.replaceAll(" ","+")).substring(3);
		}
		Map<String,Object> articleMap = new HashMap<String,Object>();
		if(key !=null && !key.equals("") && key!=""){
			try {
				articleMap = articleService.getSerachArt(key,Integer.parseInt(start),10);
			} catch (IOException e) {//跳转到失败页面
				e.printStackTrace();
			} catch (SolrServerException e) {
				e.printStackTrace();
			}
		}else{
			articleMap = articleService.getPageArticleList(Integer.parseInt(start),10, type,time);
		}
		Page page = (Page) articleMap.get("page");
		StringBuffer stringBuffer = new StringBuffer();
		if(type !=null && !type.equals("") && type!=""){
			req.setAttribute("tishi","搜索<span style='color:red'>"+Base64Util.getFromBase64(typeName).substring(3)+"</span>结果如下");
			stringBuffer.append("type="+type1+"&typeName="+typeName);
		}
		if(time !=null && !time.equals("") && time!=""){
			req.setAttribute("tishi","搜索<span style='color:red'>"+time+"</span>结果如下");
			stringBuffer.append("time="+time1+"&");
		}
		if(key !=null && !key.equals("") && key!=""){
			req.setAttribute("tishi","搜索<span style='color:red'>"+key+"</span>结果如下");
			stringBuffer.append("key="+key+"&");
		}
		String pageBean = pageUitl.getPage(page,req.getContextPath()+"/toIndex.do",stringBuffer.toString());
		req.setAttribute("pageBean",pageBean);
		if(key !=null && !key.equals("") && key!=""){
			req.setAttribute("content","content/serachArticleList.jsp");
			List<ArticleResult> serachlist = (List<ArticleResult>)articleMap.get("artList");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(ArticleResult articleResult:serachlist){
				articleResult.setCreate_time(sdf.format(sdf1.parse(articleResult.getCreate_time().substring(0,19))));
				if(articleResult.getMy_content().length()>160){
					articleResult.setMy_content(articleResult.getMy_content().substring(0,160)+"&nbsp;&nbsp;&nbsp;&nbsp;<a target=\"view_window\" href=\"/lztWeb/article/getArtDetails/"+articleResult.getId()+".do\">查看更多>></a>");
				}
			}
			req.setAttribute("artList",articleMap.get("artList"));
		}else{
			req.setAttribute("content","content/pageArticleList.jsp");
			List<ArtVo> artVoList = (List<ArtVo>)articleMap.get("artList");
			List<ArtListVo> artListVoList = new ArrayList<ArtListVo>();
			if(artVoList.size()>0){
				for (ArtVo a:artVoList) {
					ArtListVo artListVo = new ArtListVo();
					artListVo.setId(a.getId());
					artListVo.setTitle(a.getTitle());
					artListVo.setCreateTime(a.getCreateTime());
					artListVo.setUserName(a.getUserName());
					artListVo.setNickName(a.getNickName());
					artListVo.setReadCount(artCountService.getArtReadCount(a.getId(),1));
					artListVo.setPingCount(artCountService.getArtReadCount(a.getId(),2));
			/*获取图片*/
					Document doc = Jsoup.parse(a.getBcontent());
					String[] imgs = {"img[src$=.jpg]","img[src$=.png]","img[src$=.gif]","img[src$=.jpeg]","img[src$=.bmp]"};
					for(int i =0;i<imgs.length;i++){
						Elements jpgs = doc.select(imgs[i]); //　查找图片
						if(jpgs.size()>0){
							String imgurl = jpgs.get(0).toString();
							int first = imgurl.indexOf("\"");
							int end = imgurl.indexOf("\"",first+1);
							String url = imgurl.substring(first+1,end);
							artListVo.setImgUrl(url);
							if(a.getContent().length() > 120){
								artListVo.setContent(a.getContent().substring(0,120)+"&nbsp;&nbsp;&nbsp;&nbsp;<a target=\"view_window\"  href=\"/lztWeb/article/getArtDetails/"+a.getId()+".do\">查看更多>></a>");
							}else{
								artListVo.setContent(a.getContent());
							}
							break;
						}else{
							if(a.getContent().length() > 180){
								artListVo.setContent(a.getContent().substring(0,180)+"&nbsp;&nbsp;&nbsp;&nbsp;<a target=\"view_window\" href=\"/lztWeb/article/getArtDetails/"+a.getId()+".do\">查看更多>></a>");
							}else{
								artListVo.setContent(a.getContent());
							}
							break;
						}
					}
					artListVoList.add(artListVo);
				}
			}
			req.setAttribute("artList",artListVoList);
		}
		/*滚动字幕*/
		req.setAttribute("gunText",webService.getWebConttent(2).getWebContent());
		return "Index";
	}

	@RequestMapping("/login.do")
	public String test02(){
		return "login";

	}

	/**
	 * 关于本站
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/aboutWeb.do")
	public String aboutWeb(HttpServletRequest req, HttpServletResponse res){
		log.info("关于本站");
		RestServer restServer = new RestServer(req,res);
		MessageVo messageVo = new MessageVo();
		User user = userService.getuser(1);
		WebContent webContent = webService.getWebConttent(1);
		WebVo webVo = new WebVo();
		webVo.setContent(webContent.getWebContent());
		webVo.setHeadImg(user.getHeadImg());
		messageVo.setCode(MessageVo.SUCCESS);
		messageVo.setMessage("获取成功！");
		messageVo.setData(webVo);
		restServer.send(JsonUtil.jsonToString(messageVo));
		return null;
	}

	/**
	 * 阅读排行
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/read.do")
	public String read(HttpServletRequest req, HttpServletResponse res){
		log.info("获取阅读排行");
		RestServer restServer = new RestServer(req,res);
		MessageVo messageVo = new MessageVo();
		try {
			List<ReadCountBo> list = articleService.getReadCount();
			messageVo.setCode(MessageVo.SUCCESS);
			messageVo.setMessage("获取排行成功!");
			messageVo.setData(list);
		} catch (LztException e) {
			messageVo.setCode(e.getCode());
			messageVo.setMessage(e.getMessage());
		}
		restServer.send(JsonUtil.jsonToString(messageVo));
		return null;
	}

	/**
	 * 获取评论排行
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/ping.do")
	public String ping(HttpServletRequest req, HttpServletResponse res){
		log.info("获取评论排行");
		RestServer restServer = new RestServer(req,res);
		MessageVo messageVo = new MessageVo();
		try {
			List<PingCountBo> list = articleService.getPingCount();
			messageVo.setCode(MessageVo.SUCCESS);
			messageVo.setMessage("获取排行成功!");
			messageVo.setData(list);
		} catch (LztException e) {
			messageVo.setCode(e.getCode());
			messageVo.setMessage(e.getMessage());
		}
		restServer.send(JsonUtil.jsonToString(messageVo));
		return null;
	}


	/**
	 * 获取链接
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/getUrl.do")
	public String getUrl(HttpServletRequest req, HttpServletResponse res){
		log.info("获取链接");
		RestServer restServer = new RestServer(req,res);
		MessageVo messageVo = new MessageVo();
		List<Url> list = urlService.getUrlList();
		if(list==null){
			messageVo.setMessage("暂无链接");
			messageVo.setCode(MessageVo.ERROR);
		}else{
			messageVo.setCode(MessageVo.SUCCESS);
			messageVo.setMessage("获取链接成功!");
			messageVo.setData(list);
		}
		restServer.send(JsonUtil.jsonToString(messageVo));
		return null;
	}


}
