package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.UserAngular;

import java.util.List;

/**
 * Created by asus on 7/13/2017.
 */
public interface UserAngularService {
    UserAngular findById(long id);

    UserAngular findByName(String name);

    void saveUser(UserAngular user);

    void updateUser(UserAngular user);

    void deleteUserById(long id);

    List<UserAngular> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(UserAngular user);
}
