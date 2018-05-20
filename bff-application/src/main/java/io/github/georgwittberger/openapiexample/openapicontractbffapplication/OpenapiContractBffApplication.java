package io.github.georgwittberger.openapiexample.openapicontractbffapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenapiContractBffApplication {
  public static void main(String[] args) {
    SpringApplication.run(OpenapiContractBffApplication.class, args);
  }
}
