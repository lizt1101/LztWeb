package com.lzt.listener;

import com.lzt.entity.BjImage;
import com.lzt.entity.LunBo;
import com.lzt.entity.Tu;
import com.lzt.service.BjImageService;
import com.lzt.service.LunBoService;
import com.lzt.service.TuService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @Title 初始化图片
 * @Description
 * @Author:lizitao
 * @Create 2018/1/18
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */

@Component
public class MyListener implements ServletContextListener,ApplicationContextAware {

    private static ApplicationContext context = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("初始化数据成功!");
        ServletContext application = servletContextEvent.getServletContext();
        BjImageService bjImageService = (BjImageService)context.getBean("bjImageService");
        List<BjImage> bjImageList = bjImageService.getQBjList();
        application.setAttribute("bjList",bjImageList);

        LunBoService lunBoService = (LunBoService)context.getBean("lunBoService");
        List<LunBo> lunBoList = lunBoService.getLbList();
        application.setAttribute("LbList",lunBoList);

        TuService tuService =  (TuService)context.getBean("tuService");
        List<Tu> tuList = tuService.getQtList();
        application.setAttribute("tuList",tuList);
       /* if(tuList!=null){
            for (Tu tu:tuList) {
                if(tu.getTuType().equals("1")){
                    application.setAttribute("indexHeadTu",tu);
                }else if(tu.getTuType().equals("2")){
                    application.setAttribute("indexContentTu",tu);
                }else if(tu.getTuType().equals("3")){
                    application.setAttribute("indexCeTu",tu);
                }else if(tu.getTuType().equals("4")){
                    application.setAttribute("indexTimeTu",tu);
                }else if(tu.getTuType().equals("5")){
                    application.setAttribute("detailsContentTu",tu);
                }else{
                    application.setAttribute("qtTu",tu);
                }
            }
        }*/

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
