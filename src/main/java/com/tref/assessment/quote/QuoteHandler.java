package com.tref.assessment.quote;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.tref.assessment.zenquote.ZenQuote;
import com.tref.assessment.zenquote.ZenQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteHandler {

    private final ZenQuoteService zenQuoteService;

    @FunctionName("quoteSubscription")
    public void execute(
            @ServiceBusTopicTrigger(name = "message", topicName = "quote_of_the_day_topic", subscriptionName = "quote_subscription", connection = "AzureConnectionString")
            String message,
            ExecutionContext context) {
        context.getLogger().info(message);

        String quoteOfToday = zenQuoteService.getZenCodeOfToday()
                .map(ZenQuote::getQuote)
                .orElse("The quote of today is empty");

        context.getLogger().info(quoteOfToday);

    }
}
