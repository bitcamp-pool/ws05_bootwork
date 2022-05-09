package project.home.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"project.home.study", "data.*"})
@MapperScan({"data.*"})
public class BootMiniProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootMiniProjectApplication.class, args);
	}

}
