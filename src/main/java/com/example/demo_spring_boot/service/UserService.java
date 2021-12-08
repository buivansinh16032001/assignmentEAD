package com.example.demo_spring_boot.service;

import com.example.demo_spring_boot.model.Product;
import com.example.demo_spring_boot.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User saveUser(User user);

    void deleteUser(int id);

    User getUserById(int id);

    List<User> findByNameLike(String name);

    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    boolean checkLogin(String name, String password);
}
