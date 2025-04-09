package dev.mvc.mvcjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"dev.mvc"})
//@ComponentScan(basePackages = {"dev.mvc"})
public class MvcjsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcjsApplication.class, args);
	}


}
