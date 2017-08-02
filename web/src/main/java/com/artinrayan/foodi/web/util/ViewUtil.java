package com.artinrayan.foodi.web.util;

/**
 * Created by asus on 6/16/2017.
 */
public class ViewUtil {

    public enum Views
    {
        HOSTSUCCESS("hostRegistrationSuccess"),
        HOSTLIST("hostList"),
        HOSTACCESSLIST("hostAccessList"),
        MANAGEHOSTFILE("manageHostFile"),
        HOSTREGISTRATION("hostRegistration"),
        ACCESSDENIED("accessDenied"),
        HOME("home"),
        LOGIN("login"),
        LOGOUT("logout"),
        USERLIST("userlist"),
        USEREGISTRATIONSUCCESS("userRegistrationSuccess"),
        USERREGISTRATION("userRegistration"),
        HOST("host"),
        ERRORPAGE("error");



        private String viewName;

        Views(String viewName) {
            this.viewName = viewName;
        }

        public String getViewName() {
            return viewName;
        }
    }
}
