package com.person.integration.handler;

import com.person.integration.entity.Person;
import com.person.integration.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormulaMessageHandler implements MessageHandler {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        personRepo.saveAll((List<Person>) message.getPayload());
    }
}
