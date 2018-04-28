import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lzt.solrClient.CommontData;
import com.lzt.solrEntity.ArticleResult;
import com.lzt.util.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;

import com.lzt.solrEntity.ArticleField;

public class solrTest {
	
	private String URL;
	
	@Before
	public void init(){
		URL =  "http://172.31.61.25:8081/solr/lztWeb";
	}

	@Test
	public void test01() throws IOException, SolrServerException{
		HttpSolrClient server = new HttpSolrClient(URL);
		ArticleField art = new ArticleField();
		art.setId(16);
		art.setContent("* 该对象有两个可以使用，都是线程安全的  " +
				"* 1、CommonsHttpSolrServer：启动web服务器使用的，通过http请求的 * 2、" +
				" EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了  * 3、solr 4.0之后好像添加了不少东西" +
				"，其中CommonsHttpSolrServer这个类改名为HttpSolrClient");
		art.setTitle("solr");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		art.setCreateTime(date);
		UpdateResponse res = server.addBean(art);
		server.commit();
	}
	
	@Test
	public void test02() throws SolrServerException, IOException{
		HttpSolrClient client = new  HttpSolrClient(URL);
        //1.删除一个
        client.deleteById("123");
       /* //2.删除多个
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        client.deleteById(ids);
        
        //3.根据查询条件删除数据,这里的条件只能有一个，不能以逗号相隔
        client.deleteByQuery("id:1");
        
        //4.删除全部，删除不可恢复
        client.deleteByQuery("*:*");*/
        
        //一定要记得提交，否则不起作用
        client.commit();
        client.close();
	}
	
	//查询
	@Test
	public void test03() throws SolrServerException, IOException{
		HttpSolrClient server = new HttpSolrClient(URL);
		SolrQuery query = new SolrQuery();
		String keyword = "php";
		query.setQuery("my_content:"+keyword+"\nmy_title:"+keyword+"\nsign:"+keyword);
		//query.setQuery("*:*");
		//分页
		query.setSort("update_time",SolrQuery.ORDER.desc);//定义分页依据
		query.setStart(0);
		query.setRows(10);
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("my_content");
		query.addHighlightField("my_title");
		query.addHighlightField("sign");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>");
		//query.addFilterQuery("id:[1 TO 9]");
		
		QueryResponse response = server.query(query);
		server.commit();
		
		 //查询得到文档的集合
		List<ArticleResult> artLists = response.getBeans(ArticleResult.class);
        SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("通过文档集合获取查询的结果");
        System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());  
        //遍历列表  
        for (SolrDocument doc : solrDocumentList) {
            System.out.println("id:"+doc.get("id")+"content:"+doc.get("my_content")+"title:"+doc.get("my_title")+"sign:"+doc.get("sign"));
        } 

        //
        Map<String, Map<String, List<String>>> highlightings = response.getHighlighting();
        System.out.println(response.getHighlighting());
       /* //得到实体对象
        List<ArticleField> artLists = response.getBeans(ArticleField.class);
        if(artLists!=null && artLists.size()>0){
            System.out.println("通过文档集合获取查询的结果"); 
            for(ArticleField art:artLists){
            	  System.out.println("id:"+art.getId()+"content:"+art.getContent()+"title:"+art.getTitle()+"createTime:"+art.getCreateTime());
            }
        }*/
		
	}

	public static <T> CommontData<T> serach(String keyword, Integer start, Integer pagesize, Class<T> tClass) throws SolrServerException, IOException{
		CommontData<T> common = new CommontData<T>();
		HttpSolrClient server = new HttpSolrClient("http://127.0.0.1:8080/solr/my");
		SolrQuery query = new SolrQuery();
		query.setQuery("my_content:"+keyword+"\nmy_title:"+keyword+"\nsign:"+keyword);
		//query.setQuery("*:*");
		//分页
		query.setSort("update_time",SolrQuery.ORDER.desc);//定义分页依据
		query.setStart(start);
		query.setRows(pagesize);
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("my_content");
		query.addHighlightField("my_title");
		query.addHighlightField("sign");
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

	public static void main(String[] args) throws IOException, SolrServerException {
		CommontData<ArticleResult> common = solrTest.serach("php", 0, 10,ArticleResult.class);
		System.out.println(common.getDataList());
	}
	
}









