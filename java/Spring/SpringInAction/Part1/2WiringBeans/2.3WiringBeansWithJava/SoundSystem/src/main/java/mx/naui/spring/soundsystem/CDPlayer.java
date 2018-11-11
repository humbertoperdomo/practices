package mx.naui.spring.soundsystem;

import org.springframework.context.annotation.Bean;

public class CDPlayer implements MediaPlayer {
    private CompactDisc cd;

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }

    public void setCompactDisc(CompactDisc compactDisc) {
        this.cd = compactDisc;
    }

    public CompactDisc getCompactDisc() {
        return this.cd;
    }
}
