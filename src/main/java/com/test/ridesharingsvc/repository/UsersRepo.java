package com.test.ridesharingsvc.repository;

import com.test.ridesharingsvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<User, Long> {

     Boolean existsByUserId(String userId);
     User findByUserId(String userId);
     User findByUserIdAndPassword(String userId, String password);




}
