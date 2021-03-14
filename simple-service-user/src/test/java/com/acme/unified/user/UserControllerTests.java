
package com.acme.unified.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.acme.unified.user.web.dto.AddressDTO;
import com.acme.unified.user.web.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private MockMvc mockMvc;


	UserDTO u1DTO = new UserDTO("foo1","bar1", "foo1@bar.com", "abc123456");
	UserDTO u2DTO = new UserDTO("foo2","bar2", "foo2@bar.com", "abc123456");

	AddressDTO a1DTO = new AddressDTO("address_1", "address_2", "city", "state", "zipcode", "country");
	
	/** 
	 * @throws Exception
	 */
	@Test
	public void mockCreateUserRetrieveUser() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String  jsonInString = mapper.writeValueAsString(u1DTO);
		LOGGER.info("MOCK Create User Success: START");
		mockMvc.perform(post("/api/1.0/user/publish/{publish}", "false")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(jsonInString)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		LOGGER.info("MOCK Create User Success: END");

		LOGGER.info("MOCK Get User By Email: START");
		mockMvc.perform(get("/api/1.0/user/email/{email}", "foo1@bar.com")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		LOGGER.info("MOCK Get User By Email: END");

		jsonInString = mapper.writeValueAsString(a1DTO);
		LOGGER.info("MOCK Create Address For User: START");
		mockMvc.perform(post("/api/1.0/user/address/{userid}", 1)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(jsonInString)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		LOGGER.info("MOCK Create Address FOr User: END");
		
		LOGGER.info("MOCK Get User By County: START");
		mockMvc.perform(get("/api/1.0/user/country/{country}", "country")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		LOGGER.info("MOCK Get User By Country: END");

	}

	@Test
	public void mockGetUserWithEmailThatDoesnotExist() throws Exception {

		LOGGER.info("MOCK Get User By Bad Email: START");
		mockMvc.perform(get("/api/1.0/user/email/{email}", "no@exist.com")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isNotFound())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		LOGGER.info("MOCK Get User By Email: END");

	}

	@Test
	public void mockCreateUserGetUserByCountry() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String  jsonInString = mapper.writeValueAsString(u2DTO);
		LOGGER.info("MOCK Create User Success: START");
		mockMvc.perform(post("/api/1.0/user/publish/{publish}", "false")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(jsonInString)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		LOGGER.info("MOCK Create User Success: END");

		LOGGER.info("MOCK Get User By County: START");
		mockMvc.perform(get("/api/1.0/user/country/{country}", "country")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print()).andReturn();
		LOGGER.info("MOCK Get User By Country: END");
		

	}


	
}
