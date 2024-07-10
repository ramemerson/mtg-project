package com.gft.newmagicplatform;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gft.newmagicplatform.service.AccountService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class NewmagicplatformApplication implements CommandLineRunner {

	AccountService accountService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(NewmagicplatformApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

	}

}
