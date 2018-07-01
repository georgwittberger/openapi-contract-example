package io.github.georgwittberger.openapiexample.openapicontractbffapplication.messages;

import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
  "messages.ribbon.listOfServers=localhost:13082"
})
@AutoConfigureWireMock(port = 13082, files = "classpath:/mocks/messages")
public class MessagesClientTests {
  @Autowired
  private MessagesClient messagesClient;

  @Test
  public void requestsMessageProviderWithGivenId() {
    ResponseEntity<MessageResponse> messageResponse = messagesClient.getMessageById("42");
    assertThat(messageResponse).isNotNull();
    assertThat(messageResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(messageResponse.getBody()).isNotNull();
    assertThat(messageResponse.getBody().getData().getId()).isEqualTo("42");
    assertThat(messageResponse.getBody().getData().getType()).isEqualTo("messages");
    assertThat(messageResponse.getBody().getData().getAttributes().getText()).isEqualTo("Hello World");
  }
}
