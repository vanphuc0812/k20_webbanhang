package com.vanphuc.webbanhang.user.repository;

import com.vanphuc.webbanhang.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository  // = @Component + special fucntion: repositoty
public interface UserRepository extends JpaRepository<User, UUID> {
    User save(User user);

    List<User> findAll();

    User findByUsername(String pUsername); // "Select * from user where username = pUsername
}
//Hibernate implements JPA
// ->