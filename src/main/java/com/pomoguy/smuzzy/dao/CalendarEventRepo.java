package com.pomoguy.smuzzy.dao;

import com.pomoguy.smuzzy.model.CalendarEvent;
import org.springframework.data.repository.CrudRepository;

public interface CalendarEventRepo extends CrudRepository<CalendarEvent, Integer> {
}
