package com.speakout.integration.channel;

import com.speakout.integration.entity.Formula;
import com.speakout.integration.repo.SpeakOutRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.dsl.JpaInboundChannelAdapterSpec;
import org.springframework.stereotype.Component;

@Component
public class ChannelAdapters {
    @PersistenceContext
    private EntityManager entityManagerFactory;

    @Autowired
    private SpeakOutRepo speakOutRepo;

    public JpaInboundChannelAdapterSpec getFormulaInboundChannelAdapter() {
        Integer maxFormulaId = speakOutRepo.findMaxFormulaId().orElse(0);
        return Jpa.inboundAdapter(entityManagerFactory)
                .entityClass(Formula.class)
                .namedQuery("selectMaxFormulaId")
//                .jpaQuery("""
//                        select f
//                                from Formula f
//                                where f.id > (select max(p.formulaId)
//                                                    from Person p)
//                        """)
//                .jpaQuery("from Formula where/**/ id > " + maxFormulaId)
                .maxResults(100)
                .expectSingleResult(false);
    }
}
