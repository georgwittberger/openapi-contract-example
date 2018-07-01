package io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.messages;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessagesController.class)
public class MessagesControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void returnsMessageWithGivenId() throws Exception {
    mockMvc.perform(get("/api/v1/messages/42").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.id", equalTo("42")))
      .andExpect(jsonPath("$.data.type", equalTo("messages")))
      .andExpect(jsonPath("$.data.attributes.text", equalTo("Hello from the microservice!")));
  }
}
