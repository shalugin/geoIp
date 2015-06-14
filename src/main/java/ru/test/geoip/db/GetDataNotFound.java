package ru.test.geoip.db;

/**
 * @author Asus
 */
public class GetDataNotFound extends Exception {

    public GetDataNotFound(Throwable cause) {
        super(cause);
    }

    public GetDataNotFound(String message) {
        super(message);
    }
}