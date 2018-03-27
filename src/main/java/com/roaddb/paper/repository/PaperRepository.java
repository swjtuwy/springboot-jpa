package com.roaddb.paper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.roaddb.paper.model.Paper;
import org.springframework.data.jpa.repository.Query;

public interface PaperRepository extends JpaRepository<Paper, Long>, JpaSpecificationExecutor<Paper> {

    @Query("SELECT * FROM paper p WHERE p.filename = ?2 AND p.id != ?1")
    List<Paper> findPaperExcpetId(Long id, String filename);

    @Query("SELECT COUNT(1) FROM paper p WHERE p.filename = ?2 AND p.id != ?1")
    int findPaperCountExcpetId(Long id, String filename);


}

