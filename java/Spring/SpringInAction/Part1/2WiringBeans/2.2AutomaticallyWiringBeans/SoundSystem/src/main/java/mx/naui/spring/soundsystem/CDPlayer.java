package mx.naui.spring.soundsystem;

//import javax.inject.Inject;
//import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Named
public class CDPlayer implements MediaPlayer {
    private CompactDisc cd;

    /*
     * This Bean should be instantiated via this constructor, that's what indicates @Autowired
     * and pass in a bean that is assignable to CompactDisc
     */
    @Autowired(required=true)
    //@Inject
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
