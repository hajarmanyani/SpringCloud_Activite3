package org.sid.billinservice.service;
import org.sid.billinservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}")
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping(path = "/customers")
    List<Customer> customers();
}

