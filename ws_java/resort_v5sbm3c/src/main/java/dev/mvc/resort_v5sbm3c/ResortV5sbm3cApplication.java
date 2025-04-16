package dev.mvc.resort_v5sbm3c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.mvc"}) 		// Controller, DAO, Process class 등을 자동으로 인식할 패키지 선언
public class ResortV5sbm3cApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResortV5sbm3cApplication.class, args);
	}

}
