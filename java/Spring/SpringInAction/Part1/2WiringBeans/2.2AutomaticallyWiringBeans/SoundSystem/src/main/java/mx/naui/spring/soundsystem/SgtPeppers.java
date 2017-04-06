package mx.naui.spring.soundsystem;

//import javax.inject.Named;
import org.springframework.stereotype.Component;

//@Named
@Component
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
