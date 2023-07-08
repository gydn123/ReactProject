package com.exciting;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exciting.promotion.Repository.MyPointRepository;
import com.exciting.promotion.Repository.PromotionPriceRepository;

@SpringBootTest
@Transactional
class ExcitingamusementApplicationTests {
	
	@Autowired
	MyPointRepository myPointRepository;

	@Test
	void contextLoads() {
		
		myPointRepository.select_mypoint1("qwer1234");
	}

}
