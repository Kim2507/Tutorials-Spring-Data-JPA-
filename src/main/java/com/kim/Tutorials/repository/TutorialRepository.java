package com.kim.Tutorials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.kim.Tutorials.model.Tutorial;


@RepositoryRestResource
public interface TutorialRepository extends JpaRepository<Tutorial, Long>{
//// Tutorial → Entity type and Long --> primary key
//{
////   findByPublished is a custom method()
//List<Tutorial> findByPublished(boolean published);
}

