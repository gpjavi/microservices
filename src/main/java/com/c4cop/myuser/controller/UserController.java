package com.c4cop.myuser.controller;

import com.c4cop.myuser.domain.User;
import com.c4cop.myuser.service.UserService;
import com.c4cop.myuser.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    //@RequestMapping(value = "/new", method = RequestMethod.GET)
    @GetMapping("/list")
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = userService.listAll();
        if (users.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);

    }


    @PostMapping("/new")
    public ResponseEntity<?> newUser(@RequestBody User user, UriComponentsBuilder ucBuilder){

        if (userService.isUserExist(user)){
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
                    user.getUsername() + " already exist."),HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        Optional<User> optUser = userService.findUserById(id);
        if (!optUser.isPresent()) {
            return new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(optUser.get(), HttpStatus.OK);
    }


}
