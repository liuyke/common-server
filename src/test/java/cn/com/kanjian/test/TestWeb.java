package cn.com.kanjian.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.com.kanjian.controller.AuthController;
import cn.com.kanjian.jsonmsg.BaseResp;
import cn.com.kanjian.jsonmsg.SendSmsCodeReq;
import cn.com.kanjian.util.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-mvc.xml" })
public class TestWeb {

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	private AuthController authController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); 
	}
	
	@Test
	public void testSendSms() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/sendSmscode")
				.content(JsonUtil.obj2json(new SendSmsCodeReq("123", 1))).contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.content().string(JsonUtil.obj2json(BaseResp.createSuccessResp())))
		.andDo(MockMvcResultHandlers.print()).andReturn();
	}
	
}
