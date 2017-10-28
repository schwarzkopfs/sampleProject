package ssb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("th.co.ais.repository")
public class SsbDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsbDbServiceApplication.class, args);
	}
	
}
