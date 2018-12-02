package com.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateLoanFileTest {

	@Autowired
	private CreateLoanFile createFile;
	
	@Autowired
	private CreateRepayPlanFile createRepayPlanFile;
	
	@Autowired
	private CreateRepayFile createRepayFile;
	
	@Test
	public void test() {
		createFile.loanFile();
		createRepayPlanFile.repayPlanFile();
		createRepayFile.repayFile();
	}

}
