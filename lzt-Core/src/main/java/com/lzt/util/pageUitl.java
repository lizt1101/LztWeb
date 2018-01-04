package com.lzt.util;

import com.lzt.entity.Article;

public class pageUitl {
	
	
	/**
	 * 作分页插件
	 * @param page 分页信息
	 * @param url 链接
	 * @return
	 */
	public static String getPage(Page page,String url,String param){
		Long pageNum = page.getPageNum();  //当前页
		Long pageSize = page.getPageSize(); //每页大小
		Long allCount = page.getAllCount(); //总数
		Long totalPage = page.getTotalPage(); //总页数
		if(totalPage == 1){
			return "已显示全部内容";
		}else{
			StringBuffer pageSb = new StringBuffer();
			if(pageNum==1){
				pageSb.append("<li class='disabled'><a href=#>第一页</a></li>");
			}else{	
				pageSb.append("<li><a href="+url+"?Start=1&"+param+">第一页</a></li>");
			}
			if(pageNum > 1){
				pageSb.append("<li><a href="+url+"?Start="+(pageNum-1)+"&"+param+">上一页</a></li>");
			}else{
				pageSb.append("<li class='disabled'><a href=#>上一页</a></li>");
			}
			for(long i=pageNum-2;i<=pageNum+2;i++){
				if(i<1 || i>totalPage){
					continue;
				}
				if(i==pageNum){
					pageSb.append("<li class='active'><a href="+url+"?Start="+i+"&"+param+">"+i+"</a></li>");
				}else{
					pageSb.append("<li><a href="+url+"?Start="+i+"&"+param+">"+i+"</a></li>");
				}
			}
			if(pageNum < totalPage){
				pageSb.append("<li><a href="+url+"?Start="+(pageNum+1)+"&"+param+">下一页</a></li>");
			}else{
				pageSb.append("<li class='disabled'><a href=#>下一页</a></li>");
			}
			if(pageNum == totalPage){
				pageSb.append("<li class='disabled'><a href=#>最后一页</a></li>");
			}else{
				pageSb.append("<li><a href="+url+"?Start="+totalPage+"&"+param+">最后一页</a></li>");
			}
			return pageSb.toString();
		}	
	}

	public static String getLastAndNextPage(Article lastarticle,Article nextarticle,String url){
		StringBuffer sb = new StringBuffer();
		if (lastarticle == null || lastarticle.getAid() == null) {
			sb.append("<p>上一篇：神马都木有</p>");
		} else {
			sb.append("<p>上一篇：<a target=\"view_window\"  href='" + url + "/article/getArtDetails/" + lastarticle.getAid() + ".do'>"
					+ lastarticle.getTitle() + "</a></p>");
		}
		if (nextarticle == null || nextarticle.getAid() == null) {
			sb.append("<p>下一篇：神马都木有</p>");
		} else {
			sb.append("<p>下一篇：<a target=\"view_window\"  href='" + url + "/article/getArtDetails/" + nextarticle.getAid() + ".do'>"
					+ nextarticle.getTitle() + "</a></p>");
		}
		return sb.toString();
	}

}
