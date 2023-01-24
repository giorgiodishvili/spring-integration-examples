package com.speakout.integration.transformer;

import com.speakout.integration.entity.Formula;
import com.speakout.integration.entity.SpeakOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FormulaToSpeakOutTransformer implements GenericTransformer<List<Formula>, List<SpeakOut>> {
    @Override
    public List<SpeakOut> transform(List<Formula> source) {
        return source.stream().map((it) -> {
            SpeakOut speakOut = new SpeakOut();
            speakOut.setFormulaId(it.getId());
            speakOut.setUsername(it.getUsername());
            log.info("speakout logging: {}", speakOut);
            return speakOut;
        }).toList();
    }
}
