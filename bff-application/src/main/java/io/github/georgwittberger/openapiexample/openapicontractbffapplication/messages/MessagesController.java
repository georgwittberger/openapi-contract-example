package io.github.georgwittberger.openapiexample.openapicontractbffapplication.messages;

import io.github.georgwittberger.openapiexample.openapicontractbffapplication.api.handler.MessagesApi;
import io.github.georgwittberger.openapiexample.openapicontractbffapplication.api.model.MessageResponse;
import io.github.georgwittberger.openapiexample.openapicontractbffapplication.config.ApiConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfiguration.API_V1_BASE_PATH)
public class MessagesController implements MessagesApi {
  private static final Logger log = LoggerFactory.getLogger(MessagesController.class);
  private MessagesClient messagesClient;

  @Autowired
  public MessagesController(MessagesClient messagesClient) {
    this.messagesClient = messagesClient;
  }

  @Override
  public ResponseEntity<MessageResponse> getMessage() {
    ResponseEntity<io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponse> messageResponse =
      messagesClient.getMessageById("42");
    if (messageResponse.getStatusCode() == HttpStatus.OK && messageResponse.getBody() != null) {
      log.info("Sending response with message from the microservice");
      return ResponseEntity.ok(new MessageResponse().text(messageResponse.getBody().getData().getAttributes().getText()));
    }
    log.error("Message from the microservice could not be retrieved");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
