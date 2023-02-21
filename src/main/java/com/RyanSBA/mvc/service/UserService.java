package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.DTO.UserDto;
import com.RyanSBA.mvc.model.Recipe;
import com.RyanSBA.mvc.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    List<User> findAllUsers();
    void createUser(UserDto userDto);
    User findByEmail(String email);
    void deleteUser(User user);
}
