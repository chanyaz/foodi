package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.UserAngular;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by asus on 7/13/2017.
 */
@Service("userAngularService")
@Transactional
public class UserAngularServiceImpl implements UserAngularService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<UserAngular> users;

    static{
        users= populateDummyUsers();
    }

    public List<UserAngular> findAllUsers() {
        return users;
    }

    public UserAngular findById(long id) {
        for(UserAngular user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public UserAngular findByName(String name) {
        for(UserAngular user : users){
            if(user.getUsername().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void saveUser(UserAngular user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public void updateUser(UserAngular user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deleteUserById(long id) {

        for (Iterator<UserAngular> iterator = users.iterator(); iterator.hasNext(); ) {
            UserAngular user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isUserExist(UserAngular user) {
        return findByName(user.getUsername())!=null;
    }

    public void deleteAllUsers(){
        users.clear();
    }

    private static List<UserAngular> populateDummyUsers(){
        List<UserAngular> users = new ArrayList<UserAngular>();
        users.add(new UserAngular(counter.incrementAndGet(),"Sam", "NY", "sam@abc.com"));
        users.add(new UserAngular(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
        users.add(new UserAngular(counter.incrementAndGet(),"Kelly", "NEBRASKA", "kelly@abc.com"));
        return users;
    }
}
