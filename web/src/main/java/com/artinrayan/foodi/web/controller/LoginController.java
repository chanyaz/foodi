package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.web.util.UserUtil;
import com.artinrayan.foodi.web.util.ViewUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by asus on 6/6/2017.
 */
@Controller
@RequestMapping("/")
public class LoginController {



    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    HostService hostService;

    @PostConstruct
    public void postConstruct()
    {
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String userManagementPage() {
        return "userManagement";
    }

    @RequestMapping(value = "/ang", method = RequestMethod.GET)
    public String ang() {
        return "angularMap";
    }

    @RequestMapping(value = "/testHost", method = RequestMethod.GET)
    public String hostManagementPage() {
        return "hostManagement";
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        return ViewUtil.Views.ACCESSDENIED.getViewName();
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return ViewUtil.Views.LOGIN.getViewName();
        } else {
            return ViewUtil.Views.HOSTMAP.getViewName();
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = { "/", "/hostMap" }, method = RequestMethod.GET)
    public String listHosts(ModelMap modelMap){
        List<Host> hosts = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            hosts = hostService.findAllHosts();
            modelMap.addAttribute("hostsStr", mapper.writeValueAsString(hosts));
            modelMap.addAttribute("hosts", hosts);

        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ViewUtil.Views.HOSTMAP.getViewName();
    }

    @GetMapping("/error")
    public String directToErrorPage(ModelMap model) {
        return ViewUtil.Views.ERRORPAGE.getViewName();
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
