package com.c4cop.myuser.service.serviceImpl;

import com.c4cop.myuser.domain.User;
import com.c4cop.myuser.repository.UserRepository;
import com.c4cop.myuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(User user) {

        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
