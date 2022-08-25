package com.sys.bo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching()
@SpringBootApplication
@MapperScan(value = {"com.sys.bo.*.*.repository", "com.sys.bo.*.repository"})
public class FssBoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FssBoApplication.class, args);
	}

}
