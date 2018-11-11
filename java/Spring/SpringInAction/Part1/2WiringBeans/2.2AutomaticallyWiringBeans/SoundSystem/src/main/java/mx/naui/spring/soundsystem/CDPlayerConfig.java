package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan // will default scanning the same package as the configuration file
//@ComponentScan(basePackages={"mx.naui.spring.soundsystem"}) // if you want to scan a different package; you can specify multiple base packages
//@ComponentScan(basePackageClasses={CompactDisc.class}) // you can specify via classes or interfaces, whatever packages those classes are in will be used as the base package for component scanning.
public class CDPlayerConfig {
}
