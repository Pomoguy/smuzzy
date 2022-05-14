package com.pomoguy.smuzzy.event;


import com.pomoguy.smuzzy.model.Quote;
import discord4j.core.object.entity.Message;


import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;


@Controller
public class QuoteEvent {


    public Mono<Void> perform(Message eventMessage, Quote quote) {


        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
       //         .filter(message -> message.getContent().equalsIgnoreCase(quote.getQuoteText()))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(quote.getBotComment()))
                .then();
    }


}
