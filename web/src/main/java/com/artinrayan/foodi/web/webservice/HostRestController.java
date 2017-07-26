package com.artinrayan.foodi.web.webservice;

import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.model.Host;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 7/9/2017.
 */
@Controller //In this case the @ResponseBody is activate
// by default you don't need to add it in the above of function signature
@RequestMapping("/foodiWebService/host")
public class HostRestController {

    @Autowired
    HostService hostService;

    //-------------------Retrieve All Hosts--------------------------------------------------------

    @RequestMapping(value = RestURIConstants.GET_ALL_HOST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Host>> getHosts() {
        System.out.println("Fetching Hosts");
        List<Host> hostList = new ArrayList<>();
        try {
            hostList = hostService.findAllHosts();
            if (hostList.size() == 0) {
                System.out.println("Hosts with not found");
                return new ResponseEntity<List<Host>>(HttpStatus.NOT_FOUND);
            }
        } catch (BusinessException e) {
            new ResponseEntity<List<Host>>(hostList, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<List<Host>>(hostList, HttpStatus.OK);
    }

    //-------------------Retrieve Single Host--------------------------------------------------------

    @RequestMapping(value = RestURIConstants.GET_HOST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        try {
            Host host = hostService.findHostByHostId(id);
            if (host != null)
                return new ResponseEntity<Host>(host, HttpStatus.OK);
            else
                return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Host>(HttpStatus.NOT_ACCEPTABLE);
    }

    //-------------------Create a Host--------------------------------------------------------

    @RequestMapping(value = RestURIConstants.CREATE_HOST, method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Host host, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Host " + host.getHostName());

        try {
            hostService.saveHost(host);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(host.getHostId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (BusinessException e) {
            System.out.println("A User with name " + host.getHostName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);        }

    }

    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = RestURIConstants.UPDATE_HOST, method = RequestMethod.PUT)
    public ResponseEntity<Host> updateUser(@PathVariable("id") long id, @RequestBody Host host) {
        System.out.println("Updating User " + id);

        try {
            hostService.saveHost(host);
            return new ResponseEntity<Host>(host, HttpStatus.OK);
        } catch (BusinessException e) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
        }
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = RestURIConstants.DELETE_HOST, method = RequestMethod.DELETE)
    public ResponseEntity<Host> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        Host host = null;
        try {
            host = hostService.findHostByHostId(id);
        if (host != null) {
            hostService.deleteHost(id);
            return new ResponseEntity<Host>(HttpStatus.NO_CONTENT);
        }
        } catch (BusinessException e) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Unable to delete. User with id " + id + " not found");
        return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public @ResponseBody
//    List<Host> getEmployee() {
//
//        List<Host> hostList = new ArrayList<>();
//        try {
//            hostList = hostService.findAllHosts();
//            if (hostList.size() == 0) {
//                System.out.println("Hosts with not found");
//            }
//        } catch (BusinessException e) {
//            e.printStackTrace();
//        }
//
//        return hostList;
//    }
}
