package com.userreg.userreg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long>{
    @Query("SELECT u from User u where u.email = ?1")
    User findbyEmail(String email);
}
