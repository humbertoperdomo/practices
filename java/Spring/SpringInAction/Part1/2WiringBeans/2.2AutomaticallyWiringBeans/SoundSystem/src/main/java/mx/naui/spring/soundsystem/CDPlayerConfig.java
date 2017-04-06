package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan
//@ComponentScan(basePackages={"mx.naui.spring.soundsystem"})
@ComponentScan(basePackageClasses={CompactDisc.class})
public class CDPlayerConfig {
}
