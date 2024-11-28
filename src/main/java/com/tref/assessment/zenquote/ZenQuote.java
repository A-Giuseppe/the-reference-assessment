package com.tref.assessment.zenquote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ZenQuote {
    @JsonProperty("q")
    private String quote;
    @JsonProperty("a")
    private String author;
    @JsonProperty("h")
    private String richQuote;
}
