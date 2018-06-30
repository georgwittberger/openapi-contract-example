package io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.messages;

import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.handler.MessagesApi;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponse;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponseData;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.model.MessageResponseDataAttributes;
import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.config.ApiConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping(ApiConfiguration.API_V1_BASE_PATH)
public class MessagesController implements MessagesApi {
  private static final Logger log = LoggerFactory.getLogger(MessagesController.class);

  /*
   * IMPORTANT: Parameter annotations must be copied from the interface to this class because they are not inherited!
   */
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

    log.info("Sending response with message for given ID {}", id);
    return ResponseEntity.ok(messageResponse);
  }
}
