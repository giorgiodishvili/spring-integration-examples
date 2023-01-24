package com.speakout.integration.repo;

import com.speakout.integration.entity.Formula;
import com.speakout.integration.entity.SpeakOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepo extends JpaRepository<Formula, Long> {
    // repo for person entity
}
