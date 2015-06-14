package ru.test.geoip.db.ip2location;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.test.geoip.db.GeoData;
import ru.test.geoip.db.GeoDataProvider;
import ru.test.geoip.db.GetDataNotFound;

import java.net.InetAddress;
import java.net.URISyntaxException;

/**
 * @author Asus
 */
@Component
@Qualifier("ip2LocationGeoDataProvider")
public class Ip2LocationProvider implements GeoDataProvider {

    private IP2Location location;

    public Ip2LocationProvider() {
        try {
            location = new IP2Location();
            location.IPDatabasePath = Ip2LocationProvider.class.getResource("/IP2LOCATION-LITE-DB11.BIN").toURI().getPath();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public GeoData getByIp(InetAddress ipAddress) throws GetDataNotFound {
        try {
            String ip = ipAddress.toString().replace("/", "");
            IPResult rec = location.IPQuery(ip);
            if ("OK".equals(rec.getStatus())) {
                return getGeoData(rec);
            } else {
                throw new GetDataNotFound("Error: " + rec.getStatus());
            }
        } catch (Exception e) {
            throw new GetDataNotFound(e);
        }
    }

    @Override
    public String getName() {
        return "Ip2Location";
    }


    private GeoData getGeoData(IPResult ipResult) {
        GeoData geoData = new GeoData();

        geoData.setCountryIsoCode(ipResult.getCountryShort());
        geoData.setCountryName(ipResult.getCountryLong());
//        geoData.setCountryNameRu(country.getNames().get("ru"));
//        geoData.setCountryGeoNameId(country.getGeoNameId());

//        geoData.setSubdivisionIsoCode(subdivision.getIsoCode());
        geoData.setSubdivisionName(ipResult.getRegion());
//        geoData.setSubdivisionNameRu(subdivision.getNames().get("ru"));
//        geoData.setSubdivisionGeoNameId(subdivision.getGeoNameId());

        geoData.setCityName(ipResult.getCity());
//        geoData.setCityNameRu(city.getNames().get("ru"));
//        geoData.setCityGeoNameId(city.getGeoNameId());

        geoData.setPostCode(ipResult.getZipCode());

        geoData.setTimeZone(ipResult.getTimeZone());
//        geoData.setUsMetroCode(location.getMetroCode());
        geoData.setLatitude((double) ipResult.getLatitude());
        geoData.setLongitude((double) ipResult.getLongitude());

        return geoData;
    }

}
