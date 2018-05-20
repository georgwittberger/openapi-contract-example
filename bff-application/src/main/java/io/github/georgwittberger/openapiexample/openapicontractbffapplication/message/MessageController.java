package io.github.georgwittberger.openapiexample.openapicontractbffapplication.message;

import io.github.georgwittberger.openapiexample.openapicontractbffapplication.api.handler.BffApi;
import io.github.georgwittberger.openapiexample.openapicontractbffapplication.api.model.Message;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController implements BffApi {
  private MessagesClient messagesClient;

  @Autowired
  public MessageController(MessagesClient messagesClient) {
    this.messagesClient = messagesClient;
  }

  @Override
  public ResponseEntity<Message> getMessage() {
    ResponseEntity<MessageResponse> messageResponse = messagesClient.getMessageById("42");
    if (messageResponse.getStatusCode() == HttpStatus.OK) {
      return ResponseEntity.ok(new Message().text(messageResponse.getBody().getData().getAttributes().getText()));
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
