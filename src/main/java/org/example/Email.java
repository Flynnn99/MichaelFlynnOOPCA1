package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public class Email
{
    private String to;
    private String from;
    private String subject;
    private LocalDateTime date;
    private String text;

    public  Email(String to, String from, String subject, String text)
    {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.date = LocalDateTime.now();
        this.text = text;
    }

    public String getTo() {return to;}
    public void setTo(String to) {this.to = to;}
    public String getFrom() {return from;}
    public void setFrom(String from) {this.from = from;}
    public String getSubject() {return subject;}
    public void setSubject(String subject) {this.subject = subject;}
    public LocalDateTime getDate() {return date;}
    public void setDate(LocalDateTime date) {this.date = date;}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}


    @Override
    public String toString() {
        return "Email{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }

}
