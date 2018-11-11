package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.Bean;

public class Revolver implements CompactDisc {
    private String title = "Revolver";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
