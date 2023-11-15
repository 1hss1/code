package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Book {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Book() {
    }

    public Book(Integer index, String name, String author, String percentage) {
        this.index = index;
        this.name = name;
        this.author = author;
        this.read = percentage;
        this.addTime = LocalDateTime.now().format(formatter);
    }

    private Integer index;

    private String name;

    private String author;

    private String read;

    private String addTime;

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String percentage) {
        this.read = percentage;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
