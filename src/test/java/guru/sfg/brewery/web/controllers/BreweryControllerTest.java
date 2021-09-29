package guru.sfg.brewery.web.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BreweryControllerTest extends BaseIT {

  @Test
  void listBreweriesCustomerRole() throws Exception {
    mockMvc.perform(get("/api/v1/breweries")
        .with(httpBasic("scott", "tiger")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  void listBreweriesUserRole() throws Exception {
    mockMvc.perform(get("/api/v1/breweries")
            .with(httpBasic("user", "password")))
        .andExpect(status().isForbidden());
  }

  @Test
  void listBreweriesNoRole() throws Exception {
    mockMvc.perform(get("/api/v1/breweries"))
        .andExpect(status().isUnauthorized());
  }
}
