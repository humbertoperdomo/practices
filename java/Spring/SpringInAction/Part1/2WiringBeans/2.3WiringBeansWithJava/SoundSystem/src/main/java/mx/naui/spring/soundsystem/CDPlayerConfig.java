package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * By default, all beans in Spring are singletons.
 */
@Configuration
public class CDPlayerConfig {

    @Bean(name="compactDisc")
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

    @Bean
    public CompactDisc randomBeatlesCD() {
        int choice = (int) Math.floor(Math.random() * 4);
        switch (choice) {
            case 0: return new SgtPeppers();
            case 1: return new WhiteAlbum();
            case 2: return new HardDaysNight();
            default: return new Revolver();
        }
    }

    @Bean
    public MediaPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }

    /*
     * Both CD Player beans will be given the same instance of SgtPeppers.
     */
    @Bean
    public MediaPlayer anotherCDPlayer() {
        return new CDPlayer(sgtPeppers());
    }

    /*
     * When Spring calls oneMoreCDPlayer to create a MediaPlayer bean, it
     * autowires a CompactDisc into the configuration method.
     * With this technique, the oneMoreCDPlayer method can still inject the
     * CompactDisc into the CDPlayer's constructor without explicitly referring
     * to the CompactDisc's @Bean method.
     */
    @Bean
    public MediaPlayer oneMoreCDPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }

    @Bean
    public MediaPlayer okLastCDPlayer(CompactDisc compactDisc) {
        CDPlayer cdPlayer = new CDPlayer(compactDisc);
        cdPlayer.setCompactDisc(compactDisc);
        return cdPlayer;
    }
}
