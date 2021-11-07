package pl.trosko.customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.trosko.customer.dto.CustomerDto;
import pl.trosko.customer.entity.Customer;
import pl.trosko.customer.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam("id") List<Long> ids) {
        log.info("GET: Get customers");

        List<CustomerDto> customers = customerService.getAll(ids).stream()
                .map(customer1 -> modelMapper.map(customer1, CustomerDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }

    @PostMapping("{creditId}")
    public ResponseEntity<CustomerDto> createCustomer(@PathVariable Long creditId, @RequestBody CustomerDto customer) {
        log.info("POST: Create new customer {}", customer);

        Customer savedCustomer = customerService.createCustomer(creditId, modelMapper.map(customer, Customer.class));
        return ResponseEntity.ok(modelMapper.map(savedCustomer, CustomerDto.class));
    }


}
