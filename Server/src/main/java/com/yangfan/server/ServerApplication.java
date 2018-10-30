package com.yangfan.server;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.WebApplicationInitializer;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableBatchProcessing
public class ServerApplication extends SpringBootServletInitializer implements WebApplicationInitializer, AsyncConfigurer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerApplication.class);
    }

//    @Override
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(100000);
//        executor.setMaxPoolSize(100000);
//        executor.setQueueCapacity(100000);
//        executor.initialize();
//        return executor;
//    }
//    /**
//     * The {@link AsyncUncaughtExceptionHandler} instance to be used
//     * when an exception is thrown during an asynchronous method execution
//     * with {@code void} return type.
//     */
//    @Override
//    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//        return null;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
