package com.roaddb.paper.controller.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.roaddb.paper.controller.PaperController;
import com.roaddb.paper.controller.UserController;
import com.roaddb.paper.model.User;
import com.roaddb.paper.repository.PaperRepository;
import com.roaddb.paper.repository.UserRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PaperTest {

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    PaperController paperController;

    @Autowired
    UserController userController;
    @Autowired
    UserRepository userRepository;

    @Test
    public void test1() {
        for (int i = 0; i < 3; i++) {
            User u = new User("baaab" + i, "baaab" + i + "@ygomi.com");
            userController.addUser(u);
        }
    }

    @Test
    public void test2() {

        List<User> u = userRepository.findAll();
        System.out.println(u.size());
    }
}
