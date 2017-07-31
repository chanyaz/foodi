package com.artinrayan.foodi.core.cache;

import com.artinrayan.foodi.core.UserService;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by asus on 6/11/2017.
 */
@Component("systemCache")
public class SystemCache {

    @Autowired
    UserService userService;

    private SystemCache() {
    }

    private static SystemCache systemCache = new SystemCache();


    public static SystemCache getInstance()
    {
        systemCache.initializeCache();
        return systemCache;
    }

    private synchronized void initializeCache()
    {
        try {
            userService.findAllUsers();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
