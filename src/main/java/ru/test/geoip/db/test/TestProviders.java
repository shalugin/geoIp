package ru.test.geoip.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.test.geoip.db.GeoDataProvider;
import ru.test.geoip.db.GetDataNotFound;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Asus
 */
@Component
public class TestProviders {

    private static final int REPEAT_COUNT = 10;
    private static final int IP_COUNT = 10000;

    @Autowired
    TestDataFeeder testDataFeeder;
    @Autowired
    @Qualifier("maxMindGeoDataProvider")
    GeoDataProvider maxMindProvider;
    @Autowired
    @Qualifier("ip2LocationGeoDataProvider")
    GeoDataProvider ip2LocationProvider;

    @PostConstruct
    public void postConstruct() {
        List<GeoDataProvider> providerList = new ArrayList<>();
        providerList.add(ip2LocationProvider);
        providerList.add(maxMindProvider);
        testAllProviders(providerList);
    }

    private void testAllProviders(Collection<GeoDataProvider> providers) {
        for (GeoDataProvider geoDataProvider : providers) {
            System.out.println("Start provider test: " + geoDataProvider.getName());
            runTest(REPEAT_COUNT, geoDataProvider);
            System.out.println("End provider test: " + geoDataProvider.getName());
        }
    }

    private void runTest(int times, GeoDataProvider geoDataProvider) {
        // prepare JVM
        for (int i = 0; i < times; i++) {
            runTestOnce(geoDataProvider);
        }

        long time = 0;
        for (int i = 0; i < times; i++) {
            time += runTestOnce(geoDataProvider);
        }
        long avgTime = time / times;

        System.out.println("Provider: " + geoDataProvider.getName() + ". Avg time: " + avgTime + " ms");
    }

    private long runTestOnce(GeoDataProvider geoDataProvider) {
        int ipCount = IP_COUNT;
        InetAddress[] addresses = testDataFeeder.generateTestData(ipCount);

        long l = System.currentTimeMillis();
        int errorCount = runTest(addresses, geoDataProvider);
        long time = System.currentTimeMillis() - l;
        System.out.println("Provider: " + geoDataProvider.getName() + ". Time: " + time + " ms. Errors: " + errorCount);
        return time;
    }

    private int runTest(InetAddress[] addresses, GeoDataProvider geoDataProvider) {
        int errorCount = 0;
        for (InetAddress address : addresses) {
            try {
                geoDataProvider.getByIp(address);
            } catch (GetDataNotFound getDataNotFound) {
                errorCount++;
            }
        }
        return errorCount;
    }
}