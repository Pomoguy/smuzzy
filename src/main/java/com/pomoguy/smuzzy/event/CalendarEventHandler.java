package com.pomoguy.smuzzy.event;

import com.pomoguy.smuzzy.dao.CalendarEventRepo;
import com.pomoguy.smuzzy.model.CalendarEvent;
import com.pomoguy.smuzzy.task.CalendarEventTask;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

@Controller
public class CalendarEventHandler {

    @Autowired
    CalendarEventRepo calendarEventRepo;

    @Autowired
    GatewayDiscordClient discordClient;


    @EventListener(ApplicationReadyEvent.class)
    public void executeCalendarEvents() {

        ArrayList<CalendarEvent> eventList = (ArrayList<CalendarEvent>) calendarEventRepo.findAll();

        //Timer timer = new Timer();
        for (CalendarEvent event : eventList){

            new CalendarEventTask(discordClient, event, calendarEventRepo).run();


            if (event.getDate().after(new java.util.Date())){
                //timer.schedule(new CalendarEventTask(event,discordClient),event.getDate());
            }
        }


    }

    public void updateCalendarEventTasks() {

    }




}
