package com.tvn.api_for_1c_v2.persistence.dao.services.interfaces;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User findByUsername(String username) throws NotFoundException;

}
