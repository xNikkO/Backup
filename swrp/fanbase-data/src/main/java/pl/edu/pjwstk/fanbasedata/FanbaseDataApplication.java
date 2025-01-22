package pl.edu.pjwstk.fanbasedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.edu.pjwstk")
public class FanbaseDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanbaseDataApplication.class, args);
    }

}
