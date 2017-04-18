package cn.com.kanjian.test;

import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:spring-context.xml")
public class BaseTester extends AbstractTransactionalJUnit4SpringContextTests {

	@BeforeClass
	public static void setUp() {
		
	}
	
}
