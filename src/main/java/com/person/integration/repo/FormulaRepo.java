package com.person.integration.repo;

import com.person.integration.entity.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepo extends JpaRepository<Formula, Long> {
    // repo for person entity
}
