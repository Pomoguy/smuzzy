package com.pomoguy.smuzzy.config;


import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BotConfiguration {

    private static final Logger log = LoggerFactory.getLogger( BotConfiguration.class );

    @Value("${discord.token}")
    private String token;

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(token).build()
                .gateway()
                .setInitialPresence(ignore -> ClientPresence.online(ClientActivity.listening("to /commands")))
                .login()
                .block();
    }


    @Bean
    public RestClient discordRestClient(GatewayDiscordClient client) {
        return client.getRestClient();
    }

}