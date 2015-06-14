package ru.test.geoip.db.test;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author Asus
 */
@Component
public class TestDataFeeder {

    private static final Random RANDOM = new Random();

    public InetAddress[] generateTestData(int ipCount) {
        InetAddress[] addresses = new InetAddress[ipCount];

        TestDataFeeder testDataFeeder = new TestDataFeeder();
        for (int i = 0; i < ipCount; i++) {
            InetAddress ipAddress = testDataFeeder.generateNewIpAddress();
            addresses[i] = ipAddress;
        }

        return addresses;
    }

    public InetAddress generateNewIpAddress() {
        try {
            byte[] bytes = new byte[4];
            RANDOM.nextBytes(bytes);
            return InetAddress.getByAddress(bytes);
        } catch (UnknownHostException e) {
            return generateNewIpAddress();
        }
    }
}