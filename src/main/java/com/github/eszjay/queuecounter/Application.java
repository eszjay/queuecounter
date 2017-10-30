/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.jms.ConnectionFactory;
import java.io.Closeable;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.jms.listener.DefaultMessageListenerContainer.CACHE_CONNECTION;

@SpringBootApplication
@EnableJms
@EnableSwagger2
@ComponentScan(basePackages = {"com.github.eszjay.queuecounter", "com.github.eszjay.queuecounter"})
public class Application implements CommandLineRunner, Closeable {

    private static final AtomicLong thresholdCount = new AtomicLong();
    private static final AtomicLong totalCount = new AtomicLong();
    private static BigInteger currentMax = null;
    private static BigInteger currentMin = null;
    private static BigInteger threshold = null;

    public static BigInteger getCurrentMin() {
        return currentMin;
    }

    public static synchronized void setCurrentMin(BigInteger newMin) {
        if (currentMin == null || !currentMin.equals(newMin.min(currentMin))) {
            currentMin = newMin;
        }
    }

    public static BigInteger getCurrentMax() {
        return currentMax;
    }

    public static synchronized void setCurrentMax(BigInteger newMax) {
        if (currentMax == null || !currentMax.equals(newMax.max(currentMax))) {
            currentMax = newMax;
        }
    }

    public static long getTotalCount() {
        return totalCount.get();
    }

    public static long incrementAndGetTotalCount() {
        return Application.totalCount.incrementAndGet();
    }

    public static long getThresholdCount() {
        return thresholdCount.get();
    }

    public static long incrementAndGetThresholdCount() {
        return Application.thresholdCount.incrementAndGet();
    }

    public static BigInteger getThreshold() {
        return threshold;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0) {
            resetCounters();
            threshold = new BigInteger(arg0[0]);
        }
    }

    @Override
    public void close() {
        resetCounters();
        System.out.println("Closing and cleaning up");
    }

    private void resetCounters() {
        currentMax = null;
        currentMin = null;
        threshold = null;
        thresholdCount.set(0);
        totalCount.set(0);
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setCacheLevel(CACHE_CONNECTION);
        factory.setConcurrency("50-1000");
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("*");
            }
        };
    }

}
