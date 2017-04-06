package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDPlayerConfig {

    @Bean(name="compactDisc")
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

    @Bean
    public MediaPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }
}
