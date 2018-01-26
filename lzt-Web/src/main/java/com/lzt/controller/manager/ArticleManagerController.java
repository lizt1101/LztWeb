package com.lzt.controller.manager;

import com.alibaba.fastjson.JSON;
import com.lzt.controller.ArticleController;
import com.lzt.entity.Article;
import com.lzt.exception.LztException;
import com.lzt.service.ArticleService;
import com.lzt.solrEntity.ArticleResult;
import com.lzt.system.RestServer;
import com.lzt.util.JsonUtil;
import com.lzt.util.Page;
import com.lzt.vo.ArtVo;
import com.lzt.vo.MessageVo;
import jdk.nashorn.internal.ir.IdentNode;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/5
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/manager/article")
public class ArticleManagerController {

    private final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 获取后台文章列表
     * @param page
     * @param rows
     * @param
     * @param res
     * @param req
     * @return
     */
    @RequestMapping("/getList.do")
    public String getList(@RequestParam(value="page", required=false)String page,
                          @RequestParam(value="rows",required=false)String rows,
                          @RequestParam(value="key",required=false)String key, HttpServletRequest res, HttpServletResponse req){
        RestServer restServer = new RestServer(res,req);
        log.info("获取文章列表,入参page:"+page+",rows:"+rows);
        Map<String,Object> artMap = null;
        try {
            artMap = articleService.getPageArticleList1(Integer.parseInt(page),Integer.parseInt(rows),key);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",artMap.get("artList"));
        Page page1 = (Page)artMap.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    /**
     * 根据id获取详情
     * @param id
     * @param res
     * @param req
     * @return
     */
    @RequestMapping("/getDetail.do")
    public String getArtDetail(String id, HttpServletRequest res, HttpServletResponse req){
        RestServer restServer = new RestServer(res,req);
        log.info("获取文章详情,入参:"+ id);
        Article article = articleService.getDetail(Integer.parseInt(id));
        restServer.send(JsonUtil.jsonToString(article));
        return null;
    }

    /**
     * 批量删除文章
     * @param ids
     * @param res
     * @param req
     * @return
     */
    @RequestMapping("/deleteArt.do")
    public String deleteArt(String ids, HttpServletRequest res, HttpServletResponse req){
        RestServer restServer = new RestServer(res,req);
        MessageVo messageVo = new MessageVo();
        log.info("删除文章,入参:"+ ids);
        try {
            articleService.deleteById(ids);
        } catch (IOException e) {
            messageVo.setCode(MessageVo.IO_ERROR);
            messageVo.setMessage("Io异常");
        } catch (SolrServerException e) {
            messageVo.setCode(MessageVo.SOLR_ERROR);
            messageVo.setMessage("solr未开启");
        }catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }





}
