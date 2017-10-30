/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Receiver {

    @JmsListener(destination = "NumbersToProcess", containerFactory = "myFactory")
    public void receiveMessage(String number) throws InterruptedException {
        while (Application.getThreshold() == null) {
            Thread.sleep(10);
        }

        long count = Application.getTotalCount();

        //Thread.sleep(ThreadLocalRandom.current().nextLong(100L)); //To test that ordering does not affect results

        System.out.println(count + " - Thread " + Thread.currentThread().getName() + " received <" + number + ">");
        BigInteger newNumber = new BigInteger(number);
        if (Application.getCurrentMax() == null || newNumber.equals(newNumber.max(Application.getCurrentMax()))) {
            Application.setCurrentMax(newNumber);
            //System.out.println(count + " - New Max is " + number);
        }
        if (Application.getCurrentMin() == null || newNumber.equals(newNumber.min(Application.getCurrentMin()))) {
            Application.setCurrentMin(newNumber);
            //System.out.println(count + " - New Min is " + number);
        }
        if (Application.getThreshold() == null || !Application.getThreshold().equals(newNumber.max(Application.getThreshold()))) {
            long thresholdCount = Application.incrementAndGetThresholdCount();
            //System.out.println(count + " - Count above threshold of " + Application.getThreshold() + " is " + thresholdCount);
        }

        Application.incrementAndGetTotalCount();
    }

}
