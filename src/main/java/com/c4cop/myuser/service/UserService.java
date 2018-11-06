package com.c4cop.myuser.service;

import com.c4cop.myuser.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    boolean deleteUser(Long id);
    User saveUser(User user);
    Optional<User> findUserById(Long id);
    List<User> listAll();
    boolean isUserExist(User user);

}
