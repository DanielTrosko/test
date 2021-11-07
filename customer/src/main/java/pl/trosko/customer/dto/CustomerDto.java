package pl.trosko.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDto {

    @JsonProperty
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty
    private String pesel;
}
