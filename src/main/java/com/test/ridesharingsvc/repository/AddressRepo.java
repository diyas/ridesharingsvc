package com.test.ridesharingsvc.repository;

import com.test.ridesharingsvc.model.Address;
import com.test.ridesharingsvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

//     @Query(value = "SELECT * FROM users_address WHERE user_id = ?1", nativeQuery = true)
//     Page<Address> findByUserId(@Param("userId") Long userId, Pageable page);

     Page<Address> findByUsers(User users, Pageable page);





}
