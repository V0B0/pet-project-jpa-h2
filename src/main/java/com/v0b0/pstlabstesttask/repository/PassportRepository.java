package com.v0b0.pstlabstesttask.repository;

import com.v0b0.pstlabstesttask.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
}
