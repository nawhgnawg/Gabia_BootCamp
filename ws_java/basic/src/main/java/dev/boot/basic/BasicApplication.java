package dev.boot.basic;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);

	}

}
