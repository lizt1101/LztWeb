package com.lzt.util;

public class pageUitl {
	
	
	/**
	 * 作分页插件
	 * @param page 分页信息
	 * @param url 链接
	 * @return
	 */
	public static String getPage(Page page,String url){
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
				pageSb.append("<li><a href="+url+"?Start=1>第一页</a></li>");
			}
			if(pageNum > 1){
				pageSb.append("<li><a href="+url+"?Start="+(pageNum-1)+">上一页</a></li>");
			}else{
				pageSb.append("<li class='disabled'><a href=#>上一页</a></li>");
			}
			for(long i=pageNum-2;i<=pageNum+2;i++){
				if(i<1 || i>totalPage){
					continue;
				}
				if(i==pageNum){
					pageSb.append("<li class='active'><a href="+url+"?Start="+i+">"+i+"</a></li>");
				}else{
					pageSb.append("<li><a href="+url+"?Start="+i+">"+i+"</a></li>");
				}
			}
			if(pageNum < totalPage){
				pageSb.append("<li><a href="+url+"?Start="+(pageNum+1)+">下一页</a></li>");
			}else{
				pageSb.append("<li class='disabled'><a href=#>下一页</a></li>");
			}
			if(pageNum == totalPage){
				pageSb.append("<li class='disabled'><a href=#>最后一页</a></li>");
			}else{
				pageSb.append("<li><a href="+url+"?Start="+totalPage+">最后一页</a></li>");
			}
			return pageSb.toString();
		}	
	}

}
