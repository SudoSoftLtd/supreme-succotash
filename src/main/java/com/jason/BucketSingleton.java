package com.jason;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucket;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public enum BucketSingleton {

    INSTANCE();
    private final Bucket bucket = buildBucket();

    private LocalBucket buildBucket() {
        Refill refill = Refill.intervally(2, Duration.ofSeconds(intervalRange()));
        Bandwidth limit = Bandwidth.classic(2, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    private int intervalRange() {
        final int MIN_INTERVAL = 5;
        final int MAX_INTERVAL_BOUNDARY = 11;
        return ThreadLocalRandom.current().nextInt(MIN_INTERVAL, MAX_INTERVAL_BOUNDARY);
    }

    private BucketSingleton() {
    }

    public Bucket getBucket() {
        return bucket;
    }

    public BucketSingleton getInstance() {
        return INSTANCE;
    }

}
