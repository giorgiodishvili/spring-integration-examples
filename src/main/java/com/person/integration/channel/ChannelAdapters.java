package com.person.integration.channel;

import com.person.integration.entity.Formula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.dsl.JpaInboundChannelAdapterSpec;
import org.springframework.stereotype.Component;

@Component
public class ChannelAdapters {
    @PersistenceContext
    private EntityManager entityManagerFactory;

    public JpaInboundChannelAdapterSpec getFormulaInboundChannelAdapter() {
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
