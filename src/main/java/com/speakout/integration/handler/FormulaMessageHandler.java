package com.speakout.integration.handler;

import com.speakout.integration.entity.SpeakOut;
import com.speakout.integration.repo.SpeakOutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormulaMessageHandler implements MessageHandler {

    @Autowired
    private SpeakOutRepo speakOutRepo;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        speakOutRepo.saveAll((List<SpeakOut>) message.getPayload());
    }
}
