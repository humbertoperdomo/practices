package mx.naui.soap;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

/**
 * Test WSDL
 *
 */
public class TestWSDL
{
    public static void main( String[] args )
    {
        GlobalWeather globalWeather = new GlobalWeather();
        GlobalWeatherSoap globalWeatherSoap = globalWeather.getGlobalWeatherSoap();
        String countryName = args[0];
        String value = globalWeatherSoap.getCitiesByCountry(countryName);
        System.out.println(value);
        
    }
}
