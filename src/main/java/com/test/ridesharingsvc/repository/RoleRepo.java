package com.test.ridesharingsvc.repository;


import com.test.ridesharingsvc.model.Role;
import com.test.ridesharingsvc.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName roleName);
}
