package com.test.ridesharingsvc.repository;


import com.test.ridesharingsvc.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RidersRepo extends JpaRepository<Rider, Long> {
    //Boolean existsByRiderId(Long riderId);
    //User findByRiderId(Long riderId);
    //User findByRiderIdAndPassword(String riderId, String password);
}
