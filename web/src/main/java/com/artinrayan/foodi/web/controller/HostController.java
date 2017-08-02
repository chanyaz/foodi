package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.HostFileService;
import com.artinrayan.foodi.model.HostFile;
import com.artinrayan.foodi.web.util.UserUtil;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.HostAccess;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.core.HostAccessService;
import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.web.util.ViewUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.servlet.http.HttpServletRequest;
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
	HostFileService hostFileService;



	@PostConstruct
	public void postConstruct() {
		System.out.println();
	}


	/**
	 *
	 * @param model
	 * @return
     */
	@RequestMapping(value = { "/newHost" }, method = RequestMethod.GET)
	public String newHost(ModelMap model) {
		Host host = new Host();
		model.addAttribute("host", host);
		model.addAttribute("edit", false);
		return ViewUtil.Views.HOSTREGISTRATION.getViewName();
	}

	/**
	 *
	 * @param host
	 * @param result
	 * @param model
	 * @return
	 * @throws BusinessException
     */
	@RequestMapping(value = { "/newHost" }, method = RequestMethod.POST)
	public String saveHost(@Valid Host host, BindingResult result,
						   ModelMap model) throws BusinessException {

		if (result.hasErrors()) {
			return ViewUtil.Views.HOSTREGISTRATION.getViewName();
		}

		try {
			User user = userService.findByUserId(UserUtil.getCurrentUser().getUserId());
			host.setUser(user);
			host.setCreationDate(new Date());
			host.setEnabled(true);
			hostService.saveHost(host);
		}
		catch (BusinessException e)
		{
			throw new BusinessException(e.getMessage(), e.getCause());
		}

		model.addAttribute("success", "Host " + host.getHostName() + " registered successfully");
		return ViewUtil.Views.HOSTSUCCESS.getViewName();
	}

	/**
	 *
	 * @param hostId
	 * @param model
	 * @return
	 * @throws BusinessException
     */
	@RequestMapping(value = { "/edit-host-{hostId}" }, method = RequestMethod.GET)
	public String editHost(@PathVariable int hostId, ModelMap model) throws BusinessException {
		Host host = null;
		try {
			User user = userService.findUserByUsername(UserUtil.getPrincipal());
			host = hostService.findHostByHostIdAndUserId(hostId, user);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage(), e.getCause());
		}
		model.addAttribute("host", host);
		model.addAttribute("edit", true);
		return ViewUtil.Views.HOSTREGISTRATION.getViewName();
	}

	/**
	 *
	 * @param host
	 * @param result
	 * @param model
	 * @return
	 * @throws BusinessException
     */
	@PostMapping("/edit-host-{hostId}")
	public String updateHost(@Valid Host host, BindingResult result,
							 ModelMap model) throws BusinessException {

		if (result.hasErrors()) {
			return ViewUtil.Views.USERREGISTRATION.getViewName();
		}

		if (host.getCreationDate() == null)
			host.setCreationDate(new Date());
		hostService.updateHost(host);

		model.addAttribute("success", "Host " + host.getHostName() + " updated successfully");
		return ViewUtil.Views.HOSTSUCCESS.getViewName();
	}

	/**
	 *
	 * @param hostId
	 * @param model
     * @return
     */
	@RequestMapping(value = { "/add-host-access-{hostId}" }, method = RequestMethod.GET)
	public String addHostAccess(@PathVariable int hostId, ModelMap model) {
		HostAccess hostAccess = new HostAccess();
		model.addAttribute("hostAccess", hostAccess);
		model.addAttribute("edit", false);
		return "hostAccessRegistration";
	}

	/**
	 *
	 * @param modelMap
	 * @return
	 * @throws BusinessException
     */
	@RequestMapping(value = { "/**", "/hostList" }, method = RequestMethod.GET)
	public String listHosts(ModelMap modelMap) throws BusinessException {
		List<Host> hosts = hostService.findHostByUserId(UserUtil.getUserId());
		modelMap.addAttribute("hosts", hosts);
		return ViewUtil.Views.HOSTLIST.getViewName();
	}

	/**
	 *
	 * @param hostId
	 * @param model
     * @return
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

	/**
	 *
	 * @param hostId
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@GetMapping("/manage-host-file-{hostId}")
	public String loadHostAccessImage(@PathVariable int hostId, ModelMap model) throws BusinessException {
		Host host = hostService.findHostByHostId(hostId);
		HostFile hostFile = new HostFile();
		hostFile.setHost(host);
		model.addAttribute("hostFile", hostFile);

		model.addAttribute("hostFiles", host.getHostFiles());
		return ViewUtil.Views.MANAGEHOSTFILE.getViewName();
	}

	/**
	 *
	 * @param hostFile
	 * @param result
	 * @param hostId
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@PostMapping("/manage-host-file-{hostId}")
	public String saveImage(@Valid HostFile hostFile, BindingResult result,
							@RequestParam("fileContent") MultipartFile fileContent,
							@PathVariable int hostId, ModelMap model) throws BusinessException {

//		if (result.hasErrors()) {
//			return ViewUtil.Views.HOSTLIST.getViewName();
//		}

		try {
			Host host = hostService.findHostByHostId(hostId);
			hostFile.setHost(host);
			hostFile.setFileContent(fileContent.getBytes());
			hostFile.setCreationDate(new Date());
			hostFile.setFileType(
					fileContent.getOriginalFilename().substring(fileContent.getOriginalFilename().lastIndexOf(".") + 1));

			hostFileService.saveHostFile(hostFile);

		}
		catch (BusinessException e)
		{
			throw new BusinessException(e.getMessage(), e.getCause());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		model.addAttribute("success", "HostFile registered successfully");
		return ViewUtil.Views.HOSTSUCCESS.getViewName();
	}

	/**
	 *
	 * @param hostId
	 * @param model
	 * @return
	 * @throws BusinessException
	 * @throws JsonProcessingException
     */
	@RequestMapping(value = { "/hostDetail-{hostId}" }, method = RequestMethod.GET)
	public String hostDetail(@PathVariable int hostId, ModelMap model) throws BusinessException, JsonProcessingException {
		User user = userService.findByUserId(UserUtil.getCurrentUser().getUserId());
		Host host = hostService.findHostByHostIdAndUserId(hostId, user);
		model.addAttribute("host", host);
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("hostStr", mapper.writeValueAsString(host));
		model.addAttribute("edit", false);
		return ViewUtil.Views.HOST.getViewName();
	}

	/**
	 *
	 * @param hostFileId
	 * @param model
	 * @return
	 * @throws BusinessException
     */
	@RequestMapping(value = { "/delete-host-file-{hostFileId}" }, method = RequestMethod.GET)
	public String deleteHostFile(@PathVariable int hostFileId, Model model) throws BusinessException {
		hostFileService.deleteHostFileById(hostFileId);
		return "redirect:/host/" + ViewUtil.Views.HOSTLIST.getViewName();
	}

	@ExceptionHandler(BusinessException.class)
	public ModelAndView handleCustomException(BusinessException ex) {

		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", ex.getCause());
		model.addObject("errMsg", ex.getMessage());

		return model;

	}

}