package cn.com.kanjian.test;

import javax.inject.Inject;
import javax.xml.soap.Text;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.kanjian.service.IUserService;
import cn.com.kanjian.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class TestUserService {
	@Inject
	private IUserService userService;
	@Test
	public void test() {
		
		userService.appUserLogin("1213", "12345");
	}
}
