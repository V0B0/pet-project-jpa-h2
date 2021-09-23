package com.v0b0.pstlabstesttask.repository;

import com.v0b0.pstlabstesttask.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
