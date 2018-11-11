package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.Bean;

public class WhiteAlbum implements CompactDisc {
    private String title = "The Beatles ('The Withe Album')";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
