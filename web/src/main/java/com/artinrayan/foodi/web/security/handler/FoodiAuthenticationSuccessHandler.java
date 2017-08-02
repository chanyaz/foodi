package com.artinrayan.foodi.web.security.handler;

import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.model.CurrentUser;
import com.artinrayan.foodi.web.util.ViewUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by asus on 7/22/2017.
 * how to get redirected to a method at login/logout before target-url called in spring-security, spring mvc
 */
@Component
public class FoodiAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // changeLastLoginTime(username)
//        userService.changeLastLoginTime(authentication.getName());

        HttpSession session = request.getSession();
        CurrentUser authUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("fullName", authUser.getFullName());
        session.setAttribute("authorities", authentication.getAuthorities());
        String localeStr = request.getParameter("language");
        if (!StringUtils.isNullOrEmpty(localeStr)) {
            Locale locale = new Locale(localeStr);
            localeResolver.setLocale(request, response, locale);
        }

        //set our response to OK status
//        response.setStatus(HttpServletResponse.SC_OK);

        //since we have created our custom success handler, its up to us to where
        //we will redirect the user after successfully login
        response.sendRedirect(ViewUtil.Views.HOME.getViewName());
    }

}