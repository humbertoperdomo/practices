package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class CDPlayerConfig {

    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

    public MediaPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }
}
