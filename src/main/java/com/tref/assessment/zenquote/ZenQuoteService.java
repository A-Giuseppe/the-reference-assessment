package com.tref.assessment.zenquote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ZenQuoteService {

    private final RestTemplate restTemplate;

    public static final String DOMAIN_NAME = "https://zenquotes.io";
    public static final String QUOTE_OF_TODAY_URL = String.format("%s/api/today",DOMAIN_NAME);

    public Optional<ZenQuote> getZenCodeOfToday(){
        try{
            var response = restTemplate.getForEntity(QUOTE_OF_TODAY_URL, ZenQuote[].class);
            if(Objects.nonNull(response.getBody())){
                return Arrays.stream(response.getBody()).findFirst();
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }
}
