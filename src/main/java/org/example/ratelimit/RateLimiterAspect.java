package org.example.ratelimit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.ratelimit.service.RateLimiter;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimiterAspect {

    private final RateLimiter rateLimiter;

    public RateLimiterAspect(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Before("@annotation(rateLimited)")
    public void enforceRateLimit(
            JoinPoint joinPoint,
            RateLimited rateLimited) {

        rateLimiter.acquire(rateLimited.key());

    }
}
