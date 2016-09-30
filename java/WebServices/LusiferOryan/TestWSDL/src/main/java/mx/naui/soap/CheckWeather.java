package mx.naui.soap;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class CheckWeather {
    public static void main(String[] args) {
        GlobalWeather globalWeather = new GlobalWeather();
        GlobalWeatherSoap globalWeatherSoap = globalWeather.getGlobalWeatherSoap();
        String cityName = args[0];
        String countryName = args[1];
        System.out.println(cityName + " - " + countryName);
        String weather = globalWeatherSoap.getWeather(cityName, countryName);
        System.out.println(weather);
    }
}
