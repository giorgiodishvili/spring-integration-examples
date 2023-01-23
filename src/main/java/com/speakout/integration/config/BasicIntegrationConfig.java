package com.speakout.integration.config;

import com.speakout.integration.entity.Person;
import com.speakout.integration.repo.PersonRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.integration.jpa.support.PersistMode;

import java.util.stream.Stream;

@Configuration
@EnableIntegration
@Slf4j
public class BasicIntegrationConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PersonRepo personRepository;


    /**
     * Populate the database with a bunch of persons to feed the poller
     *
     * @return
     */
    @Bean
    ApplicationRunner populateDatabase() {
        Stream.iterate(0, integer -> integer++)
                .limit(100)
                .forEach(integer -> {
                    Person person = new Person();
                    person.setUsername("user " + integer);
                    personRepository.save(person);
                });
        return args -> {
        };
    }

    @Bean
    public JpaExecutor jpaExecutor() {
        JpaExecutor executor = new JpaExecutor(this.entityManager);
        executor.setEntityClass(Person.class);
        executor.setPersistMode(PersistMode.PERSIST);
//        executor.setJpaQuery("select p from Person p");
        return executor;
    }
}
