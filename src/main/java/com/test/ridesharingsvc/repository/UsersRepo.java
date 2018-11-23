package com.test.ridesharingsvc.repository;

import com.test.ridesharingsvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {

     Boolean existsByUserId(String userId);
     Boolean existsByEmail(String email);
     User findByUserId(String userId);
     User findByUserIdAndPassword(String userId, String password);
     Optional<User> findByUserIdOrEmail(String userId, String email);




}
