package org.example.ratelimit.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rate-limit")
public class RateLimitProperties {

    private Map<String, EndpointLimit> endpoints = new HashMap<>();

    public Map<String, EndpointLimit> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(Map<String, EndpointLimit> endpoints) {
        this.endpoints = endpoints;
    }

    public static class EndpointLimit {

        /**
         * Bucket size
         */
        private long capacity;

        /**
         * Tokens added every refill period
         */
        private long refillTokens;

        /**
         * Example:
         * 60s
         * 5m
         */
        private Duration refillDuration;

        public long getCapacity() {
            return capacity;
        }

        public void setCapacity(long capacity) {
            this.capacity = capacity;
        }

        public long getRefillTokens() {
            return refillTokens;
        }

        public void setRefillTokens(long refillTokens) {
            this.refillTokens = refillTokens;
        }

        public Duration getRefillDuration() {
            return refillDuration;
        }

        public void setRefillDuration(Duration refillDuration) {
            this.refillDuration = refillDuration;
        }
    }
}