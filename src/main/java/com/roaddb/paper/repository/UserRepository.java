package com.roaddb.paper.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.roaddb.paper.model.Paper;
import com.roaddb.paper.model.User;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository  extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Override
    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT u from User u WHERE u.userName like %?1 ",
            countQuery = " select count(u) from User u")
    Page<User> paperUserData(String key, Pageable pageable);

    @Query("select u from User u where u.email like %?1%")
    List<User> findByEmailLike(String email);

}
