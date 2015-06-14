package ru.test.geoip.db.maxmind;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.test.geoip.db.GeoData;
import ru.test.geoip.db.GeoDataProvider;
import ru.test.geoip.db.GetDataNotFound;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Asus
 * @see <a href="http://maxmind.github.io/GeoIP2-java/">http://maxmind.github.io/GeoIP2-java/<a/>
 */
@Component
@Qualifier("maxMindGeoDataProvider")
public class MaxMindProvider implements GeoDataProvider {

    private DatabaseReader reader;

    public MaxMindProvider() {
        try {
            URI uri = MaxMindProvider.class.getResource("/GeoLite2-City.mmdb").toURI();
            File file = new File(uri);
            // This creates the DatabaseReader object, which should be reused across lookups.
            this.reader = new DatabaseReader.Builder(file).build();
        } catch (URISyntaxException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public GeoData getByIp(InetAddress ipAddress) throws GetDataNotFound {
        try {
            return getCity(ipAddress);
        } catch (IOException | GeoIp2Exception e) {
            throw new GetDataNotFound(e);
        }
    }

    @Override
    public String getName() {
        return "MaxMind";
    }

    private GeoData getCity(InetAddress ipAddress) throws IOException, GeoIp2Exception {
        CityResponse response = reader.city(ipAddress);

        GeoData geoData = getGeoData(response);

//        System.out.println(country.getIsoCode());            // 'US'
//        System.out.println(country.getName());               // 'United States'
//        System.out.println(country.getNames().get("ru"));    // 'США'
//
//        Subdivision subdivision = response.getMostSpecificSubdivision();
//        System.out.println(subdivision.getName());    // 'Minnesota'
//        System.out.println(subdivision.getIsoCode()); // 'MN'
//
//        City city = response.getCity();
//        System.out.println(city.getName()); // 'Minneapolis'
//
//        Postal postal = response.getPostal();
//        System.out.println(postal.getCode()); // '55455'
//
//        Location location = response.getLocation();
//        System.out.println(location.getLatitude());  // 44.9733
//        System.out.println(location.getLongitude()); // -93.2323
        return geoData;
    }

    private GeoData getGeoData(CityResponse response) {
        GeoData geoData = new GeoData();

        Country country = response.getCountry();
        geoData.setCountryIsoCode(country.getIsoCode());
        geoData.setCountryName(country.getName());
        geoData.setCountryNameRu(country.getNames().get("ru"));
        geoData.setCountryGeoNameId(country.getGeoNameId());

        Subdivision subdivision = response.getMostSpecificSubdivision();
        geoData.setSubdivisionIsoCode(subdivision.getIsoCode());
        geoData.setSubdivisionName(subdivision.getName());
        geoData.setSubdivisionNameRu(subdivision.getNames().get("ru"));
        geoData.setSubdivisionGeoNameId(subdivision.getGeoNameId());

        City city = response.getCity();
        geoData.setCityName(city.getName());
        geoData.setCityNameRu(city.getNames().get("ru"));
        geoData.setCityGeoNameId(city.getGeoNameId());

        Postal postal = response.getPostal();
        geoData.setPostCode(postal.getCode());

        Location location = response.getLocation();
        geoData.setTimeZone(location.getTimeZone());
        geoData.setUsMetroCode(location.getMetroCode());
        geoData.setLatitude(location.getLatitude());
        geoData.setLongitude(location.getLongitude());
        return geoData;
    }
}