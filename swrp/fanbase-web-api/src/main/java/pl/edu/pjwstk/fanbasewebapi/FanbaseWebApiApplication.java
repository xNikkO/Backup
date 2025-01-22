package pl.edu.pjwstk.fanbasewebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.edu.pjwstk")
public class FanbaseWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanbaseWebApiApplication.class, args);
    }

}
