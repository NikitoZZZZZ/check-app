package com.netcracker.checkapp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ServerApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

//    @Bean
//	CommandLineRunner init(CheckRepository checkRepository) {
//
//        return args -> {
//
//            System.out.println("-------");
//            System.out.println("Checks found for user 'rogeenok'");
//          List<Check> checkList = checkRepository.findByLoginAndPwd("rogeenok","123456");
//			for (Check checkvar: checkList)
//				System.out.println(checkvar);
//			User user = new User();
//			user.setLogin("rogeenok");
//			user.setPwd("123456");
//			Check check = checkRepository.findByUser(user);
//			System.out.println(check);
//			System.out.println("-------");
//
//        };
//    }
}
