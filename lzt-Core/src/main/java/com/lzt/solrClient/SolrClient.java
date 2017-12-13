package com.lzt.solrClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lzt.util.Page;

@Component
public class SolrClient {

	@Value("${solr.url}")
	private String URL;

	// 更新与保存
	public Object addOrUpdateData(Object data) {
		UpdateResponse response = null;
		HttpSolrClient client = new HttpSolrClient(URL);
		try {
			response = client.addBean(data);
			client.commit();
		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}
		return response.toString();
	}
	
	//查询
	public <T> CommontData<T> serach(String keyword,Integer start,Integer pagesize,Class<T> tClass) throws SolrServerException, IOException{
		CommontData<T> common = new CommontData<T>();
		HttpSolrClient server = new HttpSolrClient(URL);
		SolrQuery query = new SolrQuery();
		query.setQuery("my_content:"+keyword+"\nmy_title:"+keyword);
		//query.setQuery("*:*");
		//分页
		query.setSort("create_time",SolrQuery.ORDER.desc);//定义分页依据
		query.setStart(start);
		query.setRows(pagesize);
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("my_content");
		query.addHighlightField("my_title");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		
		QueryResponse response = server.query(query);
		server.commit();
		List<T> artLists = response.getBeans(tClass);
		Page page = new Page(start.longValue(),pagesize.longValue(),response.getResults().getNumFound());
		common.setDataList(artLists);
		common.setPage(page);
		common.setHighlightings(response.getHighlighting());
		return common;
		
	}
	
	//根据id删除
	public void deldata(Integer id,String ids) throws SolrServerException, IOException{
		HttpSolrClient client = new  HttpSolrClient(URL);
		if(id != null){
			//1.删除一个
			client.deleteById(String.valueOf(id));
		}
		//2.删除多个
		if(ids != null){
			List<String> strids = new ArrayList<String>();
			String[] Ids = ids.split(","); 
			for (String Id : Ids) {
				strids.add(Id);
			} 
			client.deleteById(strids);
		}
       /* //3.根据查询条件删除数据,这里的条件只能有一个，不能以逗号相隔
        client.deleteByQuery("id:1");
        
        //4.删除全部，删除不可恢复
        client.deleteByQuery("*:*");*/
        
        //一定要记得提交，否则不起作用
        client.commit();
        client.close();
	}

}












