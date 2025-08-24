package com.simplecoding.simlesuggestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/* 반드시 아래를 넣어야 생성일시/수정일시가 들어감 */
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.simplecoding.simlesuggestion.jpa")
@EnableElasticsearchRepositories(basePackages = "com.simplecoding.simlesuggestion.es")
public class SimlesuggestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimlesuggestionApplication.class, args);
	}
}
