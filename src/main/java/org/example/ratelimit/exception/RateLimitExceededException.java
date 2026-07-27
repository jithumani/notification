package org.example.ratelimit.exception;

public class RateLimitExceededException extends RuntimeException {
    private final String endpointKey;

    public RateLimitExceededException(String endpointKey) {
        super("Rate limit exceeded for endpoint: " + endpointKey);
        this.endpointKey = endpointKey;
    }

    public String getEndpointKey() {
        return endpointKey;
    }
}
