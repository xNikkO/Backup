package pl.edu.pjwstk.fanbasedataupdater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pl.edu.pjwstk" )
public class FanbaseDataUpdaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanbaseDataUpdaterApplication.class, args);
	}

}
