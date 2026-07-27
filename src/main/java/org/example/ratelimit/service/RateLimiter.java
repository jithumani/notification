package org.example.ratelimit.service;

public interface RateLimiter {
    /**
     * Attempts to consume one token.
     *
     * Throws RateLimitExceededException
     * when request should be rejected.
     */
    void acquire(String endpointKey);
}
