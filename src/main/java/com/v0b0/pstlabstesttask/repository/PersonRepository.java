package com.v0b0.pstlabstesttask.repository;

import com.v0b0.pstlabstesttask.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
