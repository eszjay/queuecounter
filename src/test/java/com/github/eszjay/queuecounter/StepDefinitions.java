/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter;

import cucumber.api.java.After;
import cucumber.api.java8.En;
import org.junit.Assert;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class StepDefinitions implements En {

    private static final String baseUrl = "http://localhost:8080/v1";
    private static ApplicationContext application;

    public StepDefinitions() {

        Given("^the JMS broker is running$", () -> {
            //TODO Assert this precondition
        });

        Given("^there are (\\d+) random items in the queue$", (Integer count) -> {
            for (int i = 0; i < count; i++) {
                TestUtilities.sendToQueue(String.valueOf(ThreadLocalRandom.current().nextLong()));
            }
        });

        When("^queuecounter is started with a threshold of (\\d+)$", (String threshold) -> {
            String[] args = {threshold};
            application = SpringApplication.run(Application.class, args);
            try {
                sleep(5000); //The application takes about 5 seconds to initialize
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Then("^(\\d+) items are processed within (\\d+) seconds$", (Long expected, Long timeout) -> {
                    Assert.assertEquals(expected.toString(), TestUtilities.getWithTimeout(expected.toString(), timeout, baseUrl + "/count/total"));
                }
        );

        Given("^(-\\d+|\\d+) is in the queue$", TestUtilities::sendToQueue);

        Then("^Maximum is (-\\d+|\\d+)$", (String expected) -> {
            Assert.assertEquals(expected, TestUtilities.getWithTimeout(expected, 5, baseUrl + "/max"));
        });

        Then("^Count is (\\d+)$", (Long expected) -> {
            Assert.assertEquals(expected.toString(), TestUtilities.getWithTimeout(expected.toString(), 5, baseUrl + "/count/abovethreshold"));
        });

        Given("^(.+) is loaded in the queue$", TestUtilities::sendFileToQueue);

        Then("^Minimum is (-\\d+|\\d+)$", (String expected) -> {
            Assert.assertEquals(expected, TestUtilities.getWithTimeout(expected, 5, baseUrl + "/min"));
        });
    }

    @After
    public void afterScenario() {
        System.out.println("Attempt to shutdown queuecounter resulted in: " + SpringApplication.exit(application, (ExitCodeGenerator) () -> 0));
    }
//    private static class UnsatisfiedDependencyException extends RuntimeException {
//
//        private static final long serialVersionUID = 1L;
//
//        UnsatisfiedDependencyException(Throwable cause) {
//            super(cause);
//        }
//    }
}
