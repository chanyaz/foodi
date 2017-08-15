package com.artinrayan.foodi.web.rest;

import com.artinrayan.foodi.core.HostAccessService;
import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.Category;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    HostService hostService;

    //-------------------Retrieve All Hosts--------------------------------------------------------

    @RequestMapping(value = "/hostAccess", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> listAllUsers() {
        List<Category> hostAccesses = null;
        try {
            hostAccesses = hostAccessService.findAllHostsAccesses();
            if(hostAccesses.isEmpty()){
                return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<Category>>(hostAccesses, HttpStatus.OK);
    }

    //-------------------Retrieve Single Host--------------------------------------------------------

    @RequestMapping(value = "/restHost/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Host host = null;
        try {
            host = hostService.findHostByHostId(id);
            if (host == null) {
                System.out.println("User with id " + id + " not found");
                return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Host>(host, HttpStatus.OK);
    }
}
