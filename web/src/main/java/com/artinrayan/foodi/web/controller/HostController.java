package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.HostAccessFileService;
import com.artinrayan.foodi.model.HostAccessFile;
import com.artinrayan.foodi.web.util.UserUtil;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.HostAccess;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.core.HostAccessService;
import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.web.util.ViewUtil;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/host")
public class HostController {

	@Autowired
	UserService userService;

	@Autowired
	HostService hostService;

	@Autowired
	HostAccessService hostAccessService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	HostAccessFileService hostAccessFileService;



	@PostConstruct
	public void postConstruct() {
		System.out.println();
	}

	@RequestMapping(value= "/allHosts", method = RequestMethod.GET)
	public ResponseEntity<List<Host>> getAllPersons() {
		List<Host> list = null;
		try {
			list = hostService.findAllHosts();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Host>>(list, HttpStatus.OK);
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newHost" }, method = RequestMethod.GET)
	public String newHost(ModelMap model) {
		Host host = new Host();
		model.addAttribute("host", host);
		model.addAttribute("edit", false);
		return ViewUtil.Views.HOSTREGISTRATION.getViewName();
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newHost" }, method = RequestMethod.POST)
	public String saveHost(@Valid Host host, BindingResult result,
						   ModelMap model) throws BusinessException {

		if (result.hasErrors()) {
			return ViewUtil.Views.HOSTREGISTRATION.getViewName();
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
//		if(!userService.isUserSSOUnique(host.getId(), host.getUsername())){
//			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
//		    result.addError(ssoError);
//			return "userRegistration";
//		}

		try {
			User user = userService.findUserByUsername(UserUtil.getPrincipal());
			host.setUser(user);
			host.setCreationDate(new Date());
			host.setEnabled(true);
			hostService.saveHost(host);

//			Host host1 = hostService.findHostById(host.getHostId());
//
//			HostAccess hostAccess = new HostAccess();
//			hostAccess.setCreationDate(new Date());
//			hostAccess.setHostAddress("dd");
//			hostAccess.setHostCity("dd");
//			hostAccess.setHostState("dd");
//			hostAccess.setHost(host1);
//			hostAccess.setLatitude("35.7393891");
//			hostAccess.setLongitude("51.33359671");
//			hostAccessService.saveHostAccess(hostAccess);
		}
		catch (BusinessException e)
		{
			throw new BusinessException(e.getMessage(), e.getCause());
		}

		model.addAttribute("success", "Host " + host.getHostName() + " registered successfully");
		//return "success";
		return ViewUtil.Views.HOSTSUCCESS.getViewName();
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/manage-host-accesses-{hostId}" }, method = RequestMethod.POST)
	public String saveHostAccess(@Valid HostAccess hostAccess, BindingResult result,
								 ModelMap model, @PathVariable int hostId) throws BusinessException {

		if (result.hasErrors()) {
			HostAccess newHostAccess = new HostAccess();
			model.addAttribute("newHostAccess", newHostAccess);
			model.addAttribute("hostId", hostId);
			model.addAttribute("edit", false);
			return ViewUtil.Views.HOSTACCESSLIST.getViewName();
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
//		if(!userService.isUserSSOUnique(host.getId(), host.getSsoId())){
//			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
//		    result.addError(ssoError);
//			return "userRegistration";
//		}

		try {
			User user = userService.findUserByUsername(UserUtil.getPrincipal());
			Host host = hostService.findHostByHostIdAndUserId(hostId, user);
			hostAccess.setHost(host);
			hostAccess.setHostCountry(hostAccess.getHostCountry());
			hostAccess.setHostState(hostAccess.getHostState());
			hostAccess.setHostCity(hostAccess.getHostCity());
			hostAccess.setHostAddress(hostAccess.getHostAddress());
			hostAccess.setHostPhoneNumber(hostAccess.getHostPhoneNumber());
			hostAccess.setLatitude(hostAccess.getLatitude());
			hostAccess.setLongitude(hostAccess.getLongitude());
			hostAccess.setCreationDate(new Date());
			hostAccessService.saveHostAccess(hostAccess);

			model.addAttribute("success", "Host access registered successfully");
			HostAccess newHostAccess = new HostAccess();
			model.addAttribute("newHostAccess", newHostAccess);
			model.addAttribute("hostId", hostId);
			model.addAttribute("hostAccessList", host.getHostAccesses());
			model.addAttribute("edit", false);
		}
		catch (BusinessException e)
		{
			throw new BusinessException(e.getMessage(), e.getCause());
		}

		//return "success";
		return ViewUtil.Views.HOSTACCESSLIST.getViewName();
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-host-{hostId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable int hostId, ModelMap model) throws BusinessException {
		Host host = null;
		try {
			User user = userService.findUserByUsername(UserUtil.getPrincipal());
			host = hostService.findHostByHostIdAndUserId(hostId, user);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e.getCause());
		}
		model.addAttribute("host", host);
		model.addAttribute("edit", true);
		return "hostRegistration";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/add-host-access-{hostId}" }, method = RequestMethod.GET)
	public String addHostAccess(@PathVariable int hostId, ModelMap model) {
		HostAccess hostAccess = new HostAccess();
		model.addAttribute("hostAccess", hostAccess);
		model.addAttribute("edit", false);
		return "hostAccessRegistration";
	}

	@RequestMapping(value = { "/**", "/hostList" }, method = RequestMethod.GET)
	public String listHosts(ModelMap modelMap) throws BusinessException {
		List<Host> hosts = hostService.findHostByUserId(UserUtil.getUserId());
		modelMap.addAttribute("hosts", hosts);
		return ViewUtil.Views.HOSTLIST.getViewName();
	}

	@GetMapping("/manage-host-accesses-{hostId}")
//	@RequestMapping(value = { "/manage-host-accesses-{hostId}" }, method = RequestMethod.GET)
	public String loadHostAccesses(@PathVariable int hostId, ModelMap model) throws BusinessException {
		HostAccess newHostAccess = new HostAccess();
		model.addAttribute("newHostAccess", newHostAccess);
		model.addAttribute("hostId", hostId);
		model.addAttribute("edit", false);

		User user = userService.findUserByUsername(UserUtil.getPrincipal());
		Host host = hostService.findHostByHostIdAndUserId(hostId, user);
		model.addAttribute("hostAccessList", host.getHostAccesses());
		return ViewUtil.Views.HOSTACCESSLIST.getViewName();
	}

	/**
	 * This method will delete an user by it's username value.
	 */
	@RequestMapping(value = { "/delete-host-{hostId}" }, method = RequestMethod.GET)
	public String deleteHost(@PathVariable int hostId, Model model) {
		try {
			hostService.deleteHost(hostId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		return "redirect:/host/" + ViewUtil.Views.HOSTLIST.getViewName();
	}

	@RequestMapping(value = { "/delete-host-access-{hostAccessId}-{hostId}" }, method = RequestMethod.GET)
	public String deleteHostAccess(@PathVariable int hostAccessId,
								   @PathVariable int hostId,
								   Model model) {
//		try {
//			hostService.deleteHost(hostId);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}

		return ViewUtil.Views.HOSTACCESSLIST.getViewName();
	}

	/**
	 * This method will delete an user by it's username value.
	 */
//	@RequestMapping(value = { "/delete-host-access-{hostAccessId}" }, method = RequestMethod.GET)
//	public String deleteHostAccess(@RequestParam(value="hostAccessId", required=true) int hostAccessId,
//							 		ModelMap model) throws BusinessException {
////		try {
////			hostAccessService.deleteHostAccess(hostAccessId);
////		} catch (BusinessException e) {
////			throw new BusinessException();
////		}
////		HostAccess newHostAccess = new HostAccess();
////		model.addAttribute("newHostAccess", newHostAccess);
////		model.addAttribute("edit", false);
////		User user = userService.findUserByUsername(UserUtil.getPrincipal());
////		Host host = hostService.findHostByHostIdAndUserId(hostId, user);
////		model.addAttribute("hostAccessList", host.getHostAccesses());
//		return ViewUtil.Views.HOSTACCESSLIST.getViewName();
//	}

	/**
	 *
	 * @param hostAccessId
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = { "/manage-host-access-file-{hostAccessId}" }, method = RequestMethod.GET)
	public String loadHostAccessImage(@PathVariable int hostAccessId, ModelMap model) throws BusinessException {
		HostAccess hostAccess = hostAccessService.findHostAccessById(hostAccessId);
		HostAccessFile hostAccessFile = new HostAccessFile();
		hostAccessFile.setHostAccess(hostAccess);
		model.addAttribute("newHostAccessFile", hostAccessFile);

		model.addAttribute("hostAccessFiles", hostAccess.getHostAccessFiles());
		return ViewUtil.Views.HOSTACCESSIMAGEMANAGEMENT.getViewName();
	}

	/**
	 *
	 * @param hostAccessFile
	 * @param result
	 * @param hostAccessId
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@PostMapping("/manage-host-access-file-{hostAccessId}")
//	@RequestMapping(value = { "/manage-host-access-file-{hostAccessId}" }, method = RequestMethod.POST)
	public String saveImage(@Valid HostAccessFile hostAccessFile, BindingResult result,
							@RequestParam("fileContent") MultipartFile fileContent,
							@PathVariable int hostAccessId, ModelMap model) throws BusinessException {

//		if (result.hasErrors()) {
//			return ViewUtil.Views.HOSTACCESSIMAGEMANAGEMENT.getViewName();
//		}

		//save image into database
//		File file = new File("C:\\mavan-hibernate-image-mysql.gif");
//		byte[] bFile = new byte[(int) file.length()];
//
//		try {
//			FileInputStream fileInputStream = new FileInputStream(file);
//			//convert file into array of bytes
//			fileInputStream.read(bFile);
//			fileInputStream.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		Avatar avatar = new Avatar();
//		avatar.setImage(bFile);

		try {
			HostAccess currentAccess = hostAccessService.findHostAccessById(hostAccessId);
			hostAccessFile.setHostAccess(currentAccess);
			hostAccessFile.setFileContent(fileContent.getBytes());
			hostAccessFile.setCreationDate(new Date());
			hostAccessFile.setFileType(
					fileContent.getOriginalFilename().substring(fileContent.getOriginalFilename().lastIndexOf(".") + 1));

			hostAccessFileService.saveHostAccessImage(hostAccessFile);

			model.addAttribute("hostAccessFiles", currentAccess.getHostAccessFiles());

		}
		catch (BusinessException e)
		{
			throw new BusinessException(e.getMessage(), e.getCause());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		model.addAttribute("success", "HostAccessFile registered successfully");
		return ViewUtil.Views.HOSTSUCCESS.getViewName();
	}

	@ExceptionHandler(BusinessException.class)
	public ModelAndView handleCustomException(BusinessException ex) {

		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", ex.getCause());
		model.addObject("errMsg", ex.getMessage());

		return model;

	}

}