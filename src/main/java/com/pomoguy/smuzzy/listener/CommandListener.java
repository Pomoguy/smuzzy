package com.pomoguy.smuzzy.listener;

import com.pomoguy.smuzzy.commands.QuoteCommand;
import com.pomoguy.smuzzy.commands.SlashCommand;
import com.pomoguy.smuzzy.dao.QuoteRepo;
import com.pomoguy.smuzzy.event.QuoteEventHandler;
import com.pomoguy.smuzzy.model.Quote;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Component
public class CommandListener {

    private final Collection<SlashCommand> commands;

    @Autowired
    private QuoteRepo quoteRepo;

    public CommandListener(List<SlashCommand> slashCommands, GatewayDiscordClient client) {
        commands = slashCommands;

        client.on(ChatInputInteractionEvent.class, this::handle).subscribe();
        client.on(MessageCreateEvent.class, this::handle).subscribe();

    }


    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return new QuoteCommand().handle(event, quoteRepo);
    }

    public Mono<Void> handle(MessageCreateEvent event) {
        Message message = event.getMessage();

       List<Quote> quoteList = quoteRepo.findByQuoteText(message.getContent());

       if (quoteList.isEmpty()) {
           return Mono.just(message).then();
       } else {
           Quote quote = quoteList.get(0);
        return new QuoteEventHandler().perform(message,quote);
       }
    }


}
