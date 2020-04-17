package com.sberbank.task3;

import com.revinate.guava.util.concurrent.RateLimiter;

public class GlobalVariables {

    public static volatile RateLimiter globalRateLimiter;

    public static volatile int totalSize = 0;

}
