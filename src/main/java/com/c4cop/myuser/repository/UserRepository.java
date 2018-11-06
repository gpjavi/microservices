package com.c4cop.myuser.repository;

import com.c4cop.myuser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean findByEmail(String email);
}
