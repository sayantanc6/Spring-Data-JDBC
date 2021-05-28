package dummy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dummy.repo.MyBookDao;

@Configuration
public class MyConfig {

	@Bean
	public MyBookDao myBookDao() {
		return new MyBookDao() {
		}; 
	}
}
