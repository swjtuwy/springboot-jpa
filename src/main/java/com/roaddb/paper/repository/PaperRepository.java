package com.roaddb.paper.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.roaddb.paper.model.Paper;
import org.springframework.data.jpa.repository.Query;

public interface PaperRepository extends JpaRepository<Paper, Long>, JpaSpecificationExecutor<Paper> {

    @Query("SELECT p FROM Paper p WHERE p.filename = ?2 AND p.id != ?1")
    List<Paper> findPaperExcpetId(Long id, String filename);

    @Query("SELECT COUNT(1) FROM Paper p WHERE p.filename = ?2 AND p.id != ?1")
    int findPaperCountExcpetId(Long id, String filename);

    @Override
    Page<Paper> findAll(Pageable pageable);


    @Query(value = "SELECT p from Paper p WHERE p.title like %?1% or p.tag like %?1% or p.summary like %?1%",
            countQuery = " select count(p) from Paper p")
    Page<Paper> paperPageData(String key, Pageable pageable);

}

