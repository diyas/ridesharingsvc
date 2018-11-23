package com.test.ridesharingsvc.repository;

import com.test.ridesharingsvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {

     Boolean existsByUserId(Long userId);
     Boolean existsByUserName(String userName);
     Boolean existsByEmail(String email);
     User findByUserId(Long userId);
     Optional<User> findByUserName(String userId);
     User findByUserIdAndPassword(String userId, String password);
     Optional<User> findByUserNameOrEmail(String userId, String email);




}
