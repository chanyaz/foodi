package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.UserProfileService;
import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.model.UserProfile;
import com.artinrayan.foodi.web.util.ViewUtil;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by asus on 6/6/2017.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("roles")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;


    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = { "/**", "/userList" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = null;
        try {
            users = userService.findAllUsers();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        model.addAttribute("users", users);
        return ViewUtil.Views.USERLIST.getViewName();
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = { "/angularMap" }, method = RequestMethod.GET)
    public String angularMap(ModelMap model) {

        return "angularMap";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return ViewUtil.Views.USERREGISTRATION.getViewName();
    }

    /**
     *
     * @param user
     * @param result
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = { "/newUser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) throws BusinessException {

        if (result.hasErrors()) {
            return "userRegistration";
        }

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
        if(!userService.isUserUnique(user.getId(), user.getUsername())){
            ResourceBundle rb = ResourceBundle.
                    getBundle("message.user.Messages", LocaleContextHolder.getLocale());

            FieldError ssoError =new FieldError("user","username", messageSource.
                    getMessage(rb.getString("non.unique.username"),
                    new String[]{user.getUsername()}, LocaleContextHolder.getLocale()));
            result.addError(ssoError);
            return "userRegistration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        return "userRegistrationSuccess";
    }

    /**
     *
     * @param username
     * @param model
     * @param request
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model, HttpServletRequest request) throws BusinessException {
        User user = userService.findUserAuthenticateInfoByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return ViewUtil.Views.USERREGISTRATION.getViewName();
    }

    /**
     *
     * @param user
     * @param result
     * @param model
     * @param username
     * @param request
     * @param userId
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String username, HttpServletRequest request,
                             @RequestParam(value="id", required=false) String userId) throws BusinessException {

        if (result.hasErrors()) {
            return ViewUtil.Views.USERREGISTRATION.getViewName();
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        return ViewUtil.Views.USEREGISTRATIONSUCCESS.getViewName();
    }


    /**
     *
     * @param username
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) throws BusinessException {
        userService.deleteUserBySSO(username);
        return "redirect:/" + ViewUtil.Views.USERLIST.getViewName();
    }


    /**
     *
     * @return
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

}
