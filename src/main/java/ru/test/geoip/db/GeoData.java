package ru.test.geoip.db;

/**
 * @author Asus
 */
public class GeoData {

    private String countryIsoCode;
    private String countryName;
    private String countryNameRu;
    private Integer countryGeoNameId;

    private String subdivisionIsoCode;
    private String subdivisionName;
    private String subdivisionNameRu;
    private Integer subdivisionGeoNameId;

    private String cityName;
    private String cityNameRu;
    private Integer cityGeoNameId;

    private String postCode;
    private String timeZone;
    private Integer usMetroCode;
    private Double latitude;
    private Double longitude;

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryNameRu(String countryNameRu) {
        this.countryNameRu = countryNameRu;
    }

    public String getCountryNameRu() {
        return countryNameRu;
    }

    public void setCountryGeoNameId(Integer countryGeoNameId) {
        this.countryGeoNameId = countryGeoNameId;
    }

    public Integer getCountryGeoNameId() {
        return countryGeoNameId;
    }

    public void setSubdivisionIsoCode(String subdivisionIsoCode) {
        this.subdivisionIsoCode = subdivisionIsoCode;
    }

    public String getSubdivisionIsoCode() {
        return subdivisionIsoCode;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionNameRu(String subdivisionNameRu) {
        this.subdivisionNameRu = subdivisionNameRu;
    }

    public String getSubdivisionNameRu() {
        return subdivisionNameRu;
    }

    public void setSubdivisionGeoNameId(Integer subdivisionGeoNameId) {
        this.subdivisionGeoNameId = subdivisionGeoNameId;
    }

    public Integer getSubdivisionGeoNameId() {
        return subdivisionGeoNameId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityNameRu(String cityNameRu) {
        this.cityNameRu = cityNameRu;
    }

    public String getCityNameRu() {
        return cityNameRu;
    }

    public void setCityGeoNameId(Integer cityGeoNameId) {
        this.cityGeoNameId = cityGeoNameId;
    }

    public Integer getCityGeoNameId() {
        return cityGeoNameId;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setUsMetroCode(Integer usMetroCode) {
        this.usMetroCode = usMetroCode;
    }

    public Integer getUsMetroCode() {
        return usMetroCode;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}