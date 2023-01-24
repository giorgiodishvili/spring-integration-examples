package com.person.integration.transformer;

import com.person.integration.entity.Formula;
import com.person.integration.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FormulaToSpeakOutTransformer implements GenericTransformer<List<Formula>, List<Person>> {
    @Override
    public List<Person> transform(List<Formula> source) {
        return source.stream().map((it) -> {
            Person person = new Person();
            person.setFormulaId(it.getId());
            person.setUsername(it.getUsername());
            log.info("speakout logging: {}", person);
            return person;
        }).toList();
    }
}
