package com.RyanSBA.mvc.service;

import com.RyanSBA.mvc.DTO.UserDto;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.repository.UserRepository;
import com.RyanSBA.mvc.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository repo;

    PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository repo, @Lazy PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        if(username == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new UserPrinciple(user);
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public void createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        repo.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<User> findById(int id) { return repo.findById(id); }

    public void updateUser(User user) { repo.save(user); }
    @Override
    public void deleteUser(User user) {
        repo.delete(user);
    }
}
