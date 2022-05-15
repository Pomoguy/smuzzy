package com.pomoguy.smuzzy.commands;

import com.pomoguy.smuzzy.dao.QuoteRepo;
import com.pomoguy.smuzzy.model.Quote;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;


public class QuoteCommand {


    public String getName() {
        return "quote";
    }


    public Mono<Void> handle(ChatInputInteractionEvent event, QuoteRepo quoteRepo) {

        String quoteText = event.getOption("quotetext")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        String botComment = event.getOption("botcomment")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();


        Quote quote = new Quote(quoteText,botComment);
        quoteRepo.save(quote);

        return event.reply().withEphemeral(true).withContent("Цитата добавлена");
    }
}
