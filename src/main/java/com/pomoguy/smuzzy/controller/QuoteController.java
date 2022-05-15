package com.pomoguy.smuzzy.controller;

import com.pomoguy.smuzzy.dao.CalendarEventRepo;
import com.pomoguy.smuzzy.dao.QuoteRepo;
import com.pomoguy.smuzzy.model.CalendarEvent;
import com.pomoguy.smuzzy.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    QuoteRepo quoteRepo;


    @PostMapping("/add")
    public void addQuote(@RequestBody Quote quote) {

        quoteRepo.save(quote);

    }

    @PostMapping("/delete/{id}")
    public void deleteQuote(@PathVariable Integer id) {

        quoteRepo.deleteById(id);

    }

    @GetMapping("/get")
    public @ResponseBody
    ArrayList<Quote> getQuotes() {

        return (ArrayList<Quote>) quoteRepo.findAll();
    }
}
