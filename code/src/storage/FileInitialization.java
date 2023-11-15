package storage;


import entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileInitialization {

    public static boolean writeObject(ObservableList<Book> books) {
        // cite: https://docs.oracle.com/javase/6/docs/api/java/beans/XMLEncoder.html
        try {
            XMLEncoder e = new XMLEncoder(
                    new BufferedOutputStream(
                            Files.newOutputStream(Paths.get("./src/storage/data.xml"))));
            for (Book book:books) {
                e.writeObject(book);
            }
            e.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static ObservableList<Book> readObjets() {
        // cite https://docs.oracle.com/javase/6/docs/api/java/beans/XMLDecoder.html

        ObservableList<Book> books = FXCollections.observableArrayList();
        Book book = null;
        try {
            XMLDecoder e = new XMLDecoder(
                    new BufferedInputStream(
                            Files.newInputStream(Paths.get("./src/storage/data.xml"))));
            while ((book = (Book) e.readObject()) != null) {
                books.add(book);
            }
            e.close();
        } catch (Exception e) {
            return books;
        }
        return books;
    }

    public static void main(String[] args) {

        ObservableList<Book> books = FXCollections.observableArrayList();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        books.add(new Book(1, "Gone with the Wind", "Margaret Mitchell", "0/256"));
        books.add(new Book(2, "Alice’s Adventures in Wonderland", "Lewis Carroll", "0/359"));

        FileInitialization.writeObject(books);
        System.out.println(readObjets());
    }
}