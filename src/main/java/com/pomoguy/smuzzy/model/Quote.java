package com.pomoguy.smuzzy.model;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;


@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Unique
    private String quoteText;

    private String botComment;

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getBotComment() {
        return botComment;
    }

    public void setBotComment(String botComment) {
        this.botComment = botComment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Quote() {

    }

    public Quote(String quoteText, String botComment) {
        this.quoteText = quoteText;
        this.botComment = botComment;
    }
}
