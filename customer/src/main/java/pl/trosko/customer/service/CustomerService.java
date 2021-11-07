package pl.trosko.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.trosko.customer.entity.Customer;
import pl.trosko.customer.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAll(List<Long> ids) {
        return customerRepository.findByIds(ids);
    }

    @Transactional
    public Customer createCustomer(Long creditId, Customer customer) {
        customer.setCreditId(creditId);
        return customerRepository.save(customer);
    }
}
