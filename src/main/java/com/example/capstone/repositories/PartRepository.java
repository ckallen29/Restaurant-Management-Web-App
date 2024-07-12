package com.example.capstone.repositories;

import com.example.capstone.domain.Part;
import com.example.capstone.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface PartRepository extends CrudRepository <Part,Long> {
    @Query("SELECT p FROM Part p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    public List<Part> findByNameContainingIgnoreCase(@Param("keyword") String keyword);
}
