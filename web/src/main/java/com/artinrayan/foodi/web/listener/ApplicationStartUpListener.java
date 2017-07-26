package com.artinrayan.foodi.web.listener;

import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.core.cache.SystemCache;
import com.artinrayan.foodi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by asus on 6/8/2017.
 */
@WebListener
public class ApplicationStartUpListener implements ServletContextListener {



//    @Resource
//    private CacheManager cacheManager;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("---- initialize servlet context -----");
//        SystemCache.getInstance();
        // add initialization code here
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("---- destroying servlet context -----");
        // clean up resources
    }


}
