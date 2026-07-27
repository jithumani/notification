package org.example.ratelimit.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.example.ratelimit.config.RateLimitProperties;
import org.example.ratelimit.config.RateLimitProperties.EndpointLimit;
import org.example.ratelimit.exception.RateLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bucket4jRateLimiter implements RateLimiter {

    public Bucket4jRateLimiter(RateLimitProperties properties) {
        this.properties = properties;
    }

    private final RateLimitProperties properties;

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();


    @Override
    public void acquire(String endpointKey) {

        Bucket bucket = buckets.computeIfAbsent(
                endpointKey,
                this::createBucket
        );

        if (!bucket.tryConsume(1)) {
            throw new RateLimitExceededException(endpointKey);
        }

    }

    private Bucket createBucket(String endpointKey) {

        EndpointLimit config =
                properties.getEndpoints().get(endpointKey);

        if (config == null) {
            throw new IllegalArgumentException(
                    "No rate limit configuration found for: " + endpointKey
            );
        }

        Bandwidth bandwidth = Bandwidth.builder()
                .capacity(config.getCapacity())
                .refillGreedy(
                        config.getRefillTokens(),
                        config.getRefillDuration())
                .build();

        return Bucket.builder()
                .addLimit(bandwidth)
                .build();
    }
}
