package pl.trosko.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;

@DataAmount
public class CustomerDto {

    @JsonProperty
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty
    private String pesel;

    @JsonIgnore
    @JsonProperty("credit_id")
    private Long creditId;
}
