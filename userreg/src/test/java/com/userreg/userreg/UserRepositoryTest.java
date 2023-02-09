package com.userreg.userreg;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import org.springframework.test.annotation.*;;
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entitymanager;

    @Test
    public void testCreateUser()
    {
        User user = new User();
        user.setEmail("glek1@gmail.com");
        user.setPassword("tester111");
        user.setFirstName("Denis");
        user.setLastName("Petrov");

        User saveduser= repo.save(user);

        User existuser = entitymanager.find(User.class, saveduser.getId());
        assertThat(existuser.getEmail()).isEqualTo(user.getEmail());
    }


    @Test
    public void testFindUserbyEmail()
    {
        String email = "glek1@gmail.com";

       User user = repo.findbyEmail(email);

       assertThat(user).isNotNull();
    }
}
