package com.ursideus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("offers");
        return cacheManager;
    }
}
