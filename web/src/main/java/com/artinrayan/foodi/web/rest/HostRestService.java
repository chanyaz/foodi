package com.artinrayan.foodi.web.rest;

import com.artinrayan.foodi.core.HostService;
import com.artinrayan.foodi.core.UserService;
import com.artinrayan.foodi.model.Host;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.web.util.UserUtil;
import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by asus on 7/14/2017.
 */
@RestController
public class HostRestService {

    static final Logger logger = LoggerFactory.getLogger(HostRestService.class);

    @Autowired
    HostService hostService;

    @Autowired
    UserService userService;


    //-------------------Retrieve All Hosts--------------------------------------------------------

    @RequestMapping(value = "/hostdetails", method = RequestMethod.GET)
    public ResponseEntity<List<Host>> listAllHosts() {
        List<Host> hosts = null;
        try {
            hosts = hostService.findAllHosts();
            if(hosts.isEmpty()){
                return new ResponseEntity<List<Host>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<Host>>(hosts, HttpStatus.OK);
    }



    //-------------------Retrieve Single Host--------------------------------------------------------

    @RequestMapping(value = "/host/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Host> getHost(@PathVariable("id") int id) {
        System.out.println("Fetching Host with id " + id);
        Host host = null;
        try {
            host = hostService.findHostByHostId(id);
            if (host == null) {
                System.out.println("Host with id " + id + " not found");
                return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Host>(host, HttpStatus.NO_CONTENT);
    }

    //-------------------Create a Host--------------------------------------------------------

    @RequestMapping(value = "/host", method = RequestMethod.POST)
    public ResponseEntity<Void> createHost(@RequestBody Host host, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Host " + host.getHostName());

        try {
            User user = userService.findUserByUsername(UserUtil.getCurrentUser().getUsername());
            host.setUser(user);
            host.setEnabled(true);
            host.setCreationDate(new Date());
            hostService.saveHost(host);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/host/{id}").buildAndExpand(host.getHostId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    //------------------- Update a Host --------------------------------------------------------

    @RequestMapping(value = "/host/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateHost(@PathVariable("id") int id, @RequestBody Host host) {
        System.out.println("Updating Host " + id);

        Host currentHost = null;
        try {
            currentHost = hostService.findHostByHostId(id);
            if (currentHost == null) {
                System.out.println("Host with id " + id + " not found");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }

            currentHost.setHostName(host.getHostName());
            currentHost.setHostDetail(host.getHostDetail());

            hostService.updateHost(currentHost);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }



    //------------------- Delete a Host --------------------------------------------------------

    @RequestMapping(value="/deletehost",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public ResponseEntity DeleteUser(@RequestBody Host host) {

        try {
            Host currentHost = hostService.findHostByHostId(host.getHostId());
            if (currentHost == null) {
                System.out.println("Unable to delete. Host with id " + host.getHostId() + " not found");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            hostService.deleteHost(host.getHostId());

        } catch (BusinessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
