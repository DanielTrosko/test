package pl.trosko.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    @JsonProperty
    private String name;

    @JsonProperty
    private BigDecimal value;

    @JsonIgnore
    @JsonProperty("credit_id")
    private Long creditId;
}
