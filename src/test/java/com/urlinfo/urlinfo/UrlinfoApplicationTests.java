package com.urlinfo.urlinfo;




import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.junit.Before;
import org.junit.Test;


import org.springframework.test.web.servlet.MockMvc;

public class UrlinfoApplicationTests {
	private UrlController rest;
	private MockMvc endpoint;

	@Before
	public void setup() {
		rest = new UrlController();
		endpoint = standaloneSetup(rest).build();
	}

	@Test
	public void create_ok() throws Exception {
		endpoint
				.perform(post("/urladd").contentType(APPLICATION_JSON).content("{\"url\": \"localhost1\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("localhost1"));

		endpoint
				.perform(post("/urladd").contentType(APPLICATION_JSON).content("{\"url\": \"localhost2\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("localhost2"));
	}

	@Test
	public void create_badRequest() throws Exception {
		endpoint
				.perform(post("/urladd").contentType(APPLICATION_JSON).content("{\"typoName\": \"localhost\"}"))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("'url' is required."));
	}

	@Test
	public void get_unsafeurl() throws Exception {
		endpoint
				.perform(get("/urlinfo").param("url", "abcd"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Url abcd is , unsafe!"));
	}

	@Test
	public void get_safeurl() throws Exception {
		endpoint
				.perform(get("/urlinfo").param("url", "localhost1"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Url localhost1 is , safe!"));
	}
}
