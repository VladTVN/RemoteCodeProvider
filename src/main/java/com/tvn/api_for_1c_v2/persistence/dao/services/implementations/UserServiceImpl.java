package com.tvn.api_for_1c_v2.persistence.dao.services.implementations;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.dao.repositories.UserRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.UserService;
import com.tvn.api_for_1c_v2.persistence.model.Role;
import com.tvn.api_for_1c_v2.persistence.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean addUser(User user) {
        try{
            userRepository.findByUsername(user.getUsername()).orElseThrow(NotFoundException::new);
            return false;
        }catch (NotFoundException ex){
            user.setRoles(Collections.singleton(Role.ADMIN));
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(NotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new  UsernameNotFoundException("User not found"));
    }
}
