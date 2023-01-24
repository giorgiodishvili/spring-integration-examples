package com.person.integration.config;

import com.person.integration.channel.ChannelAdapters;
import com.person.integration.handler.FormulaMessageHandler;
import com.person.integration.transformer.FormulaToSpeakOutTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.time.Duration;

@Configuration
@EnableIntegration
@Slf4j
public class BasicIntegrationConfig {
    @Bean
    public IntegrationFlow pollingAdapterFlow(FormulaMessageHandler formulaMessageHandler,
                                              FormulaToSpeakOutTransformer formulaToSpeakOutTransformer,
                                              ChannelAdapters channelAdapters) {
        return IntegrationFlow
                .from(
                        channelAdapters.getFormulaInboundChannelAdapter(),
                        e -> e.poller(p -> p.trigger(new PeriodicTrigger(Duration.ofMillis(1000))))
                )
                .transform(formulaToSpeakOutTransformer)
                .handle(formulaMessageHandler)
//                .channel(c -> c.queue("pollingResults"))
                .get();
    }

}
