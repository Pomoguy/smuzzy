package com.pomoguy.smuzzy.task;


import com.pomoguy.smuzzy.dao.CalendarEventRepo;
import com.pomoguy.smuzzy.event.CalendarEventHandler;
import com.pomoguy.smuzzy.model.CalendarEvent;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.*;


public class CalendarEventTask extends TimerTask {



    private CalendarEventRepo calendarEventRepo;

    private final GatewayDiscordClient discordClient;

    private CalendarEvent calendarEvent;



    @Value("${discord.eventChannel}")
    private String eventChannel;

    public CalendarEventTask(GatewayDiscordClient discordClient, CalendarEvent calendarEvent, CalendarEventRepo calendarEventRepo) {
        this.discordClient = discordClient;
        this.calendarEvent = calendarEvent;
        this.calendarEventRepo = calendarEventRepo;
    }

    @Override
    public void run() {

        discordClient.rest().getChannelById(Snowflake.of("971684596997554197")).createMessage(calendarEvent.getText()).subscribe();


        if (calendarEvent.getRepeatAfter() != 0){

            Calendar newDate = Calendar.getInstance();
            newDate.setTime(calendarEvent.getDate());
            newDate.setTimeZone(TimeZone.getTimeZone("Russian Standard Time"));
            newDate.add(Calendar.MINUTE, calendarEvent.getRepeatAfter());
            calendarEvent.setDate(newDate.getTime());
            calendarEventRepo.save(calendarEvent);
            Timer timer = new Timer();
            timer.schedule(new CalendarEventTask(discordClient, calendarEvent, calendarEventRepo),calendarEvent.getDate());
        }

    }

}
