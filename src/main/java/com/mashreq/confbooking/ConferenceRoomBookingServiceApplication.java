package com.mashreq.confbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@SpringBootApplication(exclude={ SecurityAutoConfiguration.class}, scanBasePackages = { "com.mashreq.confbooking" })
@ComponentScan(basePackages = {"com.mashreq.confbooking", "com.mashreq.confbooking.scheduler"})
@EntityScan("com.mashreq.confbooking.model")
@EnableJpaRepositories("com.mashreq.confbooking.repository")
@EnableScheduling
public class ConferenceRoomBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceRoomBookingServiceApplication.class, args);
	}

}
