package com.lzt.dao;


import java.util.List;
import java.util.Map;

import com.lzt.Bo.PingCountBo;
import com.lzt.Bo.ReadCountBo;
import com.lzt.Bo.TimeCountBo;
import com.lzt.Bo.typeCountBo;
import com.lzt.entity.Article;
import com.lzt.vo.ArtVo;

public interface ArticleDao {
	
	public Article saveArt(Article art);
	
	public Map<String,Object> getPageArticle(Integer start,Integer pagesize);

	public Map<String,Object> getPageArticleList(Integer start,Integer pagesize,Integer type,String time);

	public Article getdetail(Integer id);

	public Integer deleteById(List<Integer> idList);

	public Integer getCountByTypeId(Integer typeId);

	public Article getLastdetail(Integer id);

	public Article getNextdetail(Integer id);

	public List<typeCountBo> getTypeCount();

	public List<TimeCountBo> getTimeCount();

	public List<ReadCountBo> getReadCount();

	public List<PingCountBo> getpingCount();

	public List<ArtVo> getArtVoList(List<Integer> ids);



}
