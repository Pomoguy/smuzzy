package com.pomoguy.smuzzy.controller;


import com.pomoguy.smuzzy.dao.CalendarEventRepo;
import com.pomoguy.smuzzy.model.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/calendarevent")
public class CalendarEventController {

    @Autowired
    CalendarEventRepo calendarEventRepo;


    @PostMapping("/add")
    public void addEvent(@RequestBody CalendarEvent calendarEvent) {

        calendarEventRepo.save(calendarEvent);

    }

    @PostMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Integer id) {

        calendarEventRepo.deleteById(id);

    }

    @GetMapping("/get")
    public @ResponseBody ArrayList<CalendarEvent> getEvents() {

        return (ArrayList<CalendarEvent>) calendarEventRepo.findAll();
    }
}
