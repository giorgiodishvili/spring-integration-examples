package com.speakout.integration.repo;

import com.speakout.integration.entity.SpeakOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakOutRepo extends JpaRepository<SpeakOut, Long> {

    @Query(
            """
                    select max(p.formulaId)
                    from Person p
                    """
    )
    Optional<Integer> findMaxFormulaId();
    // repo for person entity
}
