package com.test.ridesharingsvc.repository;


import com.test.ridesharingsvc.model.Rider;
import com.test.ridesharingsvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RidersRepo extends JpaRepository<Rider, Long> {
    Boolean existsByRiderId(String riderId);
    User findByRiderId(String riderId);
    User findByRiderIdAndPassword(String riderId, String password);
}
