package com.person.integration.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Person")
@Table(name = "person")
@NamedQueries(
        {
                @NamedQuery(name = "selectMaxFormulaId", query =
                                """
                                    select f
                                    from Formula f
                                    where f.id > (select coalesce(max(p.formulaId),0)
                                                        from Person p)
                                """
                )
        }
)
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private Long formulaId;

    @Basic
    private String username;
}
