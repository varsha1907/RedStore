package com.red.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.red.major.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
}
