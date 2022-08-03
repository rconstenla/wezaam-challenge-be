package com.wezaam.withdrawal.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
class WithdrawalTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	@Test
	public void findOneUser() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(get("/find-user-by-id/2"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value("2"))
				.andExpect(jsonPath("$.firstName").value("Arnold"));
	}

	@Test
	public void withdrawalTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		mockMvc.perform(post("/create-withdrawals")
                .param("executeAt", "ASAP")
                .param("userId", "2")
                .param("paymentMethodId", "3")
                .param("amount", "3000.0"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value("3000.0"))
				.andExpect(jsonPath("$.userId").value("2"))
				.andExpect(jsonPath("$.paymentMethodId").value("3"))
				.andExpect(jsonPath("$.status").value("PROCESSING"));
	}
	
}
