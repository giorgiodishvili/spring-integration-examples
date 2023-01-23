package com.speakout.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.integration.jpa.inbound.JpaPollingChannelAdapter;
import org.springframework.messaging.MessageHandler;

@Configuration
@Slf4j
public class ChannelConfig {

    private final JpaExecutor jpaExecutor;

    public ChannelConfig(JpaExecutor jpaExecutor) {
        this.jpaExecutor = jpaExecutor;
    }
    @Bean
    @InboundChannelAdapter(channel = "jpaInputChannel",
            poller = @Poller(fixedDelay = "3000"))
    public MessageSource<?> jpaInbound() {
        jpaExecutor.setJpaQuery("select p from Person p");
        return new JpaPollingChannelAdapter(jpaExecutor);
    }
//    @Bean
//    @InboundChannelAdapter(channel = "jpaInputChannelSecond",
//            poller = @Poller(fixedDelay = "3000"))
//    public MessageSource<?> jpaInboundSecondChannel() {
//        jpaExecutor.setJpaQuery("select p from Person p WHERE p.id = 1");
//        return new JpaPollingChannelAdapter(jpaExecutor);
//    }

    @Bean
    @ServiceActivator(inputChannel = "jpaInputChannel")
    public MessageHandler handler() {
        return message -> log.info(String.valueOf(message.getPayload()));
    }

//    @Bean
//    @ServiceActivator(inputChannel = "jpaInputChannelSecond")
//    public MessageHandler handlerSECONDCHANNEL() {
//        return message -> log.info(String.valueOf(message.getPayload()));
//    }

}
