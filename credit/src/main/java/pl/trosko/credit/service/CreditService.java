package pl.trosko.credit.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.trosko.credit.dto.CreditDto;
import pl.trosko.credit.dto.CustomerDto;
import pl.trosko.credit.dto.ProductDto;
import pl.trosko.credit.entity.Credit;
import pl.trosko.credit.repository.CreditRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final ModelMapper modelMapper;

    public List<CreditDto> getAllCredits() {
        List<CreditDto> credits = creditRepository.findAll().stream()
                .map(credit -> modelMapper.map(credit, CreditDto.class))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < credits.size(); i++) {
            if (i + 1 != credits.size()) {
                sb.append(credits.get(i).getId()).append(",");
            } else {
                sb.append(credits.get(i).getId());
            }
        }

        RestTemplate template = new RestTemplate();
        ResponseEntity<CustomerDto[]> customers = template
                .getForEntity("http://localhost:8081/api/v1/customers?id=" + sb, CustomerDto[].class);

        ResponseEntity<ProductDto[]> products = template
                .getForEntity("http://localhost:8082/api/v1/products?id=" + sb, ProductDto[].class);

        credits.forEach(credit -> {
            credit.setCustomer(Arrays.stream(Objects.requireNonNull(customers.getBody()))
                    .findFirst()
                    .orElseThrow(EntityNotFoundException::new));

            credit.setProduct(Arrays.stream(Objects.requireNonNull(products.getBody()))
                    .findFirst()
                    .orElseThrow(EntityNotFoundException::new));
        });
        return credits;
    }

    public Long createCredit(CreditDto credit) {
        Credit creditToSave = modelMapper.map(credit, Credit.class);
        CreditDto savedCredit = modelMapper.map(creditRepository.save(creditToSave), CreditDto.class);
        RestTemplate template = new RestTemplate();
        CustomerDto customer = template
                .postForObject("http://localhost:8081/api/v1/customers/" + creditToSave.getId(),
                        credit.getCustomer(),
                        CustomerDto.class);
        ProductDto product = template
                .postForObject("http://localhost:8082/api/v1/products/" + creditToSave.getId(),
                        credit.getProduct(),
                        ProductDto.class);


        savedCredit.setCustomer(customer);
        savedCredit.setProduct(product);
        return savedCredit.getId();
    }
}
