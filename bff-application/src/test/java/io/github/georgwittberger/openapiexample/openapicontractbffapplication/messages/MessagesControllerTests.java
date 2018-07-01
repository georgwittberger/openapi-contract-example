package io.github.georgwittberger.openapiexample.openapicontractbffapplication.messages;

import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponse;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponseData;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponseDataAttributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessagesController.class)
public class MessagesControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MessagesClient messagesClient;

  @Test
  public void returnsExpectedMessageText() throws Exception {
    MessageResponse response = new MessageResponse()
      .data(new MessageResponseData()
        .attributes(new MessageResponseDataAttributes().text("Hello World")));
    given(messagesClient.getMessageById("42")).willReturn(ResponseEntity.ok(response));
    mockMvc.perform(get("/api/v1/message").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.text", equalTo("Hello World")));
  }

  @Test
  public void returnsErrorStatusWhenMessageProviderReturnsErrorStatus() throws Exception {
    given(messagesClient.getMessageById("42")).willReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    mockMvc.perform(get("/api/v1/message").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isInternalServerError());
  }
}
