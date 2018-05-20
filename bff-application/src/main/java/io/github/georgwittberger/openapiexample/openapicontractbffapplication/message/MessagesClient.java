package io.github.georgwittberger.openapiexample.openapicontractbffapplication.message;

import io.github.georgwittberger.openapiexample.openapicontractmicroserviceapplication.api.client.MessagesApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "messages", url = "${messages.service-url}")
public interface MessagesClient extends MessagesApi {
}
