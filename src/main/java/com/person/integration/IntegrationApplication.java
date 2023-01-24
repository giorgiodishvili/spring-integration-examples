package com.person.integration;

import com.person.integration.entity.Formula;
import com.person.integration.repo.FormulaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class IntegrationApplication {

    @Autowired
    private FormulaRepo formulaRepo;

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

    @Bean
    ApplicationRunner populateDatabase() {
        Stream.iterate(0, integer -> integer++ )
                .limit(100)
                .forEach(it -> {
                    Formula person = new Formula();
                    person.setUsername("user " + it);
                    formulaRepo.save(person);
                });
        return args -> {
        };
    }
}
