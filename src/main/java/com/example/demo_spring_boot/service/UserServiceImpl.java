package com.example.demo_spring_boot.service;

import com.example.demo_spring_boot.model.User;
import com.example.demo_spring_boot.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getUserById(int id) {

        return userRepo.getById(id);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepo.findByNameLike("%"+name+"%");
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepo.findAll(pageable);
    }

    @Override
    public boolean checkLogin(String name, String password) {
        Optional<User> optionalUser = userRepo.findByName(name);
        if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
