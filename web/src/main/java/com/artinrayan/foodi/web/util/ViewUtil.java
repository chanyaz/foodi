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
        HOSTACCESSIMAGEMANAGEMENT("manageHostAccessImage"),
        HOSTREGISTRATION("hostRegistration"),
        ACCESSDENIED("accessDenied"),
        HOSTMAP("hostMap"),
        LOGIN("login"),
        LOGOUT("logout"),
        USERLIST("userlist"),
        USERREGISTRATION("userRegistration");



        private String viewName;

        Views(String viewName) {
            this.viewName = viewName;
        }

        public String getViewName() {
            return viewName;
        }
    }
}
