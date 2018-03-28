package com.roaddb.paper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roaddb.paper.model.User;
import com.roaddb.paper.repository.UserRepository;

@Component
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public long addUser(User user) {
        User r = userRepository.save(user);
        return r.getId();
    }

    public Page<User> getUser() {
        int pageNum = 0;
        int pageSize = 10;
        // Order order = new Order(Sort.Direction.DESC, "id"); //
        //   PageRequest pageRequest = new PageRequest(pageNum, pageSize, new Sort(order));
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<User> u = userRepository.findAll(pageRequest);
        return u;
    }


}
