package com.roaddb.paper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.roaddb.paper.model.Code;

public interface CodeRepository extends JpaRepository<Code, Long>, JpaSpecificationExecutor<Code> {
}
