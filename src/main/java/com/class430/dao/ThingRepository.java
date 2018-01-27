package com.class430.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.class430.entity.Thing;

public interface ThingRepository extends JpaRepository<Thing,Integer>{

	List<Thing> getByUid(Integer userId);

	
}
