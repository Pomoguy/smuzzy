package com.pomoguy.smuzzy.config;

import discord4j.common.JacksonResources;
import discord4j.discordjson.json.ApplicationCommandData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class GlobalCommandRegister implements ApplicationRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Value("${discord.global}")
    private Boolean isGlobal;

    @Value("${discord.guildId}")
    private String guildId;



    private final RestClient client;

    public GlobalCommandRegister(RestClient client) {
        this.client = client;
    }

    //
    @Override
    public void run(ApplicationArguments args) throws IOException {
        final JacksonResources d4jMapper = JacksonResources.create();

        //
        PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();
        final ApplicationService applicationService = client.getApplicationService();
        final long applicationId = client.getApplicationId().block();

        List<ApplicationCommandRequest> commands = new ArrayList<>();
        for (Resource resource : matcher.getResources("commands/*.json")) {
            ApplicationCommandRequest request = d4jMapper.getObjectMapper()
                    .readValue(resource.getInputStream(), ApplicationCommandRequest.class);

            commands.add(request);
        }




        if (isGlobal) {
            applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
            //applicationService.deleteGlobalApplicationCommand(applicationId, commands)
                    .doOnNext(ignore -> LOGGER.debug("Successfully registered Global Commands"))
                    .doOnError(e -> LOGGER.error("Failed to register global commands", e))
                    .subscribe();
        } else {
            applicationService.bulkOverwriteGuildApplicationCommand(applicationId, Long.parseLong(guildId), commands)
                    .doOnNext(ignore -> LOGGER.debug("Successfully registered Guild Commands"))
                    .doOnError(e -> LOGGER.error("Failed to register guild commands", e))
                    .subscribe();
        }

    }
}
