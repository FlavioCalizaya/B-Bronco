package com.infinity.bronco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class BroncoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BroncoApplication.class, args);
	}

}
