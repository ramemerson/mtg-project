package com.gft.newmagicplatform;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.service.AccountService;

import jakarta.transaction.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NewmagicplatformApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AccountService accountService;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testGetAccountById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/account/getById?id=1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.firstname").value("Chris"));
	}

	@Test
	public void testGetAccountByIdUnsuccessful() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/account/getById?id=1234"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testAccountCreation() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
				.param("firstname", "test Firstname")
				.param("lastname", "test Lastname")
				.param("username", "test Userame")
				.param("email", "test@email.com")
				.param("password", "test123")
				.param("birthday", "2000-12-12"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testUnsuccessfulAccountCreation() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
				.param("firstname", "test Firstname")
				.param("lastname", "test Lastname")
				.param("username", "test Userame")
				.param("email", "test@email.com")
				.param("password", "test123")
				.param("birthday", "wrong input"))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testUpdateAccount_withExistingId_ShouldUpdateAccount() throws Exception {
		// Creating existing account in the db to update
		Account existingAccount = new Account("John", "Doe", "johndoe", "password", "2000-12-12", "johndoe@test.com");
		accountService.save(existingAccount);

		// Creating account to use as updating data
		Account updatedData = new Account("Jane", "Doe", "janedoe", "newpassword", "2000-10-10", "janedoe@test.com");
		updatedData.setId(existingAccount.getId());

		// Building the update request body
		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(updatedData);
		System.out.println(requestBody);

		// Performing update request
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/account/update/{id}", existingAccount.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk())
				.andReturn();

		// Verify the response is empty (because update method is void)
		assertNull(result.getResponse().getContentAsString());

		// Fetch updated account from the database
		Optional<Account> updatedAccount = Optional.of(accountService.getAccountById(existingAccount.getId()));

		// Assert that the account was updated with the new data
		assertTrue(updatedAccount.isPresent());
		assertTrue(updatedAccount.get().getFirstname().equals(updatedData.getFirstname()));

	}

}
