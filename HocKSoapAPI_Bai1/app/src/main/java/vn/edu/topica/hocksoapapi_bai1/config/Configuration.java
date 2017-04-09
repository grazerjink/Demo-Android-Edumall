package vn.edu.topica.hocksoapapi_bai1.config;

/**
 * Created by KJ Mok on 01/02/2017.
 */

public class Configuration {
    public static String SERVER_URL = "http://www.w3schools.com/xml/tempconvert.asmx";
    public static String NAME_SPACE = "http://www.w3schools.com/xml/";
    public static String METHOD_C_TO_F = "CelsiusToFahrenheit";
    public static String PARAM_C_TO_F_CELSIUS = "Celsius";
    public static String SOAPACTION_C_TO_F = NAME_SPACE+METHOD_C_TO_F;
}
