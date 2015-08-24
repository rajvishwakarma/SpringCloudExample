package com.sample.recommendation.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SetProcTimeBean {
    
   // @Value("${service.defaultMinMs}")
    private int minMs;

   // @Value("${service.defaultMaxMs}")
    private int maxMs;

    public void setDefaultProcessingTime(int minMs, int maxMs) {

        if (minMs < 0) {
            minMs = 0;
        }
        if (maxMs < minMs) {
            maxMs = minMs;
        }

        this.minMs = minMs;
        this.maxMs = maxMs;
    }

    public int calculateProcessingTime() {
        int processingTimeMs = minMs + (int) (Math.random() * (maxMs - minMs));
        return processingTimeMs;
    }
}