package com.roaddb.paper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.roaddb.paper.model.Paper;
import com.roaddb.paper.model.User;


public interface UserRepository  extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
