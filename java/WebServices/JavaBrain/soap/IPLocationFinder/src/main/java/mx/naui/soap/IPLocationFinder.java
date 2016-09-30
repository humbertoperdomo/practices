package mx.naui.soap;

import net.webservicex.GeoIPService;
import net.webservicex.GeoIPServiceSoap;
import net.webservicex.GeoIP;

/**
 * Hello world!
 *
 */
public class IPLocationFinder
{
    public static void main( String[] args )
    {
        if (args.length != 1) {
            System.out.println("You need to pass in one IP address");
        } else {
            String ipAddress = args[0];
            GeoIPService geoIPService = new GeoIPService();
            GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
            GeoIP geoIP = geoIPServiceSoap.getGeoIP(ipAddress);
            System.out.println(geoIP.getCountryName());
        }
    }
}
