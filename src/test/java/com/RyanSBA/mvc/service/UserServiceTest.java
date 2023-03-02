package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    static private User testUser;

    @Autowired
    UserServiceImpl userService;

    @BeforeAll
    static public void setUp() {
        testUser = new User();
        testUser.setEmail("testUser@test.com");
        testUser.setPassword("password123$");
    }

    @Test
    @Order(1)
    public void createUserTest() {
        userService.createUser(testUser);
        int start = userService.findAllUsers().size();
        User newUser = new User();
        newUser.setEmail("newUser@test.com");
        newUser.setPassword("password123$");
        userService.createUser(newUser);
        int ending = (userService.findAllUsers().size());
        Assertions.assertEquals(start+1, ending);
    }

    @ParameterizedTest
    @ValueSource(strings = {"testUser@test.com", "newUser@test.com"})
    @Order(2)
    public void duplicateEmailShouldFail (String email) {
        User dup = new User();
        dup.setEmail(email);
        dup.setPassword("pass");
        Assertions.assertThrows(DataIntegrityViolationException.class, ()-> {
            userService.createUser(dup);
        });
    }
    @Test
    @Order(3)
    public void findByEmailTest() {
        User actual = userService.findByEmail(testUser.getEmail());
        User expected = new User();
        expected.setEmail("testUser@test.com");
        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
    }
    @Test
    @Order(4)
    public void updateUserTest() {
        testUser.setEmail("otherEmail@test.com");
        userService.updateUser(testUser);
        User actual = userService.findByEmail(testUser.getEmail());
        Assertions.assertEquals("otherEmail@test.com", actual.getEmail());
    }
    @Test
    @Order(5)
    public void findByIdTest() {
        User actual = userService.findById(testUser.getId()).get();
        Assertions.assertEquals(actual.getEmail(), testUser.getEmail());
    }
    @Test
    @Order(6)
    public void deleteUserTest() {
        User user = userService.findByEmail(testUser.getEmail());
        userService.deleteUser(user);

        User other = userService.findByEmail("newUser@test.com");
        userService.deleteUser(other);

    }

}
