package io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.messages;

import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.handler.MessagesApi;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponse;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponseData;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponseDataAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class MessagesController implements MessagesApi {
  @Override
  public ResponseEntity<MessageResponse> getMessageById(@PathVariable("id") String id) {
    MessageResponseDataAttributes messageAttributes = new MessageResponseDataAttributes();
    messageAttributes.setText("Hello from the microservice!");
    messageAttributes.setLastModified(OffsetDateTime.now());

    MessageResponseData messageData = new MessageResponseData();
    messageData.setType("messages");
    messageData.setId(id);
    messageData.setAttributes(messageAttributes);

    MessageResponse messageResponse = new MessageResponse();
    messageResponse.setData(messageData);

    return ResponseEntity.ok(messageResponse);
  }
}
