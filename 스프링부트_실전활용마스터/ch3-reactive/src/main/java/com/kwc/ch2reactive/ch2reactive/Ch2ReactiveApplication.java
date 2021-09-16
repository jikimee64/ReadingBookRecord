package com.kwc.ch2reactive.ch2reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class Ch2ReactiveApplication {

	public static void main(String[] args) {
		//블로킹 메소드 검출 역할
		BlockHound.install(); //run() 보다 앞에 위치해야 부트가 시작할대 블록하운드가 바이트코드를 조작

		BlockHound.builder()
				.allowBlockingCallsInside(
					TemplateEngine.class.getCanonicalName(), "process"
				).install();

		SpringApplication.run(Ch2ReactiveApplication.class, args);
	}

}
