package com.lzt.controller;

import com.lzt.service.ArtCountService;
import com.lzt.service.ArticleService;
import com.lzt.util.Base64Util;
import com.lzt.util.Page;
import com.lzt.util.pageUitl;
import com.lzt.vo.ArtListVo;
import com.lzt.vo.ArtVo;
import com.sun.tools.internal.ws.wsdl.document.Documentation;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
	
	public static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArtCountService artCountService;

	@RequestMapping("/toIndex.do")
	public String toIndex(@RequestParam(value="Start",required=false) String start,
						  @RequestParam(value="type",required=false) String type,
						  @RequestParam(value="typeName",required = false) String typeName,
						  @RequestParam(value="time",required=false) String time,HttpServletRequest req, HttpServletRequest res){
		if(start==null || "".equals(start.trim())){
			start = "1";
		}
		if(type != null){
		    type = Base64Util.getFromBase64(type).substring(3);
        }
        if(time != null){
			time = Base64Util.getFromBase64(time.replaceAll(" ","+")).substring(3);
		}
		Map<String,Object> articleMap = articleService.getPageArticleList(Integer.parseInt(start),10, type,time);
		Page page = (Page) articleMap.get("page");
		StringBuffer stringBuffer = new StringBuffer();
		if(type != null){
			req.setAttribute("tishi","搜索<span style='color:red'>"+Base64Util.getFromBase64(typeName).substring(3)+"</span>结果如下");
			stringBuffer.append("type="+type+"&");
		}
		if(time != null){
			req.setAttribute("tishi","搜索<span style='color:red'>"+time+"</span>结果如下");
			stringBuffer.append("time="+time+"&");
		}
		String pageBean = pageUitl.getPage(page,req.getContextPath()+"/toIndex.do",stringBuffer.toString());
		req.setAttribute("pageBean",pageBean);
		req.setAttribute("content","content/pageArticleList.jsp");
		List<ArtVo> artVoList = (List<ArtVo>)articleMap.get("artList");
		List<ArtListVo> artListVoList = new ArrayList<ArtListVo>();
		if(artVoList.size()>0){
			for (ArtVo a:artVoList) {
				ArtListVo artListVo = new ArtListVo();
				artListVo.setId(a.getId());
				if(a.getContent().length() > 80){
					artListVo.setContent(a.getContent().substring(0,80)+"......");
				}else{
					artListVo.setContent(a.getContent());
				}
				artListVo.setTitle(a.getTitle());
				artListVo.setCreateTime(a.getCreateTime());
				artListVo.setUserName(a.getUserName());
				artListVo.setReadCount(artCountService.getArtReadCount(a.getId()));
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
						break;
					}
				}
				artListVoList.add(artListVo);
			}
		}
		req.setAttribute("artList",artListVoList);
		return "Index";
	}

	@RequestMapping("/login.do")
	public String test02(){
		return "login";

	}

}
