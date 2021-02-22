package com.investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.investment.service.InvestmentService;

@SpringBootApplication
public class InvestorAndFundingApplication implements ApplicationRunner {

	@Autowired
	InvestmentService is;

	public static void main(String[] args) {
		SpringApplication.run(InvestorAndFundingApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		is.initilizeValues();
	}

}
