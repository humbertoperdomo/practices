package mx.naui.spring.knights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.naui.spring.knights.BraveKnight;
import mx.naui.spring.knights.Knight;
import mx.naui.spring.knights.Quest;
import mx.naui.spring.knights.SlayDragonQuest;

@Configuration
public class KnightConfig {

    @Bean
    public Knight knight() {
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }
}
