package com.tref.assessment.zenquote;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ZenQuoteServiceTest {

    @Mock
    RestTemplate restTemplate;

    ZenQuote[] zenQuotes = {new ZenQuote("quote", "author","richQuote")};

    @InjectMocks
    ZenQuoteService zenQuoteService;

    @Test
    public void shouldReturnTheQuoteGetFromTheApi_whenTheApiIsAvailable(){

        ResponseEntity mockResponseEntity = mock(ResponseEntity.class);
        given(mockResponseEntity.getBody()).willReturn(zenQuotes);
        given(restTemplate.getForEntity(anyString(), any())).willReturn(mockResponseEntity);

        Optional<ZenQuote> quote = zenQuoteService.getZenCodeOfToday();

        assertEquals("quote", quote.get().getQuote());
    }

    @Test
    public void shouldReturnAnEmptyOptional_whenBodyIsNul(){
        ResponseEntity mockResponseEntity = mock(ResponseEntity.class);
        given(mockResponseEntity.getBody()).willReturn(null);
        given(restTemplate.getForEntity(anyString(), any())).willReturn(mockResponseEntity);

        Optional<ZenQuote> quote = zenQuoteService.getZenCodeOfToday();

        assertFalse(quote.isPresent());
    }
}
