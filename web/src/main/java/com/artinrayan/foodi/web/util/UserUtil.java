package com.artinrayan.foodi.web.util;

import com.artinrayan.foodi.model.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by asus on 5/26/2017.
 */
public class UserUtil {

    static Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    public static String getPrincipal(){
        String userName = null;

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public static int getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CurrentUser) {
            return ((CurrentUser) principal).getUserId();
        }

        return 0;
    }

    public static CurrentUser getCurrentUser() {
        if (principal instanceof UserDetails) {
            return (CurrentUser) principal;
        }
        return null;
    }
}
