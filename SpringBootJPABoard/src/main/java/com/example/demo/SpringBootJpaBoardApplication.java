package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.demo", "board.*"})
@EntityScan("board.data")
@EnableJpaRepositories("board.data")
public class SpringBootJpaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaBoardApplication.class, args);
	}

}

/*
스프링 데이터 JPA
	JPA를 스프링에서 쉽게 사용할 수 있도록 도와주는 프레임워크로 
	내부적으로 하이버네이트를 이용해서 기능을 구현하고 있습니다. 
JPA(Java Persistence API)란 
	자바 객체와 데이터베이스 테이블 간의 매핑을 처리하는 
	ORM(Object Relational Mapping) 기술의 표준입니다. 
	ORM이란 객체와 관계를 설정하는 것입니다. 
	객체와 관계형 데이터베이스를 매핑시킨다는 개념입니다. 
*/
