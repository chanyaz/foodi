package com.artinrayan.foodi.web.rest;

import com.artinrayan.foodi.core.HostAccessService;
import com.artinrayan.foodi.model.HostAccess;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asus on 7/16/2017.
 */
@RestController
public class HostAccessRestService {


    @Autowired
    HostAccessService hostAccessService;

    //-------------------Retrieve All Hosts--------------------------------------------------------

    @RequestMapping(value = "/hostAccess", method = RequestMethod.GET)
    public ResponseEntity<List<HostAccess>> listAllUsers() {
        List<HostAccess> hostAccesses = null;
        try {
            hostAccesses = hostAccessService.findAllHostsAccesses();
            if(hostAccesses.isEmpty()){
                return new ResponseEntity<List<HostAccess>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<HostAccess>>(hostAccesses, HttpStatus.OK);
    }

    //-------------------Retrieve Single Host--------------------------------------------------------

    @RequestMapping(value = "/hostAccess/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostAccess> getUser(@PathVariable("id") int id, ModelMap model) {
        System.out.println("Fetching User with id " + id);
        HostAccess hostAccess = null;
        try {
            hostAccess = hostAccessService.findHostAccessById(id);
            if (hostAccess == null) {
                System.out.println("User with id " + id + " not found");
                return new ResponseEntity<HostAccess>(HttpStatus.NOT_FOUND);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        model.addAttribute("hostAccess", hostAccess);
        return new ResponseEntity<HostAccess>(hostAccess, HttpStatus.OK);
    }
}
