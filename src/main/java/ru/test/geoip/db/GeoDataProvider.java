package ru.test.geoip.db;

import java.net.InetAddress;

/**
 * @author Asus
 */
public interface GeoDataProvider {

    GeoData getByIp(InetAddress ipAddress) throws GetDataNotFound;

    String getName();
}