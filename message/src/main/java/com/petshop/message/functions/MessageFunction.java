package com.petshop.message.functions;

import com.petshop.message.dto.PetsMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Function;

@Configuration
public class MessageFunction {

    private static final Logger log = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<PetsMessageDto, PetsMessageDto> email() {
        return petsMessageDto -> {
            log.info("Sending email with the details: {}", petsMessageDto.toString());
            return petsMessageDto;
        };
    }

    @Bean
    public Function<PetsMessageDto, UUID> sms() {
        return petsMessageDto -> {
            log.info("Sending SMS with the details: {}", petsMessageDto.toString());
            return petsMessageDto.petUuid();
        };
    }
}
