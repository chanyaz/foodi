package com.artinrayan.foodi.web.controller;

import com.artinrayan.foodi.core.*;
import com.artinrayan.foodi.model.Attachment;
import com.artinrayan.foodi.web.util.UserUtil;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.Category;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.web.util.ViewUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
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
import java.util.*;

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
	AttachmentService attachmentService;

	@Autowired
//	@Qualifier(value = "categoryServiceTransactionalImpl")
	CategoryService categoryService;



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
		Category hostAccess = new Category();
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
	public String removeHost(@PathVariable int hostId, Model model) {
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
	@GetMapping("/manage-attachment-{hostId}")
	public String prepareHostAttachments(@PathVariable int hostId, ModelMap model) throws BusinessException {
		Host host = hostService.findHostByHostId(hostId);
		Attachment attachment = new Attachment();
		attachment.setHost(host);
		model.addAttribute("attachment", attachment);

		model.addAttribute("attachments", host.getAttachments());
		return ViewUtil.Views.MANAGEHOSTATTACHMENT.getViewName();
	}

	/**
	 *
	 * @param attachment
	 * @param result
	 * @param hostId
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@PostMapping("/manage-attachment-{hostId}")
	public String saveAttachment(@Valid Attachment attachment, BindingResult result,
							@RequestParam("fileContent") MultipartFile fileContent,
							@PathVariable int hostId, ModelMap model) throws BusinessException {

//		if (result.hasErrors()) {
//			return ViewUtil.Views.HOSTLIST.getViewName();
//		}

		try {
			Host host = hostService.findHostByHostId(hostId);
			attachment.setHost(host);
			attachment.setFileContent(fileContent.getBytes());
			attachment.setCreationDate(new Date());
			attachment.setFileType(
					fileContent.getOriginalFilename().substring(fileContent.getOriginalFilename().lastIndexOf(".") + 1));

			attachmentService.saveAttachment(attachment);

		}
		catch (BusinessException e)
		{
			throw new BusinessException(e.getMessage(), e.getCause());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		model.addAttribute("success", "Attachment registered successfully");
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
	 * @param attachmentId
	 * @param model
	 * @return
	 * @throws BusinessException
     */
	@RequestMapping(value = { "/delete-host-file-{attachmentId}" }, method = RequestMethod.GET)
	public String deleteAttachment(@PathVariable int attachmentId, Model model) throws BusinessException {
		attachmentService.deleteAttachmentById(attachmentId);
		return "redirect:/host/" + ViewUtil.Views.HOSTLIST.getViewName();
	}

	/**
	 *
	 * @param hostId
	 * @param model
	 * @return
	 * @throws BusinessException
     */
	@GetMapping("/manage-category-{hostId}")
	public String manageHostCategories(@PathVariable int hostId, ModelMap model) throws BusinessException {

		Host host = hostService.findHostByHostId(hostId);
		List<Category> categories = categoryService.findHostCategoriesByHostId(host);
		model.addAttribute("categories", categories);
		return ViewUtil.Views.MANAGEHOSTCATEGORY.getViewName();
	}

	/**
	 *
	 * @param ex
	 * @return
     */
	@ExceptionHandler(BusinessException.class)
	public ModelAndView handleCustomException(BusinessException ex) {

		ModelAndView model = new ModelAndView("error");
		model.addObject("errCode", ex.getCause());
		model.addObject("errMsg", ex.getMessage());

		return model;

	}

	/**
	 *
	 * @return
     */
	@ModelAttribute("activationList")
	public Map<Boolean, String> getCountryList()
	{
		Map<Boolean, String> activationStatus = new LinkedHashMap<Boolean, String>();
		activationStatus.put(true, "Active");
		activationStatus.put(false, "Inactive");

		return activationStatus;
	}


}