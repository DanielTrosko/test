package pl.trosko.customer.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    ApplicationRunner mapperConfiguration(
            ModelMapper modelMapper
    ) {
        return args -> {
            modelMapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT);
        };
    }
}
