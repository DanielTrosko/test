package pl.trosko.credit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreditDto {

    @JsonProperty
    private Long id;

    @JsonProperty("credit_name")
    private String creditName;

    @JsonProperty
    private CustomerDto customer;

    @JsonProperty
    private ProductDto product;
}
